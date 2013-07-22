/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiano
 */
public class NetworkClientController implements Runnable {
    GameClient client;
    NetworkListener listener;
    
    public NetworkClientController(String addr, int port) {
        try {
            client = new GameClient(addr, port);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void send(String msg) {
        try {
            client.send(msg);
        } catch (IOException ex) {
            Logger.getLogger(NetworkClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setListener(NetworkListener l) {
        listener = l;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                String hostResponse = client.receive();
                
                listener.onResponse(hostResponse);
            } catch (IOException ex) {
                Logger.getLogger(NetworkClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
