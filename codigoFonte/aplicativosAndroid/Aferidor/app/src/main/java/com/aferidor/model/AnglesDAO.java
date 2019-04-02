package com.aferidor.model;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by leonardo on 12/11/15.
 */
public class AnglesDAO extends AsyncTask{

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void saveCoordinate (AnglesVO anglesVO, String nameFile) throws Exception{

        String lineWrite;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/storage/emulated/0/" + nameFile + ".csv", true));

        lineWrite = dateFormat.format(Calendar.getInstance().getTime()) + ";" + anglesVO.getX() + ";" + anglesVO.getY();
        lineWrite = lineWrite.replace('.',',');

        bufferedWriter.write(lineWrite);
        bufferedWriter.newLine();

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        switch ( (String) params[0]){
            case "write":
                try {
                    this.saveCoordinate( (AnglesVO) params[1], (String) params[2] );

                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        e.printStackTrace(new PrintStream(new File("/storage/emulated/0/erros_AnglesDAO.txt")));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                break;

            case "read":

                break;

            case "alter":

                break;
        }

        return null;
    }
}
