package com.mihua.frameproject.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mihua.code.utils.ToastUtils;
import com.mihua.frameproject.R;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class BlueToothActivity extends AppCompatActivity {


    private static final int REQUEST_ENABLE_BT = 1;

    @BindView(R.id.bt_search_bluetooth)
    Button mBtSearchBluetooth;
    @BindView(R.id.recycler_view)
    ListView mRecyclerView;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> mList;

    private final UUID MY_UUID = UUID
            .fromString("abcd1234-ab12-ab12-ab12-abcdef123456");//随便定义一个


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        ButterKnife.bind(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            ToastUtils.showShortToast("该设备不支持蓝牙");
            finish();
            return;
        }

        mList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);
        mRecyclerView.setAdapter(mArrayAdapter);

        boolean enabled = mBluetoothAdapter.isEnabled();
        Logger.d("isEnabled"+enabled);
        if (!enabled){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        if (bondedDevices.size()>0){
            for (BluetoothDevice bondedDevice : bondedDevices) {

                mList.add(bondedDevice.getName() + "\n" + bondedDevice.getAddress());
                mArrayAdapter.notifyDataSetChanged();
            }
        }

        boolean enable1 = mBluetoothAdapter.enable();
        Logger.d("enable"+enable1);
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy


        new RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            mBluetoothAdapter.cancelDiscovery();
                            mBluetoothAdapter.startDiscovery();
                        }else {
                            ToastUtils.showShortToast("没有权限");
                        }
                    }
                });



        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = mList.get(position);
                String[] split = s.split("\n");
                String macAddress = split[1];
                Logger.d(macAddress);
                BluetoothDevice remoteDevice = mBluetoothAdapter.getRemoteDevice(macAddress);
                ConnectThread connectThread = new ConnectThread(remoteDevice);
                Thread thread = new Thread(connectThread);
                thread.start();
            }
        });
    }

    @OnClick(R.id.bt_search_bluetooth)
    public void onViewClicked() {

        mBluetoothAdapter.cancelDiscovery();
        mBluetoothAdapter.startDiscovery();
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            Logger.d("收到"+action);
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                // Add the name and address to an array adapter to show in a ListView
                mList.add(device.getName() + "\n" + device.getAddress());
                mArrayAdapter.notifyDataSetChanged();
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){

            }

        }
    };

    @Override
    protected void onDestroy() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver=null;
        }
        super.onDestroy();
    }



    private class ConnectThread implements Runnable {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private OutputStream mOutputStream;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;
//            ParcelUuid[] uuids = mmDevice.getUuids();
//            Logger.d(uuids);
            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {

            Logger.d("连接");
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                Logger.d("连接111。。。。。");
                // 连接建立之前的先配对
                if (mmDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Method creMethod = BluetoothDevice.class.getMethod("createBond");
                    Log.i("BluetoothManager", "开始配对");
                    creMethod.invoke(mmDevice);
                }
//                mmSocket.connect();
//                mOutputStream = mmSocket.getOutputStream();

//            } catch (IOException connectException) {
//                // Unable to connect; close the socket and get out
//                try {
//                    Logger.d("连接异常。。。。。");
//                    mmSocket.close();
//                } catch (IOException closeException) { }
//                return;
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            // Do work to manage the connection (in a separate thread)
//            manageConnectedSocket(mmSocket);
        }

//        private void manageConnectedSocket(BluetoothSocket mmSocket) {
//
//        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }


}
