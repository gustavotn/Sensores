package com.uniftec.sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView ativo;
    private ImageView desativado;
    private TextView valorProximidade;
    private TextView valorLuminosidade;
    private ConstraintLayout layout;

    private SensorManager mSensorManager;
    private Sensor mProximidade;
    private Sensor mLuminosidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ativo = (ImageView) findViewById(R.id.imageView);
        desativado = (ImageView) findViewById(R.id.imageView2);
        valorProximidade = (TextView) findViewById(R.id.textView);
        valorLuminosidade = (TextView) findViewById(R.id.textView2);
        layout = (ConstraintLayout) findViewById(R.id.layout);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            mProximidade = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            mLuminosidade = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        mSensorManager.registerListener(new SensorProximidade(), mProximidade, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(new SensorLuminosidade(), mLuminosidade, SensorManager.SENSOR_DELAY_UI);
    }

    class SensorProximidade implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            valorProximidade.setText(String.valueOf(x));

            if (x < 6)
            {
                ativo.setVisibility(View.VISIBLE);
                desativado.setVisibility(View.INVISIBLE);
            }
            else
            {
                ativo.setVisibility(View.INVISIBLE);
                desativado.setVisibility(View.VISIBLE);
            }
        }
    }

    class SensorLuminosidade implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            valorLuminosidade.setText(String.valueOf(x));

            if (x < 10000)
                layout.setBackgroundColor(Color.GRAY);
            else
                layout.setBackgroundColor(Color.WHITE);
        }
    }
}