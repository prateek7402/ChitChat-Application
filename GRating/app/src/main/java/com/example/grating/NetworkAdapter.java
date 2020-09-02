package com.example.grating;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class NetworkAdapter extends AppCompatActivity {
    ConnectivityManager connectivityManager;
    BluetoothAdapter bluetoothAdapter;
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_adapter);
        b = findViewById(R.id.bt1);
        t = findViewById(R.id.txt1);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter intentFilter1 = new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        registerReceiver(mreceiver1,intentFilter1);

        IntentFilter intentFilter2 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mreceiver2,intentFilter2);
    }

    BroadcastReceiver mreceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             String action = intent.getAction();
             Toast.makeText(context, "Receiver 1", Toast.LENGTH_SHORT).show();

             if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action))
             {
                 int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
                 switch(state)
                 {
                     case BluetoothAdapter.STATE_OFF:
                         Toast.makeText(context, "Bluetooth off", Toast.LENGTH_SHORT).show();
                         break;
                     case BluetoothAdapter.STATE_TURNING_OFF:
                         Toast.makeText(context, "Bluetooth turning off", Toast.LENGTH_SHORT).show();
                         break;
                     case BluetoothAdapter.STATE_ON:
                         Toast.makeText(context, "Bluetooth on", Toast.LENGTH_SHORT).show();
                         break;
                     case BluetoothAdapter.STATE_TURNING_ON:
                         Toast.makeText(context, "Bluetooth turning on", Toast.LENGTH_SHORT).show();
                         break;
                 }
             }
        }
    };

    BroadcastReceiver mreceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receiver 2", Toast.LENGTH_SHORT).show();
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = bluetoothDevice.getName();
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public  void GetStatus(View v1)
    {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
        {
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
            }
            if(networkInfo.getType() == connectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(this, "MOBILE NETWORK", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mreceiver1);
        unregisterReceiver(mreceiver2);
        bluetoothAdapter.cancelDiscovery();
    }

    public void Find(View v2)
    {
        if(bluetoothAdapter!=null)
        {
            Toast.makeText(this, "Starting finding devices", Toast.LENGTH_SHORT).show();
            bluetoothAdapter.startDiscovery();
        }
    }

    public void Blue(View v3)
    {
        if(!bluetoothAdapter.isEnabled())
        {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,1);
            b.setBackgroundResource(R.color.colorPrimary);
            Toast.makeText(this, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
        }
        if(bluetoothAdapter.isEnabled())
        {
           bluetoothAdapter.disable();
           Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
           b.setBackgroundResource(R.color.colorAccent);
        }
    }

    public void Discoverable(View v4)
    {
        if(bluetoothAdapter!=null)
        {
            Intent discInt = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discInt.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,400);
            startActivityForResult(discInt,2);
            Toast.makeText(this, "made discoverable for 400 second", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Already Discoverable", Toast.LENGTH_SHORT).show();
        }
    }

    public void Paired(View v6)
    {
        StringBuilder str = new StringBuilder();
        if(bluetoothAdapter!=null)
        {
            Set<BluetoothDevice> bl = bluetoothAdapter.getBondedDevices();
            if(bl.size()>0)
            {
                for(BluetoothDevice device : bl)
                {
                     str.append(device+"\n");
                }
            }
        }
        t.setText(str);
    }
}
