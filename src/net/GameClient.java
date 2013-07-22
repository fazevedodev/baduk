package net;

import java.io.*;
import java.net.*;

public class GameClient {
    Socket socket;
    
    //Output
    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    BufferedWriter bufferedWriter;
    
    //Input
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    
    public GameClient(String address, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(address);
        socket = new Socket(addr, port);
        
        outputStream = socket.getOutputStream();
        outputStreamWriter = new OutputStreamWriter(outputStream);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        
        inputStream = socket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }
    
    public void send(String msg) throws IOException {
        if(bufferedWriter == null) {
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }
    
        bufferedWriter.write(msg);
        bufferedWriter.flush();
    }
    
    public String receive() throws IOException {
        return bufferedReader.readLine();
    }
    
    public void close() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        
        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();
        
        socket.close();
    }
    
    public static void main(String args[]) {
        System.out.println("Game Client running...");
    
        GameClient client;
        boolean gameOver = false;
    
        try {
            System.out.println("Attempting to connect to the host...");
            client = new GameClient(args[0], Integer.parseInt(args[1]));
            System.out.println("Connected to host.");
            
            client.send("JOIN\n");
            
            while(!gameOver) {
                String reply = client.receive();
                
                if(reply.equals("GAMEOVER")) {
                    gameOver = true;
                    
                    client.send("ACK\n");
                }
            }
            
            client.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}