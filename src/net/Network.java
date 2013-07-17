/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiano
 */
public class Network {
    ServerSocket server;
    Socket client;
    PrintWriter out;
    BufferedReader in;
    
    public boolean host(int port) {
        try {
            server = new ServerSocket(port);
            client = server.accept();
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        return true;
    }
    
    public boolean send(String host, int port, String msg) {
        try {
            out = new PrintWriter(new Socket(host, port).getOutputStream(), true);
            out.print(msg);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        return true;
    }
    
    public boolean close() {
        try {
            if(server != null) {
                server.close();
            }

            if(client != null) {
                client.close();
            }

            if(out != null) {
                out.close();
            }

            if(in != null) {
                in.close();
            }
        } catch(IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
        
        return true;
    }
}
