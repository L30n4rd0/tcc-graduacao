package com.aferidor.control;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by leonardo on 05/06/15.
 */
public class ControlSocketClient extends AsyncTask {

    @Override
    protected String doInBackground(Object[] params) {

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        String resposta = null;

        try {
            socket = new Socket((String) params[0], (int) params[1]);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject(params[2]);

        } catch (Exception e){
            e.printStackTrace();

        } finally {

            try {
                if (socket != null)
                    socket.close();

                if (objectInputStream != null)
                    objectInputStream.close();

                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resposta;
    }
}
