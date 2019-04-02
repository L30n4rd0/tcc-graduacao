package com.aferidor.view;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.aferidor.control.ControlSensorEvent;
import com.aferidor.control.ControlSerialManagemant;
import com.aferidor.R;
import com.aferidor.control.ControlServerStartStop;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends Activity {

    private ControlSerialManagemant controlSerialManagemant;
    private ControlSensorEvent controlSensorEvent;
    private SensorManager sensorManager;
    private TextView textView_serverStatus, textView_serverIP, textView_angleXValue, textView_angleYValue;
    private Button button_serverStartStop;
    private int direction;
    private boolean dataSave, sendStatus;
    private String ipSendStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.direction = 11;
        this.dataSave = false;
        this.sendStatus = false;
        this.ipSendStatus = null;

        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        this.controlSerialManagemant = new ControlSerialManagemant(this);
        this.controlSensorEvent = new ControlSensorEvent(this);

        this.textView_serverStatus = (TextView) findViewById(R.id.textView_serverStatus);
        this.textView_serverIP = (TextView) findViewById(R.id.textView_severIP);
        this.textView_serverIP.setText("IP: " + getIp());
        this.textView_angleXValue = (TextView) findViewById(R.id.textView_angleXValue);
        this.textView_angleYValue = (TextView) findViewById(R.id.textView_angleYValue);

        this.button_serverStartStop = (Button) findViewById(R.id.button_serverStartStop);
        this.button_serverStartStop.setOnClickListener(new ControlServerStartStop(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        this.controlSerialManagemant.openUSBConnection();
        this.controlSerialManagemant.startUSBConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.controlSerialManagemant.closeUSBConnection();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public TextView getTextView_serverStatus() {
        return this.textView_serverStatus;
    }

    public TextView getTextView_angleXValue() {
        return textView_angleXValue;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setDataSave(boolean dataSave) {
        this.dataSave = dataSave;
    }

    public boolean isDataSave() {
        return dataSave;
    }

    public boolean isSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public TextView getTextView_angleYValue() {
        return textView_angleYValue;
    }

    public Button getButton_serverStartStop() {
        return button_serverStartStop;
    }

    public String getIpSendStatus() {
        return ipSendStatus;
    }

    public void setIpSendStatus(String ipSendStatus) {
        this.ipSendStatus = ipSendStatus;
    }

    public void registerSensorManager() {
        sensorManager.registerListener(controlSensorEvent, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void unregisterSensorManager() {
        sensorManager.unregisterListener(controlSensorEvent);

    }

    public String getIp() {
        String ipAddress = null;
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();

                if (ip.isSiteLocalAddress()) {
                    ipAddress = ip.getHostAddress();
                }
            }
        }
        return ipAddress;
    }
}
