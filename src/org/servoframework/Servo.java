package org.servoframework;

import org.servoframework.annotation.Route;
import org.servoframework.http.ListenningThread;
import org.servoframework.http.SpecificRouting;
import org.servoframework.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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

    public void setPort(int port) {
        Log.i(TAG, "Set Port: " + port);
        this.port = port;
    }

    public void stopServer() {
        try {
            Log.i(TAG, "Stopping Server");
            if (serverSocket.isBound()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addController(Class controller) {
        addController(controller, router);
    }

    private void addController(Class controller, List<Router> routers) {
        Method[] methods = controller.getDeclaredMethods();
        for(Method method : methods) {
            Route route = method.getAnnotation(Route.class);
            if(route != null) {
                routers.add(new Router(method, route.route(), route.method()));
            }
        }
    }

    public Method notFound() {
        return getRouter(Route.RouteMethod.ALL, "/404");
    }

    public Method serverError() {
        return getRouter(Route.RouteMethod.ALL, "/500");
    }

    public Method getRouter(Route.RouteMethod routeMethod, String path) {
        Method m = getMethod(router, routeMethod, path);
        if(m == null) {
            return getMethod(defaultRouter, routeMethod, path);
        } else {
            return m;
        }
    }

    private Method getMethod(List<Router> routers, Route.RouteMethod routeMethod, String path) {
        for (Router r : routers) {
            if ((r.routeMethod == routeMethod || r.routeMethod == Route.RouteMethod.ALL) && r.path.equals(path)) {
                return r.method;
            }
        }
        return null;
    }

    public void addPublic(String path) {
        File f = new File(path);
        if(!f.isDirectory()) {
            Log.e(TAG, path + " is not directory!!");
        } else {
            publics.add(path);
        }
    }

    public File getPublic(String path) {
        for(String pub:publics) {
            File f = new File(pub + path);
            if(f.exists() && !f.isDirectory()) {
                return f;
            }
        }
        return null;
    }

    public void set(String key, Object object) {
        // if key is public object is public path
        if(key.equals("public"))
            publics.add((String)object);
        preferences.put(key, object);
    }

    public Object get(String key) {
        if(preferences == null)
            return null;
        return preferences.get(key);
    }
}
