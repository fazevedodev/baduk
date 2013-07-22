package net;

import java.net.*;
import java.io.*;

public class GameHost {
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    
    OutputStream outputStream;
    OutputStreamWriter outputStreamWriter;
    BufferedWriter bufferedWriter;
    
    ServerSocket serverSocket;
    Socket clientSocket;
    
    public GameHost(int p) throws IOException {
        serverSocket = new ServerSocket(p);
        clientSocket = serverSocket.accept();
        
        outputStream = clientSocket.getOutputStream();
        outputStreamWriter = new OutputStreamWriter(outputStream);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        
        inputStream = clientSocket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }
    
    public String receive() throws IOException {
        return bufferedReader.readLine();
    }
    
    public void send(String msg) throws IOException {
        if(bufferedWriter == null) {
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }
    
        bufferedWriter.write(msg);
        bufferedWriter.flush();
    }
    
    public void close() throws IOException {
        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();
        
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        
        clientSocket.close();
        serverSocket.close();
    }
    
    public static void main(String args[]) {
        boolean gameOver = false;
        boolean accept = false;
    
        System.out.println("Game Host running...");
    
        GameHost host;
    
        try {
            host = new GameHost(7000);
            
            System.out.println("Waiting for client connection...");
            
            while(!accept) {
                String requestMsg = host.receive();
                
                if(requestMsg.equals("JOIN")) {
                    System.out.println("Connection accepted.");
                    accept = true;
                }
                else {
                    System.out.println("Connection refused.");
                }
            }
            
            while(!gameOver) {
                host.send("GAMEOVER\n");
            
                String clientReplyMsg = host.receive();
                
                if(clientReplyMsg == null) {
                    gameOver = true;
                }
                else if(clientReplyMsg.equals("ACK")) {
                    gameOver = true;
                }
            }
            
            host.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}