package com.example.p010_recycleviewall.bluetooth;

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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.bluetooth.bluecommon.bean.BlueDevice;
import com.example.p010_recycleviewall.bluetooth.bluecommon.utils.SettingblueUtils;
import com.example.p010_recycleviewall.utils.ShowLoadingUtil;
import com.example.p010_recycleviewall.utils.SpUtils;
import com.example.p010_recycleviewall.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;

import static com.example.p010_recycleviewall.bluetooth.bluecommon.utils.SettingblueUtils.showDailogs;


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
    @BindView(R.id.toggle_lanya)
    ToggleButton toggle_lanya;
    @BindView(R.id.toggle_lany_shebei)
    ToggleButton toggle_lany_shebei;
    @BindView(R.id.tv_o_c)
    TextView tv_o_c;
    @BindView(R.id.tv_de1)
    TextView tv_de1;
    @BindView(R.id.tv_de2)
    TextView tv_de2;
    @BindView(R.id.tv_de3)
    TextView tv_de3;
    @BindView(R.id.ll_ceng1)
    LinearLayout ll_ceng1;
    @BindView(R.id.tv_zzsm)
    TextView tv_zzsm;
    @BindView(R.id.button_id)
    Button button_id;
    @BindView(R.id.sll)
    ScrollView sll;
    @BindView(R.id.rl_ceng2)
    RelativeLayout rl_ceng2;
    @BindView(R.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R.id.recycler_view11)
    RecyclerView recyclerView2;
    //这条是蓝牙串口通用的UUID，不要更改
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "20:16:07:26:18:46"; // <==要连接的目标蓝牙设备MAC地址

    private int pos;
    boolean is_shebei_open = false;
    /**
     * 蓝牙音频传输协议
     */
    public static BluetoothA2dp a2dp;
    /**
     * 需要连接的蓝牙设备
     */
    public static BluetoothDevice currentBluetoothDevice;
    private String currentBluetoothDevice_address;

    private RecycleAdapterbluetooth1 mAdapter1;
    private List<BlueDevice> mBlueDevice1 = new ArrayList<>();
    private BlueDevice listItem_bind;

    private RecycleAdapterbluetooth2 mAdapter2;
    private List<BlueDevice> mBlueDevice2;

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        if (timer != null) {
            timer = null;
        }
