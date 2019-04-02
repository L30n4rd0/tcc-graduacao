package com.sensorAnalyzer.control;

import android.view.View;

import com.sensorAnalyzer.view.SensorActivity;

/**
 * Created by leonardo on 11/11/15.
 */
public class ControlStartStop implements View.OnClickListener{

    private SensorActivity sensorActivity;

    public ControlStartStop(SensorActivity sensorActivity) {
        this.sensorActivity = sensorActivity;
    }


    @Override
    public void onClick(View v) {
        if (this.sensorActivity.getButton_startStop().getText().toString().equals("Parar"))
            this.sensorActivity.unregisterSensorManager();

        else
            this.sensorActivity.registerSensorManager();

    }
}
