package com.sensorAnalyzer.control;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sensorAnalyzer.model.AnglesDAO;
import com.sensorAnalyzer.model.AnglesVO;
import com.sensorAnalyzer.view.SensorActivity;

import java.util.ArrayList;

/**
 * Created by leonardo on 15/05/15.
 */
public class ControlSensorEvent implements SensorEventListener {

    private SensorActivity sensorActivity;
    private float[] values;
    private float x_old, y_old, minimumOscillation;
    private int cont_toSave, maxValues_toSave;


    public ControlSensorEvent(SensorActivity sensorActivity) {
        this.sensorActivity = sensorActivity;
        this.cont_toSave = 0;
        this.minimumOscillation = 0.25f;
        this.maxValues_toSave = 5;
        this.x_old = this.y_old = 0f;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        this.values = event.values;
        this.cont_toSave++;

        if (this.cont_toSave == this.maxValues_toSave) {

            if (Math.abs(this.values[0] - this.x_old) > this.minimumOscillation ||
                    Math.abs(this.values[1] - this.y_old) > this.minimumOscillation) {

                this.x_old = this.values[0];
                this.y_old = this.values[1];

            } else {
                this.values[0] = this.x_old;
                this.values[1] = this.y_old;

            }

            float x_angle = mapValueToAngle(this.values[0], -10f, 10f, -90, 90),
                    y_angle = mapValueToAngle(this.values[1], -10f, 10f, -90, 90);

            if (this.sensorActivity.getSwitch_saveDatas().isChecked()) {

                try {
                    Object[] params = {
                            "write",
                            new AnglesVO(x_angle, y_angle),
                            this.sensorActivity.getEditText_nameFile().getText().toString()
                    };

                    AnglesDAO anglesDAO = new AnglesDAO();
                    anglesDAO.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);// usando o executeOnExecutor para rodar multiplas AsyncTasks

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this.sensorActivity, "Erro ao salvar dados no arquivo CSV\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    this.sensorActivity.unregisterSensorManager();

                }
            }

            if (this.sensorActivity.getSwitch_sendStatus().isChecked()) {

                try {
                    ArrayList<Float> arrayListAngles = new ArrayList<>();
                    arrayListAngles.add(x_angle);
                    arrayListAngles.add(y_angle);

                    Object[] params = {
                            this.sensorActivity.getEditText_IPToSendStatus().getText().toString(), //IP do servidor de status
                            9999, //porta usada
                            arrayListAngles //dados que serão enviados
                    };

                    ControlTransference controlTransference = new ControlTransference();
                    controlTransference.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);// usando o executeOnExecutor para rodar multiplas AsyncTask

                } catch (Exception e) {

                    Toast.makeText(this.sensorActivity, "Erro ao enviar status\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    this.sensorActivity.unregisterSensorManager();
                    e.printStackTrace();
                }

            }

            this.sensorActivity.getTextView_sensorName().setText(event.sensor.getName());

            this.sensorActivity.getTextView_x_axis().setText("" + x_angle);
            this.sensorActivity.getTextView_y_axis().setText("" + y_angle);

            this.cont_toSave = 0;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private int mapValueToAngle(float x, float input_min, float input_max, int output_min, int output_max)
    {
        int angle = (int) ((x - input_min) * (output_max - output_min) / (input_max - input_min) + output_min);

        return angle;
    }
}
