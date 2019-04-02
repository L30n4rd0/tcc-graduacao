package com.sensorAnalyzer.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.sensorAnalyzer.R;
import com.sensorAnalyzer.model.SensorItemVO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensor_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> deviceSensors = sensor_manager.getSensorList(Sensor.TYPE_ALL);

        ArrayList<SensorItemVO> listSensors = new ArrayList<SensorItemVO>();

        String numberOfSensores = "Total de sensores encontrados: " + deviceSensors.size() + "\nInformações dos Sensores";

        for (Sensor sensor : deviceSensors) {

            SensorItemVO sensor_item = new SensorItemVO(sensor.getName(), sensor.getType(), sensor.getVendor() + "\n...................................\n");

            listSensors.add(sensor_item);

        }

        TextView textViewNumberOfSensors = (TextView) findViewById(R.id.textViewNumberOfSensors);
        textViewNumberOfSensors.setText(numberOfSensores);

        ListAdapterItem listAdapterItem = new ListAdapterItem(this, 0, listSensors);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapterItem);


    }
}
