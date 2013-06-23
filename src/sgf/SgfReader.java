package sgf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SgfReader {
    public static final String SIZE_PATTERN = "SZ\\[\\d+\\]";
    public static final String RULES_PATTERN = "RU\\[\\w+\\]";
    public static final String KOMI_PATTERN = "KM\\[\\d+\\.\\d+\\]";
    public static final String WPLAYER_PATTERN = "PW\\[\\w+\\]";
    public static final String BPLAYER_PATTERN = "PB\\[\\w+\\]";
    public static final String WRANK_PATTERN = "WR\\[\\w+\\+?\\]";
    public static final String BRANK_PATTERN = "BR\\[\\w+\\+?\\]";
    public static final String RESULT_PATTERN = "RE\\[(W|B)\\+\\d+\\.?\\d+\\]";
    public static final String DATE_PATTERN = "DT\\[\\d{4}-\\d{2}-\\d{2}\\]";
    public static final String MOVES_PATTERN = "(;W\\[(\\w\\w)?\\])|(;B\\[(\\w\\w)?\\])";
    public static final String HANDICAP_PATTERN = "HA\\[\\d\\]";

    public static GameInfo getGameInfo(String filename) throws IOException {
        GameInfo info = new GameInfo();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String fileContents = null;
        
        while(reader.ready()) {
            fileContents += reader.readLine();
        }
        
        reader.close();
        
        info.boardSize = clean(matcher(sizePattern, fileContents));
        info.rules = clean(matcher(rulesPattern, fileContents));
        info.komi = clean(matcher(komiPattern, fileContents));
        info.handicap = clean(matcher(handicapPattern, fileContents));
        info.whitePlayerName = clean(matcher(wplayerPattern, fileContents));
        info.blackPlayerName = clean(matcher(bplayerPattern, fileContents));
        info.whitePlayerRank = clean(matcher(wrankPattern, fileContents));
        info.blackPlayerRank = clean(matcher(brankPattern, fileContents));
        info.result = clean(matcher(resultPattern, fileContents));
        info.date = clean(matcher(datePattern, fileContents));
        info.moves = getMoves(fileContents);
        
        return info;
    }
    
    private static String matcher(Pattern p, String s) {
        Matcher m = p.matcher(s);
        
        if(m.find()) {        
            return m.group(0);
        }
        else {
            return null;
        }
    }
    
    private static List<Move> getMoves(String s) {
        List<Move> moves = new ArrayList<>();
        
        Matcher m = movesPattern.matcher(s);
        
        while(m.find()) {
            Move move = new Move();
            
            String tmp = m.group(0);
            move.player = tmp.charAt(1)+"";
            
            if(tmp.length() == 6) {
                move.x = tmp.charAt(3)-'a';
                move.y = tmp.charAt(4)-'a';
            }
            else {
                move.x = -1; //It's a pass move
                move.y = -1; //It's a pass move
            }
            
            moves.add(move);
        }
        
        return moves;
    }
    
    private static String clean(String s) {
        if(s == null) {
            return null;
        }
        
        if(s.indexOf("[") < 0 || s.indexOf("]") < 0) {
            return s;
        }
    
        return s.substring(s.indexOf("[")+1, s.indexOf("]"));
    }
    
    private static Pattern sizePattern = Pattern.compile(SIZE_PATTERN);
    private static Pattern rulesPattern = Pattern.compile(RULES_PATTERN);
    private static Pattern komiPattern = Pattern.compile(KOMI_PATTERN);
    private static Pattern wplayerPattern = Pattern.compile(WPLAYER_PATTERN);
    private static Pattern bplayerPattern = Pattern.compile(BPLAYER_PATTERN);
    private static Pattern wrankPattern = Pattern.compile(WRANK_PATTERN);
    private static Pattern brankPattern = Pattern.compile(BRANK_PATTERN);
    private static Pattern resultPattern = Pattern.compile(RESULT_PATTERN);
    private static Pattern datePattern = Pattern.compile(DATE_PATTERN);
    private static Pattern movesPattern = Pattern.compile(MOVES_PATTERN);
    private static Pattern handicapPattern = Pattern.compile(HANDICAP_PATTERN);
}