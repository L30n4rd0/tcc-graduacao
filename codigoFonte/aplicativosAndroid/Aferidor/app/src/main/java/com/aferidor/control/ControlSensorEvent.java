package com.aferidor.control;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aferidor.model.AnglesDAO;
import com.aferidor.model.AnglesVO;
import com.aferidor.model.SerialManagemant;
import com.aferidor.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by leonardo on 15/05/15.
 */
public class ControlSensorEvent implements SensorEventListener {

    private MainActivity main_Activity;
    private float interval, x_old, y_old, minimumOscillation;
    private float[] values;
    private String fileName;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");
    private int maxValues, cont, direction_old;

    public ControlSensorEvent(MainActivity mainActivity) {
        this.main_Activity = mainActivity;
        this.fileName = "Dados__" + this.dateFormat.format(Calendar.getInstance().getTime()); //nome do arquivo salvo
        this.maxValues = 5;
        this.interval = 2.65f;
        this.cont = 0;
        this.minimumOscillation = 0.25f;
        this.x_old = this.y_old = 0f;
        this.direction_old = this.main_Activity.getDirection();
        SerialManagemant.getUniqueSerialManagemant(this.main_Activity);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        this.cont++;
        this.values = event.values;

        if (this.cont == this.maxValues) { // Entra no if pra enviar ao Arduino, salvar em arquivo local e enviar status

            try {

                if (Math.abs(this.values[0] - this.x_old) > this.minimumOscillation ||
                        Math.abs(this.values[1] - this.y_old) > this.minimumOscillation) {

                    int current_angle_x = mapValueToAngle(this.values[0], -this.interval, this.interval, 0, 180);
                    SerialManagemant.getUniqueSerialManagemant(main_Activity).sendToArduino(current_angle_x + "a");

                    int current_angle_y = mapValueToAngle(this.values[1], -this.interval, this.interval, 0, 180);
                    SerialManagemant.getUniqueSerialManagemant(main_Activity).sendToArduino(current_angle_y + "b");

                    this.main_Activity.getTextView_angleYValue().setText("ÂnguloY: " + current_angle_y);
                    this.main_Activity.getTextView_angleXValue().setText("ÂnguloX: " + current_angle_x);

                    this.x_old = this.values[0];
                    this.y_old = this.values[1];

                } else {
                    this.values[0] = this.x_old;
                    this.values[1] = this.y_old;

                }

                float x = mapValueToAngle(this.values[0], -10f, 10f, -90, 90), y = mapValueToAngle(this.values[1], -10f, 10f, -90, 90);

                if (this.main_Activity.isDataSave()) {
                    try {

                    Object[] params = {
                            "write", //tipo de operação
                            new AnglesVO(x, y), //dados que serão salvos
                            this.fileName //nome do arquivo salvo
                    };

                    AnglesDAO anglesDAO = new AnglesDAO();
                    anglesDAO.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);// usando o executeOnExecutor para rodar multiplas AsyncTasks

                    } catch (Exception e) {
                        Toast.makeText(this.main_Activity, "Erro ao salvar dados no arquivo CSV\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        this.main_Activity.unregisterSensorManager();
                        e.printStackTrace();

                    }
                }

                if (this.main_Activity.isSendStatus()) {
                    try {
                    ArrayList<Float> arrayListAngles = new ArrayList<>();
                    arrayListAngles.add(x);
                    arrayListAngles.add(y);

                    Object[] params = {
                            this.main_Activity.getIpSendStatus(), //IP do servidor para envio de status
                            8888, //porta usada
                            arrayListAngles //dados que serão enviados
                    };

                    ControlSocketClient controlSocketClient = new ControlSocketClient();
                    controlSocketClient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);// usando o executeOnExecutor para rodar multiplas AsyncTask

                    } catch (Exception e) {
                        Toast.makeText(this.main_Activity, "Erro ao enviar status\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        this.main_Activity.unregisterSensorManager();
                        e.printStackTrace();

                    }
                }

                this.cont = 0;

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.main_Activity, "Erro ao enviar dado para Arduino\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                this.main_Activity.unregisterSensorManager();

            }
        }

        if (this.direction_old != this.main_Activity.getDirection()) {
            try {
                SerialManagemant.getUniqueSerialManagemant(main_Activity).sendToArduino(this.main_Activity.getDirection() + "c");
                this.direction_old = this.main_Activity.getDirection();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.main_Activity, "Erro ao enviar dado para Arduino\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                this.main_Activity.unregisterSensorManager();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private int mapValueToAngle(float x, float input_min, float input_max, int output_min, int output_max) {
        int angle = (int) ((x - input_min) * (output_max - output_min) / (input_max - input_min) + output_min);

        return angle;
    }
}
