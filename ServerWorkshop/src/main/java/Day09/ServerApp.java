package Day09;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerApp {
    private ServerApp(){
    }
    public static void main( String[] args) throws IOException{

        // need the random class to carry out randomise operation
        Random random = new Random();

        // generate random number between o and 99
        Integer randomNumber = random.nextInt(100);

        // store my guess
        Integer myGuess = 0;

        // open the server socket to listen to server 1234 for input
        System.out.println("Server running on port");
        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();

        // preparing input coming in through socket from client (receiving)
        // to read and write
        InputStream is = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // prepare sending data out using socket to client (sending out)
        OutputStream os = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        String msgRecv = "";

        while(msgRecv.equals("quit")) {
            // guess XX
            msgRecv = dis.readUTF();

            if (msgRecv.contains("guess")){
                myGuess = Integer.parseInt(msgRecv.substring(6));
            }

            if (myGuess < randomNumber) {
                dos.writeUTF("Guess higher!");
            } else if (myGuess > randomNumber){
                dos.writeUTF("Guess lower!");
            } else {
                dos.writeUTF("Bingo!");
            }
            // ensure records are written and sent across the socket
            dos.flush();

        }

        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();
    }
}

