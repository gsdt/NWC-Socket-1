package e7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author giaosudauto
 */
public class Server {
    
    
    public static void main(String[] args) {
        ServerSocket server;
        Socket client;
        //read data from client
        DataInputStream dis;
        //output data to client
        DataOutputStream dos;
        try {
            server = new ServerSocket(1234);
            System.out.println("Started server");
            // wait for client connection
            client = server.accept();
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            
            String msg = dis.readUTF();
            if (msg != null) {
                // split message to field of data
                String[] arr = msg.split(" - ");
                // get infomation
                String name = arr[0];
                String id = arr[1];
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(arr[2]);

                Calendar birthday  = Calendar.getInstance();
                Calendar nextBirthday = Calendar.getInstance();
                Calendar today = Calendar.getInstance();

                birthday.setTime(date);
                nextBirthday.setTime(date);
                // next birthday in this year?
                nextBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR));
                // if birthday in this year has passed
                if(nextBirthday.compareTo(today)<0){
                    // next birthday in next year.
                    nextBirthday.add(Calendar.YEAR, 1);
                }

                long differ = nextBirthday.getTime().getTime() - today.getTime().getTime();
                differ /= 60*1000;

                dos.writeUTF(String.format("Hello %s (%s), your next birhday come in %d minutes", name, id, differ));
            }
            
        } catch (Exception e) {
            System.out.println("Start server take an error.");
        }
    }
}
