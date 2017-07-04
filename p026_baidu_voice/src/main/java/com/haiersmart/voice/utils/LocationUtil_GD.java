package com.haiersmart.voice.utils;//package com.haiersmart.voice.utils;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//import com.haiersmart.voice.activity.AppManager;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 定位工具类 based on 高德
// * Created by JefferyLeng on 2017/2/18.
// */
//
//public class LocationUtil {
//
//    private static final String TAG = LocationUtil.class.getSimpleName();
//    //声明mLocationOption对象
//    public static AMapLocationClientOption mLocationOption = null;
//
//    public static final String SEPARATOR = "/";
//
//    //声明AMapLocationClient类对象
//    public static AMapLocationClient mLocationClient = null;
//
//    private static Context sContext = AppManager.getAppManager().currentActivity();
//
//    /** 纬度 */
//    private static double mLatitude;
//    /** 精度 */
//    private static double mLongitude;
//
//    /**
//     * 获取位置信息
//     * @return 经度/纬度
//     */
//    public static String getLocation() {
//
//        mLocationClient = new AMapLocationClient(sContext.getApplicationContext());
//        //初始化定位参数
//        mLocationOption = new AMapLocationClientOption();
//        //设置定位监听
//        mLocationClient.setLocationListener(new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    //定位成功回调信息，设置相关消息
//                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    mLatitude = aMapLocation.getLatitude();//获取纬度
//                    mLongitude = aMapLocation.getLongitude();//获取经度
//                    Log.d(TAG, "onLocationChanged: ---> latitude : " + mLatitude + ",longitude : " + mLongitude);
//                    aMapLocation.getAccuracy();//获取精度信息
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(aMapLocation.getTime());
//                    df.format(date);//定位时间
//                } else {
//                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError", "location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//        });
//        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
//        //设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//        // 在定位结束后，在合适的生命周期调用onDestroy()方法
//        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//        //启动定位
//        mLocationClient.startLocation();
//
//        return mLatitude + SEPARATOR + mLongitude;
//    }
//}
