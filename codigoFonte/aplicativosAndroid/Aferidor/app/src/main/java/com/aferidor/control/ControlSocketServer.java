package com.aferidor.control;

import android.graphics.Color;
import android.os.AsyncTask;

import com.aferidor.view.MainActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by leonardo on 05/06/15.
 */
public class ControlSocketServer extends AsyncTask {

    private MainActivity mainActivity;
    private boolean running;
    protected static ControlSocketServer UNIQ_CONTROL_SERVER = null;

    public ControlSocketServer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.running = true;
    }

    public static ControlSocketServer getUNIQ_CONTROL_SERVER(MainActivity mainActivity) {

        if (UNIQ_CONTROL_SERVER == null)
            UNIQ_CONTROL_SERVER = new ControlSocketServer(mainActivity);

        return UNIQ_CONTROL_SERVER;

    }

    @Override
    protected String doInBackground(Object[] params) {
        String resposta = null;

        try {
            ServerSocket serverSocket;
            Socket socket;
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;
            ArrayList<Object> option;

            while (this.running) {
                serverSocket = new ServerSocket((Integer) params[0]);
                socket = serverSocket.accept();

                if (this.running) {
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    option = (ArrayList<Object>) objectInputStream.readObject();

                    switch ( (int)option.get(0) ) {
                        case 1: //registrar sensores
                            this.mainActivity.registerSensorManager();
                            break;

                        case 2: //desregistrar sensores
                            this.mainActivity.unregisterSensorManager();
                            break;

                        case 3: //salvar dados em arquivo CSV no celular local
                            this.mainActivity.setDataSave(true);
                            break;

                        case 4: //desativar o salvamento de arquivo CSV
                            this.mainActivity.setDataSave(false);
                            break;

                        case 5: //enviar angulos em tempo real para o dispositivo de controle
                            this.mainActivity.setIpSendStatus((String) option.get(1));
                            this.mainActivity.setSendStatus(true);
                            break;

                        case 6: //desativar o envio de angulos
                            this.mainActivity.setSendStatus(false);
                            break;

                        default:
                            this.mainActivity.setDirection( (int)option.get(0) );
                            break;

                    }
                    objectOutputStream.writeObject(option);
                }
                objectInputStream.close();

                objectOutputStream.flush();
                objectOutputStream.close();

                socket.close();
                serverSocket.close();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return resposta;
    }

    protected void startServer() throws Exception {
        Object[] params = new Object[1];
        params[0] = 6789;

        this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);// usando o executeOnExecutor para rodar multiplas AsyncTask

        this.mainActivity.getTextView_serverStatus().setText("Servidor Iniciado");
        this.mainActivity.getButton_serverStartStop().setText("Parar Servidor");
        this.mainActivity.getButton_serverStartStop().setBackgroundColor(Color.parseColor("#83BE2136"));

    }

    protected void stopServer() throws Throwable {
        this.running = false;
        this.finalize();

        this.mainActivity.getTextView_serverStatus().setText("Servidor Parado");
        this.mainActivity.getButton_serverStartStop().setText("Iniciar Servidor");
        this.mainActivity.getButton_serverStartStop().setBackgroundColor(Color.parseColor("#833544ed"));

        System.exit(0);

    }
}
