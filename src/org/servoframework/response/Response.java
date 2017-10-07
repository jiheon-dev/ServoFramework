package org.servoframework.response;

import org.servoframework.Preferences;
import org.servoframework.Servo;
import org.servoframework.json.JSONObject;
import org.servoframework.library.Renderer;
import org.servoframework.middleware.DefaultRenderer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.Socket;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by unidev on 2017. 7. 4..
 */
public class Response {
    private static final String TAG = "Response";
    private Socket socket;
    private DataOutputStream dos;
    private StringBuilder content = new StringBuilder();
    private int responseCode = 200;
    private String responseString = "OK";
    private Map<String, String> headerOptions = new HashMap<>();
    private Map<String, String> cookieOptions;
    private OnErrorListener onErrorListener;

    public Response(Socket socket, DataOutputStream dos) {
        this.socket = socket;
        this.dos = dos;
        headerOptions.put("Content-Type", "text/html; charset=utf-8");
        headerOptions.put("Server", "Servo/" + Servo.VersionName);
        headerOptions.put("Connection", "close");
    }

    public void write(String msg) {
        content.append(msg);
//        end();
    }

    public void send(String path) {
        try {
            BufferedReader f;
            try {
                f = new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException e) {
                f = new BufferedReader(new InputStreamReader(Response.class.getResourceAsStream(path)));
            }
            String content = "";
            String tmp;
            while ((tmp = f.readLine()) != null) {
                content += tmp;
            }

            writeHeader();
            byte[] bytes = content.getBytes("UTF-8");
            dos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            if(onErrorListener != null) {
                onErrorListener.onError("Output Stream Writing Error", socket);
            }
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void writeHeader() {
        try {
            dos.writeBytes("HTTP/1.1 " + responseCode + " " + responseString + "\r\n");
            // write cookie
            if(cookieOptions != null) {
                for(String key : cookieOptions.keySet()) {
                    dos.writeBytes("Set-Cookie: " + key + "=" + cookieOptions.get(key) + "\r\n");
                }
            }
            for (String key : headerOptions.keySet()) {
                dos.writeBytes(key + ": " + headerOptions.get(key) + "\r\n");
            }
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            if(onErrorListener != null) {
                onErrorListener.onError("Output Stream Writing Error", socket);
            }
            e.printStackTrace();
        }
    }

    public void end() {
        try {
            writeHeader();
            byte[] bytes = content.toString().getBytes("UTF-8");
            dos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            if(onErrorListener != null) {
                onErrorListener.onError("Output Stream Writing Error", socket);
            }
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void render(String path) {
        render(path, null);
    }


    public void render(String path, Map<String, ?> params) {
        Preferences preference = Preferences.getInstance();
        Class c = (Class)preference.get("view engine");
        String result = null;
        if(c != null) {
            try {
                Renderer o = (Renderer)c.newInstance();
                // check library type
                if(o.getType().equals("view engine"))
                    result = o.render(path, params);
                else
                    onErrorListener.onError("Rendering engine Error", socket);
            } catch (IllegalAccessException|InstantiationException e) {
                e.printStackTrace();
                onErrorListener.onError("Rendering Method Error", socket);
                return;
            }
        } else {
            DefaultRenderer defaultRenderer = new DefaultRenderer();
            result = defaultRenderer.render(path, params);
        }

        if(result == null) {
            onErrorListener.onError("Rendering Result Problem", socket);
            return;
        }

        try {
            writeHeader();
            byte[] bytes = result.getBytes("UTF-8");
            dos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCode(int val) {
        responseCode = val;
    }

    public void cookie(String key, String val) {
        if(cookieOptions == null) {
            cookieOptions = new HashMap<>();
        }
        cookieOptions.put(key, val);
    }

    public void expireCookie(String key) {
        if(cookieOptions == null) {
            cookieOptions = new HashMap<>();
        }
        cookieOptions.put(key, "; Expires=Sun, 06 Nov 1994 08:49:37 GMT");
    }

    public void setResponseString(String val) {
        responseString = val;
    }

    public void setHeader(String key, String val) {
        headerOptions.put(key, val);
    }

    public void json(HashMap<String, Object> map) {
        JSONObject obj = new JSONObject(map);
        content.append(obj.toJSONString());
//        end();
    }

    public void json(HashMap<Object, Object> map) {
        JSONObject obj = new JSONObject(map);
        content.append(obj.toJSONString());
//        end();
    }

    public void setOnErrorListener(OnErrorListener listener) {
        onErrorListener = listener;
    }

    /**
     * On Error Listener
     * If there is some error, this interface's {@link #onError(String, Socket)} will be called.
     */

    public interface OnErrorListener {
        /**
         * On Error
         *
         * @param msg error message
         * @param socket error socket
         */
        void onError(String msg, Socket socket);
    }

}