//        if (a2dp != null) {
//            a2dp = null;
//        }
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclelistview_bluetooth);
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
        Data2();
        set_blue();
    }

    private void set_blue() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            ToastUtil.showToastCenter("未找到蓝牙设备!");
            return;
        }
        if (mBluetoothAdapter.isEnabled()) {
            open_blue_zhuangtai();
            //new
//            Intent intent1 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(intent1, ENABLE_BLUE);
            //不做提示，强行打开
            // mAdapter.enable();
        } else {
            close_blue();
            close_blue_zhuangtai();
        }
        //本机设备被扫描初始状态bufen
        if (mBluetoothAdapter.getScanMode() == 23) {
            //开启状态设置bufen
            open_discover();
        } else if (mBluetoothAdapter.getScanMode() == 21) {
            //关闭状态设置bufen
            close_discover();
        }
        //获取绑定的设备地址sp
        currentBluetoothDevice_address = (String) SpUtils.getInstance(MainActivity_BlueTooth.this).get("a2dp_state", "");
    }

    /**
     * 开启本机被扫描bufen
     */
    private void open_discover() {
        //开启操作
        //弹窗
//                    Intent intent = new Intent(
//                            BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//                    //设置蓝牙可见性的时间，方法本身规定最多可见300秒
//                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, BLUE_TIME);
//                    startActivityForResult(intent, SEARCH_BLUE);
        //不弹窗
        SettingblueUtils.setDiscoverableTimeout(BLUE_TIME);
        shezhi_blue_research(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SettingblueUtils.closeDiscoverableTimeout();
                shezhi_blue_research(false);
            }
        }, BLUE_TIME * 1000);
    }

    /**
     * 关闭本机被扫描bufen
     */
    private void close_discover() {
        SettingblueUtils.closeDiscoverableTimeout();
        shezhi_blue_research(false);
    }

    private void open_blue_zhuangtai() {
        toggle_lanya.setChecked(true);
        sll.setVisibility(View.VISIBLE);
        rl_ceng2.setVisibility(View.GONE);
        tv_de1.setText(mBluetoothAdapter.getName());
        Data1();
    }

    private void close_blue() {
        /**关闭蓝牙*/
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
        SettingblueUtils.closeDiscoverableTimeout();
        shezhi_blue_research(false);
    }

    private void close_blue_zhuangtai() {
        toggle_lanya.setChecked(false);
        sll.setVisibility(View.GONE);
        rl_ceng2.setVisibility(View.VISIBLE);
//        shezhi_sp_kong();
    }

    /**
     * 取消配对的连接bufen
     *
     * @param listItem
     */
    private void shezhi_cancel_connect(final BlueDevice listItem) {
        showDailogs(MainActivity_BlueTooth.this, "是否取消与设备" + listItem.getName() + "连接？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ShowLoadingUtil.showProgressDialog2(MainActivity_BlueTooth.this, "");
                //取消连接bufen
                if (a2dp == null) {
                    return;
                }
                SettingblueUtils.discontectBuleDevices(listItem, mBluetoothAdapter, a2dp);
//                SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", "");
//                Data1();
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
        showDailogs(MainActivity_BlueTooth.this, msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ShowLoadingUtil.showProgressDialog2(MainActivity_BlueTooth.this, "");
                /**当前需要配对的蓝牙设备*/
                currentBluetoothDevice = listItem.getDevice();
//                currentBluetoothDevice_address = listItem.getDevice().getAddress();
                /**还没有配对*/
                if (listItem.getDevice().getBondState() != BluetoothDevice.BOND_BONDED) {
                    SettingblueUtils.startPariBlue(listItem);
                } else {
                    /**完成配对的,直接连接*/
                    /**使用A2DP协议连接设备*/
//                    SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", currentBluetoothDevice_address);
                    mBluetoothAdapter.getProfileProxy(MainActivity_BlueTooth.this, mProfileServiceListener, BluetoothProfile.A2DP);
                }
            }
        });
    }

    private void findView() {
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
//        tv_o_c.setOnClickListener(this);
        toggle_lanya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    close_blue();
                    close_blue_zhuangtai();
                } else {
                    Intent intent1 = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent1, ENABLE_BLUE);
                    //不做提示，强行打开
                    // mAdapter.enable();
                }
            }
        });
        toggle_lany_shebei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //关闭操作bufen
                    close_discover();
                } else {
                    open_discover();
                }
            }
        });

