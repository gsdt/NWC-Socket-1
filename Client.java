/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giaosudauto
 */
public class Client {
    public static void main(String[] args) {
        
        Socket server;
        //read data from server
        DataInputStream dis;
        //output data to server
        DataOutputStream dos;
        // message
        String messasge = "Nguyen Anh Tuan - SE62864 - 19/05/1998";
        try {
            server = new Socket("localhost", 1234);
            dis = new DataInputStream(server.getInputStream());
            dos = new DataOutputStream(server.getOutputStream());
            
            System.out.println("Sending to server: " + messasge);
            dos.writeUTF(messasge);
            
            System.out.println("Receiving: " + dis.readUTF());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
