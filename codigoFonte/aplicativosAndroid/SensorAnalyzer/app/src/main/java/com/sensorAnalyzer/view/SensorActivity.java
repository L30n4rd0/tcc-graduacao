package com.sensorAnalyzer.view;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.sensorAnalyzer.R;
import com.sensorAnalyzer.control.ControlSensorEvent;
import com.sensorAnalyzer.control.ControlStartStop;

public class SensorActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private ControlSensorEvent controlSensorEvent;
    private TextView textView_x_axis, textView_y_axis, textView_sensorName;
    private Button button_startStop;
    private EditText editText_nameFile, editText_IPToSendStatus;
    private Switch switch_sendStatus, switch_saveDatas;
    private int currentSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        this.currentSensor = getIntent().getExtras().getInt("sensorAtual");

        this.textView_x_axis = (TextView) findViewById(R.id.X_value);
        this.textView_y_axis = (TextView) findViewById(R.id.Y_value);
        this.textView_sensorName = (TextView) findViewById(R.id.sensor_name);
        this.textView_sensorName.setText(this.sensorManager.getDefaultSensor(this.currentSensor).getName());

        this.editText_nameFile = (EditText) findViewById(R.id.editText_nameFile);
        this.editText_IPToSendStatus = (EditText) findViewById(R.id.editText_IPToSendStatus);

        this.button_startStop = (Button) findViewById(R.id.button_startStop);
        this.button_startStop.setOnClickListener(new ControlStartStop(this));

        this.switch_saveDatas = (Switch) findViewById(R.id.switch_saveDatas);
        this.switch_sendStatus = (Switch) findViewById(R.id.switch_sendStatus);

        this.controlSensorEvent = new ControlSensorEvent(this);

    }

    public TextView getTextView_x_axis() {
        return textView_x_axis;
    }

    public TextView getTextView_y_axis() {
        return textView_y_axis;
    }

    public TextView getTextView_sensorName() {
        return textView_sensorName;
    }

    public Button getButton_startStop() {
        return button_startStop;
    }

    public EditText getEditText_nameFile() {
        return editText_nameFile;
    }

    public EditText getEditText_IPToSendStatus() {
        return editText_IPToSendStatus;
    }

    public Switch getSwitch_sendStatus() {
        return switch_sendStatus;
    }

    public Switch getSwitch_saveDatas() {
        return switch_saveDatas;
    }

    public void registerSensorManager() {
        sensorManager.registerListener(this.controlSensorEvent, sensorManager.getDefaultSensor(this.currentSensor), SensorManager.SENSOR_DELAY_FASTEST);
        this.button_startStop.setText("Parar");
        this.button_startStop.setBackgroundColor(Color.parseColor("#83BE2136"));

    }

    public void unregisterSensorManager() {
        sensorManager.unregisterListener(this.controlSensorEvent);
        this.button_startStop.setText("Iniciar");
        this.button_startStop.setBackgroundColor(Color.parseColor("#833544ed"));

    }
}
