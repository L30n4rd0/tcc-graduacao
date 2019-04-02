package com.aferidor.model;

import android.content.Context;
import android.hardware.usb.UsbManager;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;

/**
 * Created by leonardo on 02/09/15.
 */
public class SerialManagemant {
    private UsbManager usbManager;
    private UsbSerialDriver usbSerialDriver;
    private Context context;
    private static SerialManagemant UNIQUESERIAL_MANAGEMANT = null;


    private SerialManagemant (Context context) {
        this.context = context;
        this.usbManager = (UsbManager) this.context.getSystemService(Context.USB_SERVICE);

    }

    public static SerialManagemant getUniqueSerialManagemant (Context context){

        if (UNIQUESERIAL_MANAGEMANT == null)
            UNIQUESERIAL_MANAGEMANT = new SerialManagemant(context);

        return UNIQUESERIAL_MANAGEMANT;
    }

    public void sendToArduino(String data) throws Exception {
        byte[] dataToSend = data.getBytes();

        //send the datas to the serial device
        if (this.usbSerialDriver != null){
            this.usbSerialDriver.write(dataToSend, 500);

        }
    }

    public void readFromToArduino(String data) throws Exception {

    }

    public void openUSBConnection () throws Exception {
        //get a USB to Serial device object
        this.usbSerialDriver = UsbSerialProber.acquire(this.usbManager);

        //open the device
        this.usbSerialDriver.open();

    }

    public void startUSBConnection () throws Exception {

        //set the communication speed
        //make sure this matches your device's setting!
//        this.usbSerialDriver.setBaudRate(1920000);
//        this.usbSerialDriver.setBaudRate(115200);
        this.usbSerialDriver.setBaudRate(9600);
    }

    public void closeUSBConnection () throws Exception {

        //close the device
        this.usbSerialDriver.close();
        this.usbSerialDriver = null;

    }

}