//        ll_ceng1.setOnClickListener(this);
        button_id.setOnClickListener(this);

        mAdapter1.setOnItemClickLitener(new RecycleAdapterbluetooth1.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, int tpye) {
                final BlueDevice listItem = (BlueDevice)
                        mAdapter1.getItem(position);
                //未配对连接部分
                address = listItem.getAddress();
                listItem_bind = listItem;
                if (tpye == -1) {
                    if (currentBluetoothDevice_address.equals("")) {
                        //已配对连接bufen
                        //old
                        shezhi_connect_peidui(listItem_bind);
                        //new
//                        showDailogs(MainActivity_BlueTooth.this, "是否与设备" + listItem_bind.getName() + "连接？", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //扫描周围设备是否开启 如果开启就连接 不开启就提示未找到bufen
//                                // 如果正在搜索，就先取消搜索
//                                if (mBluetoothAdapter.isDiscovering()) {
//                                    mBluetoothAdapter.cancelDiscovery();
//                                }
//                                // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
//                                mBlueDevice2 = new ArrayList<BlueDevice>();
//                                mBluetoothAdapter.startDiscovery();
//                                is_shebei_open = true;
//                            }
//                        });
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
                SettingblueUtils.shezhi_cancel_peidui(listItem, MainActivity_BlueTooth.this);
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
                //old
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
            Log.d("atodp_status", currentBluetoothDevice_address + "地址0");
            currentBluetoothDevice_address = (String) SpUtils.getInstance(MainActivity_BlueTooth.this).get("a2dp_state", "");
            Log.d("atodp_status", currentBluetoothDevice_address + "地址00");
            if (mList1.get(i).getAddress().equals(currentBluetoothDevice_address)) {
                bd.setStatus(getResources().getString(R.string.bluetooth_tips5));
                bd.setType(BLUE_yilianjie);// 2
            } else {
                bd.setStatus(getResources().getString(R.string.bluetooth_tips4));
                bd.setType(BLUE_yipeidui);
            }
            if (mList1.get(i).getName() == null || mList1.get(i).getName().equals("")) {
                bd.setName("未知设备");
            } else {
                bd.setName(mList1.get(i).getName());
            }
            bd.setAddress(mList1.get(i).getAddress());
            bd.setDevice(mList1.get(i));
            mBlueDevice1.add(bd);
        }
        //设置排位
        mAdapter1.setContacts(SettingblueUtils.shezhi_ranking(mBlueDevice1, currentBluetoothDevice_address));
        mAdapter1.notifyDataSetChanged();
    }

    private void Data2() {
        /**注册搜索蓝牙receiver*/
        IntentFilter mFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mFilter.addAction(BluetoothDevice.ACTION_FOUND);
        mFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        mFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        mFilter.addAction(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_id:
                //开始
                tv_zzsm.setVisibility(View.VISIBLE);
                button_id.setVisibility(View.VISIBLE);
                // 如果正在搜索，就先取消搜索
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }
                // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
                mBlueDevice2 = new ArrayList<BlueDevice>();
                mBluetoothAdapter.startDiscovery();
                is_shebei_open = false;
                break;
            default:

                break;
        }

    }

    private CountDownTimer timer = new CountDownTimer(BLUE_TIME * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
//            tv_de3.setBackgroundResource(R.drawable.address_manager_defult);
            toggle_lany_shebei.setChecked(true);
            tv_de2.setText(getResources().getString(R.string.tips2) + (millisUntilFinished / 1000) + "）");
        }

        @Override
        public void onFinish() {
            close_discover();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENABLE_BLUE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "蓝牙开启成功", Toast.LENGTH_SHORT).show();
                open_blue_zhuangtai();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "蓝牙开始失败", Toast.LENGTH_SHORT).show();
                close_blue();
                close_blue_zhuangtai();
            }
        }
        if (requestCode == SEARCH_BLUE) {
            if (resultCode == BLUE_TIME) {
                shezhi_blue_research(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SettingblueUtils.closeDiscoverableTimeout();
                        shezhi_blue_research(false);
                    }
                }, BLUE_TIME * 1000);
            } else if (resultCode == RESULT_CANCELED) {
                SettingblueUtils.closeDiscoverableTimeout();
                shezhi_blue_research(false);
            }
        }

    }

    private void shezhi_blue_research(boolean is_search) {
        if (is_search) {
            if (timer != null) {
                timer.start();
            }
        } else {
            tv_de2.setText(getResources().getString(R.string.tips1));
            toggle_lany_shebei.setChecked(false);
//            tv_de3.setBackgroundResource(R.drawable.address_manager_normal);
            if (timer != null) {
                timer.cancel();
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
                BluetoothDevice device11 = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 搜索到的不是已经绑定的蓝牙设备
                if (!is_shebei_open) {
                    receiver1(device11);
                } else {
                    receiver11(device11);
                }
                /**当绑定的状态改变时*/
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice device22 = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                receiver2(device22, MainActivity_BlueTooth.this);
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                receiver3();
            } else if (action.equals(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, BluetoothA2dp.STATE_DISCONNECTED);
                receiver4(state);
            } else if (action.equals(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, BluetoothA2dp.STATE_NOT_PLAYING);
                receiver5(state);
            }
        }
    };

    /**
     * 连接蓝牙设备（通过监听蓝牙协议的服务，在连接服务的时候使用BluetoothA2dp协议）
     */
    private BluetoothProfile.ServiceListener mProfileServiceListener = new BluetoothProfile.ServiceListener() {

        @Override
        public void onServiceDisconnected(int profile) {
            SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", "");
            Data1();
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
                    a2dp.getClass().getMethod("connect", BluetoothDevice.class).invoke(a2dp, currentBluetoothDevice);

                    if (a2dp != null) {
                        List<BluetoothDevice> bluetoothDevices = a2dp.getConnectedDevices();
                        if (bluetoothDevices.size() > 0) {
                            for (BluetoothDevice bluetoothDevice : bluetoothDevices) {
                                Log.d("atodp_status", "" + bluetoothDevice.getName() + bluetoothDevice.getAddress());
                            }
                            currentBluetoothDevice_address = bluetoothDevices.get(0).getAddress();
                            SpUtils.getInstance(MainActivity_BlueTooth.this).put("a2dp_state", currentBluetoothDevice_address); //currentBluetoothDevice.getAddress()
                            Data1();
                        }
                    }
                    if (currentBluetoothDevice == null) {
                        Log.d("atodp_status", "currentBluetoothDevice:");
                    } else {
                        Log.d("atodp_status", "currentBluetoothDevice:" + currentBluetoothDevice.getAddress());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    //Receiverbufen
    public void receiver1(BluetoothDevice device11) {
        BlueDevice bd = new BlueDevice();
        bd.setName(device11.getName());
        bd.setAddress(device11.getAddress());
        bd.setStatus("未配对");
        bd.setDevice(device11);
        if (mBlueDevice2 != null) {
            mBlueDevice2.add(bd);
            mAdapter2.setContacts(mBlueDevice2);
            mAdapter2.notifyDataSetChanged();
        }
    }

    public void receiver11(BluetoothDevice device11) {
        if (listItem_bind.getAddress().equals(device11.getAddress())) {
            mBluetoothAdapter.getProfileProxy(MainActivity_BlueTooth.this, mProfileServiceListener, BluetoothProfile.A2DP);
        }
    }

    public void receiver2(BluetoothDevice device11, Context mContext) {
        switch (device11.getBondState()) {
            case BluetoothDevice.BOND_BONDING:
                Log.d("atodp_status", "正在配对......");
                break;
            case BluetoothDevice.BOND_BONDED:
                Log.d("atodp_status", "完成配对");
                Log.d("atodp_status", currentBluetoothDevice_address + "地址1");
//                        hideProgressDailog();
                /**开始连接*/
                /**使用A2DP协议连接设备*/
                mBluetoothAdapter.getProfileProxy(mContext, mProfileServiceListener, BluetoothProfile.A2DP);
                break;
            case BluetoothDevice.BOND_NONE:
                Log.d("atodp_status", "取消配对");
                ToastUtil.showToastCenter("成功取消配对");
                Data1();
                break;
            default:
                break;
        }
    }

    public void receiver3() {
//        setProgressBarIndeterminateVisibility(false);
        Log.d("atodp_status", "搜索完成......");
//                hideProgressDailog();
        tv_zzsm.setVisibility(View.GONE);
        button_id.setVisibility(View.VISIBLE);

    }

    public void receiver4(int state) {
        if (state == BluetoothA2dp.STATE_CONNECTED) {
            mBluetoothAdapter.getProfileProxy(MainActivity_BlueTooth.this, mProfileServiceListener, BluetoothProfile.A2DP);
            Log.d("atodp_status", "A2DP STATE_CONNECTED");
            Log.d("atodp_status", currentBluetoothDevice_address + "地址2");
            ToastUtil.showToastCenter("已连接成功,请播放音乐!");
            if (a2dp != null) {
                List<BluetoothDevice> bluetoothDevices = a2dp.getConnectedDevices();
                if (bluetoothDevices.size() > 0) {
                    for (BluetoothDevice bluetoothDevice : bluetoothDevices) {
                        Log.d("atodp_status", "" + bluetoothDevice.getName() + bluetoothDevice.getAddress());
                    }
                    currentBluetoothDevice_address = bluetoothDevices.get(0).getAddress();
                    SpUtils.getInstance(this).put("a2dp_state", currentBluetoothDevice_address); //currentBluetoothDevice.getAddress()
                }
            }
            Data1();
            if (!currentBluetoothDevice_address.equals("")) {
                Log.d("atodp_status", pos + "");
                mAdapter2.remove_item(pos);
            }
        } else if (state == BluetoothA2dp.STATE_DISCONNECTED) {
            Log.d("atodp_status", "A2DP STATE_DISCONNECTED");
            SpUtils.getInstance(this).put("a2dp_state", "");
            Data1();
        }
    }

    public void receiver5(int state) {
        if (state == BluetoothA2dp.STATE_PLAYING) {
            Log.d("atodp_status", "A2DP start playing");
        } else {
            Log.d("atodp_status", "A2DP stop playing");
            Data1();
        }
    }

}
