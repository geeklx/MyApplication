package com.example.p010_recycleviewall.bluetoothold;

import android.Manifest;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.bluetoothold.bluecommon.bean.BlueDevice;
import com.example.p010_recycleviewall.bluetoothold.bluecommon.utils.BlueUtils;
import com.example.p010_recycleviewall.utils.SpUtils;
import com.example.p010_recycleviewall.utils.ToastUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by shining on 2017/3/6 0006.
 */

public class MainActivity_BlueTooth extends AppCompatActivity implements View.OnClickListener {

    /**
     * 自定义返回码
     */
    private static final int MY_PERMISSION_REQUEST_CONSTANT = 123456;
    private static final int ENABLE_BLUE = 1001;
    private static final int SEARCH_BLUE = 1002;
    private static final int BLUE_TIME = 120;
    private static final int BLUE_yipeidui = -1;
    private static final int BLUE_yilianjie = 2;
    //这条是蓝牙串口通用的UUID，不要更改
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "20:16:07:26:18:46"; // <==要连接的目标蓝牙设备MAC地址
    private int pos;
    private int a2dp_state = -1;
    /**
     * 蓝牙音频传输协议
     */
    private BluetoothA2dp a2dp;
    /**
     * 需要连接的蓝牙设备
     */
    private BluetoothDevice currentBluetoothDevice;
    private String currentBluetoothDevice_address = "";

    private TextView tv_o_c;
    private TextView tv_de1;
    private TextView tv_de2;
    private TextView tv_de3;
    private LinearLayout ll_ceng1;
    private Button button_id;
    private ScrollView sll;

    private RecyclerView recyclerView1;
    private RecycleAdapterbluetooth1 mAdapter1;
    private List<BluetoothDevice> mList1;
    private List<BlueDevice> mBlueDevice1 = new ArrayList<>();

