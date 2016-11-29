package netmind.com.practicasensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "In Main Activity";

    private SensorManager mSensorManager;
    private Sensor mLight, mAccelerometer;
    private RelativeLayout mMainLayout;
    private long lastSensorUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMainLayout = (RelativeLayout) this.findViewById(R.id.relLayoutMain);


        this.mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> myDeviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        String mSensorString = "";
        for (Sensor aSensor : myDeviceSensors)
            mSensorString += aSensor.getName() + "\n";

        Log.i(TAG, mSensorString);



    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mSensorManager.registerListener(this, this.mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mSensorManager.unregisterListener(this, this.mLight);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long evenTime = event.timestamp;
        Toast.makeText(this, String.valueOf(evenTime), Toast.LENGTH_SHORT).show();

        if (event.sensor.getType()==Sensor.TYPE_LIGHT){
            float[] lightSensorValues = event.values;
            for(float aValue : lightSensorValues)

            Log.i(MainActivity.TAG, String.valueOf(aValue));

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float[] accSensorValues = event.values;
            float x = accSensorValues[0]; float y = accSensorValues[1]; float z = accSensorValues[2];
            float accTimesG = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            if (accTimesG > 5) && (evenTime


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
