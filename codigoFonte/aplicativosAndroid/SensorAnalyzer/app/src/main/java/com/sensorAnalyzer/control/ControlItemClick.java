package com.sensorAnalyzer.control;

import android.content.Intent;
import android.view.View;

import com.sensorAnalyzer.model.SensorItemVO;
import com.sensorAnalyzer.view.SensorActivity;


/**
 * Created by leonardo on 13/05/15.
 */
public class ControlItemClick implements View.OnClickListener {

    private SensorItemVO sensorItemVO;

    public ControlItemClick(SensorItemVO sensorItemVO) {
        this.sensorItemVO = sensorItemVO;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
//        MainActivity.setCurrentTypeSensor(this.sensorItemVO.getType());

        Intent intent = new Intent(v.getContext(), SensorActivity.class);
        intent.putExtra("sensorAtual", this.sensorItemVO.getType());
        v.getContext().startActivity(intent);

    }
}
