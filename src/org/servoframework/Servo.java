package org.servoframework;

import org.servoframework.http.ListenningThread;
import org.servoframework.http.SpecificRouting;
import org.servoframework.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unidev on 2017. 7. 4..
 */

public class Servo {
    public final static String VersionName = "0.0.1";
    private int port = 80;
    private ServerSocket serverSocket;
    private static final String TAG = "ServoFramework";
    private static final int VersionCode = 1;

    private ListenningThread listenningThread;
    private List<Router> router = new ArrayList<>();
    private List<Router> defaultRouter = new ArrayList<>();
    private List<String> publics = new ArrayList<>();
    private Preferences preferences;

    public Servo() {
        listenningThread = new ListenningThread(this);
        addController(SpecificRouting.class, defaultRouter);
        preferences = Preferences.getInstance();
    }

    public boolean startServer() {
        setPort();
        try {
            serverSocket = new ServerSocket(port);
            Log.i(TAG, "Create Server Socket at port " + port);
            listenningThread.setServerSocket(serverSocket);
            listenningThread.start();

            return true;
        } catch (IOException e) {
            Log.i(TAG, "Creating server failed");
            e.printStackTrace();
            return false;
        }
    }
}
