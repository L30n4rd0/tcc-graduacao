package com.aferidor.control;

import android.view.View;
import android.widget.Toast;

import com.aferidor.view.MainActivity;

/**
 * Created by leonardo on 02/09/15.
 */
public class ControlServerStartStop implements View.OnClickListener {

    private MainActivity mainActivity;

    public ControlServerStartStop(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {

        if (this.mainActivity.getTextView_serverStatus().getText().toString().equals("Servidor Parado")) {
            try {
                ControlSocketServer.getUNIQ_CONTROL_SERVER(this.mainActivity).startServer();

//            Toast.makeText(v.getContext(), "Iniciando servidor", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(v.getContext(), "Erro ao iniciar servidor\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        } else {

            try {
                ControlSocketServer.getUNIQ_CONTROL_SERVER(this.mainActivity).stopServer();

//            Toast.makeText(v.getContext(), "Iniciando servidor", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(v.getContext(), "Erro ao parar servidor\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (Throwable throwable) {
                Toast.makeText(v.getContext(), "Erro ao parar servidor\n" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                throwable.printStackTrace();
            }

        }
    }
}
