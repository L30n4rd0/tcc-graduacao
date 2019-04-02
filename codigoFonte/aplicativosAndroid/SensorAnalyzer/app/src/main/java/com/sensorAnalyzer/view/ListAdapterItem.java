package com.sensorAnalyzer.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sensorAnalyzer.R;
import com.sensorAnalyzer.control.ControlItemClick;
import com.sensorAnalyzer.model.SensorItemVO;

import java.util.ArrayList;

/**
 * Created by leonardo on 12/05/15.
 */
public class ListAdapterItem extends ArrayAdapter<SensorItemVO> {

    private Context context;
    private int resource;
    private ArrayList<SensorItemVO> list;

    public ListAdapterItem(Context context, int resource, ArrayList<SensorItemVO> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SensorItemVO sensor_item = this.list.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_layout,null);

        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewSensorName)
                ;
        textViewName.setText(textViewName.getText() + sensor_item.getName());

        TextView textViewType = (TextView) convertView.findViewById(R.id.textViewTypeSensor);
        textViewType.setText(textViewType.getText() + "" + sensor_item.getType());

        TextView textViewManufacturer = (TextView) convertView.findViewById(R.id.textViewSensorManufacturer);
        textViewManufacturer.setText(textViewManufacturer.getText() + sensor_item.getManufacturer());

        convertView.setOnClickListener(new ControlItemClick(sensor_item));

        return convertView;
    }
}