    private RecyclerView recyclerView2;
    private RecycleAdapterbluetooth2 mAdapter2;
    private List<BluetoothDevice> mList2;
    private List<BlueDevice> mBlueDevice2;

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        if (a2dp != null) {
            a2dp = null;
        }
        if (mProfileServiceListener != null) {
            mProfileServiceListener = null;
        }
    }

    /**
     * 请求权限的回调：这里判断权限是否添加成功
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CONSTANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("main", "添加权限成功");
                }
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclelistview_bluetooth_old);
        /**判断手机系统的版本*/
        Log.d("MainActivity", Build.VERSION.SDK_INT + "");
        if (Build.VERSION.SDK_INT >= 6.0) {//Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                /**动态添加权限：ACCESS_FINE_LOCATION*/
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST_CONSTANT);
            }
        }
        findView();
        addlisteners();
        //初始化BLUE
        set_blue();
    }

    private void set_blue() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            open_blue();
        } else {
            close_blue();
        }
    }

    private void open_blue() {
        if (mBluetoothAdapter == null) {
            ToastUtil.showToastCenter("未找到蓝牙设备!");
            return;
        }
        tv_o_c.setText("关闭蓝牙");
        sll.setVisibility(View.VISIBLE);
        button_id.setVisibility(View.VISIBLE);
        tv_de1.setText(mBluetoothAdapter.getName());
        Data1();
        Data2();
    }

    private void close_blue() {
        /**关闭蓝牙*/
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
        tv_o_c.setText("打开蓝牙");
        sll.setVisibility(View.GONE);
        button_id.setVisibility(View.GONE);
        SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", "");
        currentBluetoothDevice_address = "";
    }

    /**
     * 取消配对bufen
     *
     * @param listItem
     */
    private void shezhi_cancel_peidui(final BlueDevice listItem) {
        if (listItem.getDevice().getBondState() != BluetoothDevice.BOND_BONDED) {
            /**还没有配对*/
        } else {
            /**完成配对的*/
            showDailogs("是否取消" + listItem.getName() + "配对？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    BlueUtils.unpairDevice(listItem.getDevice());
                }
            });
        }
    }

    /**
     * 取消配对的连接bufen
     *
     * @param listItem
     */
    private void shezhi_cancel_connect(final BlueDevice listItem) {
        showDailogs("是否取消与设备" + listItem.getName() + "连接？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //取消连接bufen
                discontectBuleDevices(listItem);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        a2dp_state = -1;
                        SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", "");
                        currentBluetoothDevice_address = "";
                        Data1();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 配对连接bufen
     *
     * @param listItem
     */
    private void shezhi_connect_peidui(final BlueDevice listItem) {
        /**还没有配对*/
        String msg = "";
        if (listItem.getDevice().getBondState() != BluetoothDevice.BOND_BONDED) {
            msg = "是否与设备" + listItem.getName() + "配对并连接？";
        } else {
            msg = "是否与设备" + listItem.getName() + "连接？";
        }
        showDailogs(msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /**当前需要配对的蓝牙设备*/
                currentBluetoothDevice = listItem.getDevice();
                /**还没有配对*/
                if (listItem.getDevice().getBondState() != BluetoothDevice.BOND_BONDED) {
                    startPariBlue(listItem);
                } else {
                    /**完成配对的,直接连接*/
                    contectBuleDevices();
                }
//                        showProgressDailog();
            }
        });
    }

    /**
     * 排行连接的媒体bufen
     *
     * @param mBlueDevice1
     * @return
     */
    private List<BlueDevice> shezhi_ranking(List<BlueDevice> mBlueDevice1) {
        List<BlueDevice> bd_list = new ArrayList<>();
        for (BlueDevice bd : mBlueDevice1) {
            if (currentBluetoothDevice != null && bd.getAddress().equals(currentBluetoothDevice.getAddress())) {
                bd_list.add(0, bd);
            } else {
                bd_list.add(bd);
            }
        }
        return bd_list;
    }

    private void findView() {
        tv_o_c = (TextView) findViewById(R.id.tv_o_c);
        tv_de1 = (TextView) findViewById(R.id.tv_de1);
        tv_de2 = (TextView) findViewById(R.id.tv_de2);
        tv_de3 = (TextView) findViewById(R.id.tv_de3);
        ll_ceng1 = (LinearLayout) findViewById(R.id.ll_ceng1);
        button_id = (Button) findViewById(R.id.button_id);
        sll = (ScrollView) findViewById(R.id.sll);

        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view11);

        mAdapter1 = new RecycleAdapterbluetooth1(this);
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
        mLinearLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        recyclerView1.setLayoutManager(mLinearLayoutManager1);
        recyclerView1.setAdapter(mAdapter1);

        mAdapter2 = new RecycleAdapterbluetooth2(this);
        LinearLayoutManager mLinearLayoutManager2 = new LinearLayoutManager(this);
        mLinearLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        recyclerView2.setLayoutManager(mLinearLayoutManager2);
        recyclerView2.setAdapter(mAdapter2);
    }

    private void addlisteners() {
        tv_o_c.setOnClickListener(this);
        ll_ceng1.setOnClickListener(this);
        button_id.setOnClickListener(this);

        mAdapter1.setOnItemClickLitener(new RecycleAdapterbluetooth1.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, int tpye) {
                final BlueDevice listItem = (BlueDevice)
                        mAdapter1.getItem(position);
                //未配对连接部分
                address = listItem.getAddress();
                if (tpye == -1) {
                    if (currentBluetoothDevice_address.equals("")) {
                        //已配对连接bufen
                        //判断设备是否开启bufen

                        shezhi_connect_peidui(listItem);
                    } else {
                        ToastUtil.showToastCenter("请先断开其他连接设备！");
                    }
                } else if (tpye == 2) {
                    shezhi_cancel_connect(listItem);
                }
            }
        });

        mAdapter1.setOnLongItemClickLitener(new RecycleAdapterbluetooth1.OnLongItemClickLitener() {
            @Override
            public void onLongItemClick(View view, int position) {
                final BlueDevice listItem = (BlueDevice)
                        mAdapter1.getItem(position);
                //已配对连接部分
                address = listItem.getAddress();
                shezhi_cancel_peidui(listItem);
            }
        });

        mAdapter2.setOnItemClickLitener(new RecycleAdapterbluetooth2.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                final BlueDevice listItem = (BlueDevice)
                        mAdapter2.getItem(position);
                address = listItem.getAddress();
                pos = position;
                //TODO 先上线 后修改 new
                Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
                if (devices.size() == 1) {
                    ToastUtil.showToastCenter("请先取消已配对设备");
                } else {
                    shezhi_connect_peidui(listItem);
                }
//                shezhi_connect_peidui(listItem);
            }
        });

