package org.servoframework.http;

import org.servoframework.Servo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class ListenningThread extends Thread {
    private Servo servo;
    private ServerSocket serverSocket;

    public ListenningThread(Servo servo) {
        this.servo = servo;
    }

    @Override
    public void run() {
        Socket client;
        AcceptThread acceptThread;
        try {
            // listening
            while ((client = serverSocket.accept()) != null) {

                // create accept thread
                acceptThread = new AcceptThread(servo, client);
                acceptThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
