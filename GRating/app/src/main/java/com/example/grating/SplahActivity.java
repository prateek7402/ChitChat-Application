package com.example.grating;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplahActivity extends AppCompatActivity implements SensorEventListener {
    ListView listView;
    SensorManager sensorManager;
    ArrayList<String> listtt = new ArrayList<>();
    Sensor lightSensor, proxySensor, accSensor, magSensor, tempSensor;
    TextView textView;
    float[] accarr = new float[3];
    float[] magarr = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        listView = findViewById(R.id.lv1);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // listtt = new StringBuilder();
        textView = findViewById(R.id.txt1);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proxySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensorList) {
            String s1 = "Sensor name:" + s.getName() + "\nsensor vendor:" + s.getVendor();
            listtt.add(s1 + "\n");
        }

        ArrayAdapter<String> m = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listtt);
        listView.setAdapter(m);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) getApplicationContext());
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                break;
            case Sensor.TYPE_PROXIMITY:
                float[] value = sensorEvent.values;
                Toast.makeText(this, "Distance is " + value[0], Toast.LENGTH_SHORT).show();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magarr = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accarr = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                break;
        }
        float[] rotationMatrix = new float[9];

        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix, null, accarr, magarr);

        if (rotationOK) {
            float orientation[] = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientation);
            float azimut = orientation[0];
            float pitch = orientation[1];
            float roll = orientation[2];

            textView.setText("azimut is : " + azimut + "\npitch is : " + pitch + "\nroll" + roll);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (lightSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proxySensor != null) {
            sensorManager.registerListener((SensorEventListener) this, proxySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (tempSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (accSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (magSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