//        mAdapter2.setOnLongItemClickLitener(new RecycleAdapterbluetooth2.OnLongItemClickLitener() {
//            @Override
//            public void onLongItemClick(View view, int position) {
//                final BlueDevice listItem = (BlueDevice)
//                        mAdapter2.getItem(position);
//                shezhi_cancel_peidui(listItem);
//            }
//        });

    }

    /**
     * 开始配对蓝牙设备
     *
     * @param blueDevice
     */
    private void startPariBlue(BlueDevice blueDevice) {
        BlueUtils blueUtils = new BlueUtils(blueDevice);
        blueUtils.doPair();
    }

    /**
     * 取消配对蓝牙设备
     *
     * @param blueDevice
     */
    private void stopPariBlue(BlueDevice blueDevice) {
        BlueUtils.unpairDevice(blueDevice.getDevice());
    }

    /**
     * 断开已配对连接蓝牙设备
     */
    private void discontectBuleDevices(BlueDevice blueDevice) {
        /**断开A2DP协议连接设备*/
        try {
            disconnect(a2dp.getClass(), a2dp, blueDevice.getDevice());
            mBluetoothAdapter.closeProfileProxy(BluetoothProfile.A2DP, a2dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始连接蓝牙设备
     */
    private void contectBuleDevices() {
        /**使用A2DP协议连接设备*/
        mBluetoothAdapter.getProfileProxy(this, mProfileServiceListener, BluetoothProfile.A2DP);
    }

    private void Data1() {
        if (mBlueDevice1 != null || mBlueDevice1.size() > 0) {
            mBlueDevice1.clear();
        }
        // 获取所有已经绑定的蓝牙设备
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> mList1 = new ArrayList<>();
        if (devices.size() > 0) {
            mList1 = new ArrayList<>(devices);
        }
        for (int i = 0; i < mList1.size(); i++) {
            BlueDevice bd = new BlueDevice();
            currentBluetoothDevice_address = (String) SpUtils.getInstance(MainActivity_BlueTooth.this).get("a2dp_state", "");
            if (mList1.get(i).getAddress().equals(currentBluetoothDevice_address)) {
                bd.setStatus("已连接到媒体设备");
                bd.setType(BLUE_yilianjie);// 2
            } else {
                bd.setStatus("已配对");
                bd.setType(BLUE_yipeidui);
            }
            if (mList1.get(i).getName() == null || mList1.get(i).getName().equals("")) {
                bd.setName("未知设备");
            } else {
                bd.setName(mList1.get(i).getName());
            }
            bd.setAddress(mList1.get(i).getAddress());
//            Log.e("###", a2dp.getConnectionState(currentBluetoothDevice) + "");
            bd.setDevice(mList1.get(i));
            mBlueDevice1.add(bd);
        }
        //设置排位
        mAdapter1.setContacts(shezhi_ranking(mBlueDevice1));
        mAdapter1.notifyDataSetChanged();
    }

    private void Data2() {
        //new
        /**注册搜索蓝牙receiver*/
        IntentFilter mFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mFilter.addAction(BluetoothDevice.ACTION_FOUND);
        mFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mReceiver, mFilter);
        //old
//        // 注册用以接收到已搜索到的蓝牙设备的receiver
//        IntentFilter mFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        registerReceiver(mReceiver, mFilter);
//        // 注册搜索完时的receiver
//        mFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_o_c:
                //打开蓝牙
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent intent1 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent1, ENABLE_BLUE);
                    //不做提示，强行打开
                    // mAdapter.enable();
                } else {
                    close_blue();
                }
                break;
            case R.id.ll_ceng1:
                if (mBluetoothAdapter.getScanMode() == 21) {
                    //弹窗
//                    Intent intent = new Intent(
//                            BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//                    //设置蓝牙可见性的时间，方法本身规定最多可见300秒
//                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, BLUE_TIME);
//                    startActivityForResult(intent, SEARCH_BLUE);
                    //不弹窗
                    setDiscoverableTimeout(BLUE_TIME);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shezhi_blue_research(false);
                        }
                    }, BLUE_TIME * 1000);
                } else if (mBluetoothAdapter.getScanMode() == 23) {
                    shezhi_blue_research(false);
                }

                break;
            case R.id.button_id:
                setProgressBarIndeterminateVisibility(true);
                setTitle("正在扫描....");
