package com.mihua.frameproject.socket.socketbroadcast;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mihua.frameproject.R;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/05/28
 *     desc   :
 * </pre>
 */
public class SocketWifiBroadcastActivity extends AppCompatActivity {

    @BindView(R.id.consoleText)
    TextView mConsoleText;
    @BindView(R.id.demoScroller)
    ScrollView mDemoScroller;


    private InetAddress mPort;
    private String mOrderInfo;

    @BindView(R.id.et_money)
    EditText mEtMoney;
    private String mHostAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_socket_broadcast);
        ButterKnife.bind(this);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                startUdpServer();
            }
        };
        thread.start();

        mEtMoney.setFilters(new InputFilter[]{new MoneyInputFilter(1000)});

//        Thread thread1 = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                startTcpServer();
//            }
//        };
//        thread1.start();

    }

    private void startTcpServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(6300);
            System.out.println("等待socket连接");
            Socket socket = serverSocket.accept();

            System.out.println("socket连接成功");

            readWriteDataToClient(socket);
//            SocketTask socketTask = new SocketTask(socket);
//            ThreadPoolManager.getInstance().execute(new FutureTask(socketTask,null));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    private void readWriteDataToClient(Socket socket) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            if (!s.isEmpty()) {
                System.out.println("收到数据" + s);
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String s1 = "我是TcpSocket服务端";
            bufferedWriter.write(s1, 0, s1.length());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void startUdpServer() {

        byte[] buffer = new byte[1024 * 10];
        /*在这里同样使用约定好的端口*/
        int port = 12811;
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(port);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                try {
                    server.receive(packet);
                    final String s = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    mHostAddress = packet.getAddress().getHostAddress();


                    System.out.println("address : " + packet.getAddress().getHostAddress() + ", port : " + packet.getPort() + ", content : " + s);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final String message = "  "+s + "\n";

                            dealWithData(s);

                            mConsoleText.append(message);
                            mDemoScroller.smoothScrollTo(0, mConsoleText.getBottom());
                        }
                    });
//                startConnectServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if (server != null)
                server.close();
        }

    }

    private void dealWithData(String s) {

        if (!TextUtils.isEmpty(s)) {
            OrderInfo orderInfo = new Gson().fromJson(s, OrderInfo.class);
            int code = orderInfo.getCode();

        }

    }

    private void startConnectServer() {

        if (mPort != null) {
            OutputStream outputStream = null;
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            try {
                Socket socket = new Socket(mPort, 6300);
                Log.d("result", "客户端线程" + Thread.currentThread().toString());
                Log.d("result", socket.getInetAddress().getHostName() + "," + socket.getInetAddress().getHostAddress());
                outputStream = socket.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String s = "我是客户端\r\n";
                bufferedWriter.write(s, 0, s.length());
                bufferedWriter.flush();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("result", "客户端收到的消息" + (new StringBuffer().append(line)).toString());
                }
            } catch (IOException e) {
                Log.d("message", e.getMessage());
                e.printStackTrace();

            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    public  void sendBroadcastToCenter(final String data){

        if (mHostAddress == null) {
            WifiManager wifiMgr = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

        /*这里获取了IP地址，获取到的IP地址还是int类型的。*/
            int ip = wifiInfo.getIpAddress();

            //这里就是将int类型的IP地址通过工具转化成String类型的，便于阅读
            String ips = Formatter.formatIpAddress(ip);

        /*这一步就是将本机的IP地址转换成xxx.xxx.xxx.255*/
            int broadCastIP = ip | 0xFF000000;
            mHostAddress = Formatter.formatIpAddress(broadCastIP);
        }


        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                DatagramSocket theSocket = null;
                try {
                    InetAddress server = InetAddress.getByName(mHostAddress);

                    theSocket = new DatagramSocket();
                    DatagramPacket theOutput = new DatagramPacket(data.getBytes(), data.getBytes().length, server, 8000);
                /*这一句就是发送广播了，其实255就代表所有的该网段的IP地址，是由路由器完成的工作*/
                    theSocket.send(theOutput);

//                    Logger.d("发送数据"+data);
//            startUdpServer();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (theSocket != null)
                        theSocket.close();
                }
            }
        };
        thread.start();
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.cashPay:
                String trim = mEtMoney.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(this, "请输入收银金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(trim) < 1) {
                    Toast.makeText(this, "请输入正确的收银金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendCommandCash(1, "现金收银", trim);

                break;
            case R.id.continuePay:
                sendCommandCash(2, "继续找零", null);
                break;
            case R.id.restart:
                sendCommandCash(3, "重新找零", null);
                break;

        }


//        mOrderInfo = getOrderInfo(true);
//
//        Logger.d(mOrderInfo);

    }

    private void sendCommandCash(int code, String msg, String cash) {

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setCode(code);
        commandInfo.setMsg(msg);
        if (cash != null) {
            CommandInfo.DataBean dataBean = new CommandInfo.DataBean();
            dataBean.setNeedPayMoney(cash);
            commandInfo.setData(dataBean);
        }
        String s = new Gson().toJson(commandInfo);
        sendBroadcastToCenter(s);
    }

    private String getOrderInfo(boolean b) {
        OrderInfo orderInfo = new OrderInfo();
        if (b) {
            orderInfo.setCode(0);
            orderInfo.setMsg("交易成功");
            OrderInfo.DataBean dataBean = new OrderInfo.DataBean();
            dataBean.setNeedPayMoney(2);
            dataBean.setHasPayMoney(2);
            dataBean.setNeedPayoutMoney(2);
            dataBean.setHasPayoutMoney(2);
            dataBean.setUnPayoutMoney(2);
            orderInfo.setData(dataBean);
        } else {
            orderInfo.setCode(-1);
            orderInfo.setMsg("交易失败");
        }
        String json = new Gson().toJson(orderInfo);

        return json;
    }

    public void onClick1(View view) {
        if (mOrderInfo != null) {

            OrderInfo orderInfo = new Gson().fromJson(mOrderInfo, OrderInfo.class);
            Logger.d(orderInfo);

        }

    }
}
