package sgf;

import java.util.List;

public class GameInfo {
    public String rules;
    public String whitePlayerName;
    public String whitePlayerRank;
    public String blackPlayerName;
    public String blackPlayerRank;
    public String date;
    public String result;
    public String komi;
    public String boardSize;
    public String handicap;
    public List<Move> moves;
    
    @Override
    public String toString() {
        String s;
    
        s = "Board Size: "+boardSize+"\n";
        s += "Rules: "+rules+"\n";
        s += "Komi: "+komi+"\n";
        s += "Handicap: "+handicap+"\n";
        s += "White Player: "+whitePlayerName+"\n";
        s += "White Rank: "+whitePlayerRank+"\n";
        s += "Black Player: "+blackPlayerName+"\n";
        s += "Black Rank: "+blackPlayerRank+"\n";
        s += "Result: "+result+"\n";
        
        for(Move m:moves) {
            s += m.player+"("+m.x+", "+m.y+")\n";
        }
        
        return s;
    }
}