//                mList2 = new ArrayList<>();
                mBlueDevice2 = new ArrayList<>();
                // 如果正在搜索，就先取消搜索
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }
                // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
                mBluetoothAdapter.startDiscovery();
                break;
            default:

                break;
        }

    }

    public void setDiscoverableTimeout(int timeout) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);
            setDiscoverableTimeout.invoke(adapter, timeout);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        shezhi_blue_research(true);
    }

    private CountDownTimer timer = new CountDownTimer(BLUE_TIME * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tv_de3.setBackgroundResource(R.drawable.foodmanager_choose2);
            tv_de2.setText(getResources().getString(R.string.tips2) + (millisUntilFinished / 1000) + "）");
        }

        @Override
        public void onFinish() {
            tv_de3.setBackgroundResource(R.drawable.foodmanager_choose1);
            tv_de2.setText(getResources().getString(R.string.tips1));
            closeDiscoverableTimeout();
        }
    };

    public void closeDiscoverableTimeout() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(adapter, 1);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENABLE_BLUE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "蓝牙开启成功", Toast.LENGTH_SHORT).show();
                open_blue();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "蓝牙开始失败", Toast.LENGTH_SHORT).show();
                close_blue();
            }
        }
        if (requestCode == SEARCH_BLUE) {
            if (resultCode == BLUE_TIME) {
                shezhi_blue_research(true);
            } else if (resultCode == RESULT_CANCELED) {
                shezhi_blue_research(false);
            }
        }

    }

    private void shezhi_blue_research(boolean is_search) {
        if (is_search) {
            timer.start();
        } else {
            tv_de2.setText(getResources().getString(R.string.tips1));
            tv_de3.setBackgroundResource(R.drawable.foodmanager_choose1);
            timer.cancel();
            closeDiscoverableTimeout();
        }
    }


    private Set<BluetoothDevice> device_con;

    private void is_kaiqi_shebei_bluetooth(BluetoothDevice device) {
        // 获取所有已经绑定的蓝牙设备
        device_con = mBluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> mList1 = new ArrayList<>();
        if (device_con.size() > 0) {
            mList1 = new ArrayList<>(device_con);
        }
        for (BluetoothDevice device1 : mList1) {
            if (device.getAddress().equals(device1.getAddress())) {

            }
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 搜索到的不是已经绑定的蓝牙设备
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    // 显示在TextView上
//                    mTextView.append(device.getName() + ":"
//                            + device.getAddress() + "\n");
                    //  mList2.add(device);
                    //获取搜索到的蓝牙列表bufen
                    BlueDevice bd = new BlueDevice();
                    bd.setName(device.getName());
                    bd.setAddress(device.getAddress());
                    bd.setStatus("未配对");
                    bd.setDevice(device);
                    mBlueDevice2.add(bd);
                    mAdapter2.setContacts(mBlueDevice2);
                    mAdapter2.notifyDataSetChanged();
                }
                /**当绑定的状态改变时*/
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        Log.d("BLUE_Together", "正在配对......");

                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Log.d("BLUE_Together", "完成配对");
//                        hideProgressDailog();
                        /**开始连接*/
                        contectBuleDevices();
                        break;
                    case BluetoothDevice.BOND_NONE:
                        Log.d("BLUE_Together", "取消配对");
                        ToastUtil.showToastCenter("成功取消配对");
                        Data1();

                        break;
                    default:
                        break;
                }

                /**搜索完成*/
            } else if (action
                    .equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                setProgressBarIndeterminateVisibility(false);
                Log.d("BLUE_Together", "搜索完成......");
//                hideProgressDailog();
                setTitle("搜索蓝牙设备");
//                mAdapter2.addConstacts(mList2);

            }
        }
    };

    public boolean disconnect(Class btClass, BluetoothProfile proxy, BluetoothDevice btDevice) throws Exception {
        Method disconnectMethod = btClass.getDeclaredMethod("disconnect", BluetoothDevice.class);
        disconnectMethod.setAccessible(true);
        Boolean returnValue = (Boolean) disconnectMethod.invoke((BluetoothA2dp) proxy, btDevice);
        return returnValue.booleanValue();
    }

    /**
     * 连接蓝牙设备（通过监听蓝牙协议的服务，在连接服务的时候使用BluetoothA2dp协议）
     */
    private BluetoothProfile.ServiceListener mProfileServiceListener = new BluetoothProfile.ServiceListener() {

        @Override
        public void onServiceDisconnected(int profile) {
            ToastUtil.showToastCenter("未连接到设备请检查设备再次连接！");
        }

        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            try {
                if (profile == BluetoothProfile.HEADSET) {
//                    bh = (BluetoothHeadset) proxy;
//                    if (bh.getConnectionState(mTouchObject.bluetoothDevice) != BluetoothProfile.STATE_CONNECTED){
//                        bh.getClass()
//                                .getMethod("connect", BluetoothDevice.class)
//                                .invoke(bh, mTouchObject.bluetoothDevice);
//                    }

                } else if (profile == BluetoothProfile.A2DP) {
                    /**使用A2DP的协议连接蓝牙设备（使用了反射技术调用连接的方法）*/
                    a2dp = (BluetoothA2dp) proxy;
                    if (a2dp.getConnectionState(currentBluetoothDevice) != BluetoothProfile.STATE_CONNECTED) {
                        a2dp.getClass()
                                .getMethod("connect", BluetoothDevice.class)
                                .invoke(a2dp, currentBluetoothDevice);
                        ToastUtil.showToastCenter("已连接成功,请播放音乐!");
                        SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", currentBluetoothDevice.getAddress());
//                        a2dp_state = (int) SpUtils.getInstance(MainActivity_BlueTooth.this).get("a2dp_state","-1");
//                        getBondedDevices();
                        Data1();
                        mAdapter2.remove_item(pos);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private AlertDialog alertDialog;

    public void showDailogs(String msg, DialogInterface.OnClickListener listenter) {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "确认", listenter);
        alertDialog.show();
    }
}
