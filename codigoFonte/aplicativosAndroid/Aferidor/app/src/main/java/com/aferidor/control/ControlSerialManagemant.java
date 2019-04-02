package com.aferidor.control;

import android.app.Activity;
import android.widget.Toast;

import com.aferidor.model.SerialManagemant;

/**
 * Created by leonardo on 03/09/15.
 */
public class ControlSerialManagemant {
    private Activity activity;

    public ControlSerialManagemant(Activity activity) {
        this.activity = activity;
        SerialManagemant.getUniqueSerialManagemant(this.activity.getApplicationContext());
    }

    public void openUSBConnection () {

        try {
            SerialManagemant.getUniqueSerialManagemant(this.activity.getApplicationContext()).openUSBConnection();

        } catch (Exception e) {
            Toast.makeText(this.activity.getApplicationContext(), "Erro ao abrir conexão USB", Toast.LENGTH_LONG);
            e.printStackTrace();
        }


    }

    public void startUSBConnection () {
        try {
            SerialManagemant.getUniqueSerialManagemant(this.activity.getApplicationContext()).startUSBConnection();

        } catch (Exception e) {
            Toast.makeText(this.activity.getApplicationContext(), "Erro ao iniciar conexão USB", Toast.LENGTH_LONG);
            e.printStackTrace();
        }

    }

    public void closeUSBConnection () {

        try {
            SerialManagemant.getUniqueSerialManagemant(this.activity.getApplicationContext()).closeUSBConnection();

        } catch (Exception e) {
            Toast.makeText(this.activity.getApplicationContext(), "Erro ao fechar conexão USB", Toast.LENGTH_LONG);
            e.printStackTrace();
        }

    }

}
