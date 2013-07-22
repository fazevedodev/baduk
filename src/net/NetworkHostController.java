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
public class NetworkHostController implements Runnable {
    GameHost host;
    NetworkListener listener;
    
    public NetworkHostController() {
        
    }
    
    public void setListener(NetworkListener l) {
        listener = l;
    }
    
    public void send(String msg) {
        try {
            host.send(msg);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            host = new GameHost(7000);
        } catch (IOException ex) {
            Logger.getLogger(NetworkHostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true) {
            try {
                String clientResponse = host.receive();
                
                listener.onResponse(clientResponse);
            } catch (IOException ex) {
                Logger.getLogger(NetworkHostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
