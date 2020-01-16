package com.example.car_message.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;

import com.amap.api.maps.LocationSource;
import com.amap.api.maps.UiSettings;

import com.amap.api.maps.model.BitmapDescriptorFactory;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;


import com.amap.api.maps.model.MyLocationStyle;

import com.amap.api.maps.model.Text;
import com.example.car_message.R;

import com.example.car_message.adapter.PopwindowAdapter;
import com.example.car_message.base.BaseFragment;
import com.example.car_message.utils.MyMapView;

import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewHomeFragment extends BaseFragment implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener, View.OnClickListener {
    private static final int STROKE_COLOR = Color.argb(0, 0, 0, 0);
    private static final int FILL_COLOR = Color.argb(0, 0, 0, 0);
    private MyMapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    private CameraUpdate mUpdata;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private AMapLocationClient mLocationClient;
    private OnLocationChangedListener mListener;
    private double latitude;
    private double longitude;
    private LatLng latLng;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private Marker showMarker;
    private RecyclerView cinema_recycler_details;
    private Button close_pop;
    private PopupWindow popupWindow;
    private TextView tal_text_show;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        tal_text_show = view.findViewById(R.id.tal_text_show);
        tal_text_show.setText("首页");
        ImageView tal_break = view.findViewById(R.id.tal_img_back);
        tal_break.setVisibility(View.GONE);
        View new_home_in = view.findViewById(R.id.new_home_in);
        new_home_in.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            // startLocaion();//开始定位
            //  Toast.makeText(getActivity(), "已开启定位权限", Toast.LENGTH_LONG).show();
        }
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        aMap = mapView.getMap();
        // UiSettings settings = aMap.getUiSettings();
        aMap.setLocationSource(this);
        //是否显示定位按钮
        aMap.setOnMarkerClickListener(this);

        //  settings.setMyLocationButtonEnabled(false);
        aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位，默认flase
        location();
//            mUiSettings = aMap.getUiSettings();
//            mUiSettings.setZoomControlsEnabled(false);
//            mUiSettings.setCompassEnabled(true);
//            //这是地理位置，就是经纬度。
//            mUpdata = CameraUpdateFactory.newCameraPosition(
//            //15是缩放比例，0是倾斜度，30显示比例
//                    new CameraPosition(new LatLng(40.043212, 116.299728), 15, 0, 30));
//            aMap.moveCamera(mUpdata);//定位的方法
//            mLocationClient = new AMapLocationClient(getContext());
//
//            mLocationOption = new AMapLocationClientOption();
//            mlocationClient.setLocationListener(this);
//            //设置定位模式为高精度模式
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //设置定位时间间隔
//            mLocationOption.setInterval(2000);
//            mlocationClient.setLocationOption(mLocationOption);
//            mlocationClient.startLocation();
        // drawMarkers();

//            if (latitude!=0){
//                latLng = new LatLng(latitude,longitude);
//                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.car_up);
//                MarkerOptions markerOptions = new MarkerOptions()
//                        .anchor(0.5f, 0.5f).position(latLng)
//                        .icon(bitmapDescriptor).draggable(false).period(50);
//                aMap.addMarker(markerOptions);
//            }
    }

    private void location() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.car_up));// 设置小蓝点的图标
        myLocationStyle.strokeColor(STROKE_COLOR);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(0.0f);// 设置圆形的边框粗细
        myLocationStyle.showMyLocation(true);

        aMap.setMyLocationStyle(myLocationStyle);
        mLocationClient = new AMapLocationClient(getActivity());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(false);//false
        mLocationOption.setWifiScan(true);
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);//false
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
//        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                Log.i("tag", "点击activity");
//
//            }
//        });
    }


//

    @Override
    public void onResume() {
        super.onResume();
        isFirstLoc = true;
        location();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端。
        //销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onReloading(String type) {

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                //获取纬度
                latitude = amapLocation.getLatitude();
                //获取经度
                longitude = amapLocation.getLongitude();
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(amapLocation);
                    //添加图钉
                    //aMap.addMarker(BitmapDescriptorFactory.fromResource(R.drawable.car_up));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(amapLocation.getCountry() + ""
                            + amapLocation.getProvince() + ""
                            + amapLocation.getCity() + ""
                            + amapLocation.getProvince() + ""
                            + amapLocation.getDistrict() + ""
                            + amapLocation.getStreet() + ""
                            + amapLocation.getStreetNum());
                    // Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作

                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getContext(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        HideBackgroun();  //弹出  弹窗
        return false;
    }

    //  public void drawMarkers() {
//        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.car_up);
//        Marker marker = aMap.addMarker(new MarkerOptions()
//                .position(new LatLng(40.043212, 116.299728))
//                .icon(icon)
//                .draggable(true));
//        marker.showInfoWindow();// 设置默认显示一个infowinfow
//    }


    private void HideBackgroun() {
        // 产生背景变暗效果
        final View contentView = View.inflate(getActivity(), R.layout.pop_window_father, null);
        final FragmentActivity activity = getActivity();
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);//设置窗口触摸
        popupWindow.setFocusable(true);
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT); //设置宽度 布局文件里设置的没有用
//        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);  //设置高度  必须代码设置
        popupWindow.setAnimationStyle(R.style.pop_shop_anim);//设置popupwindow弹出动画
        popupWindow.setOutsideTouchable(true);//设置窗口外部可触摸   要结合setBackgroundDrawable来使用
        //设置popupwindow背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);//相对于父窗体底部
        cinema_recycler_details = (RecyclerView) contentView.findViewById(R.id.cinema_recycler_details);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        cinema_recycler_details.setLayoutManager(manager);
        PopwindowAdapter popwindowAdapter = new PopwindowAdapter(getContext());
        cinema_recycler_details.setAdapter(popwindowAdapter);
        close_pop = contentView.findViewById(R.id.close_pop);
        close_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });


        //     popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        //    popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_home_in:
                //弹出 pop弹窗
                ShowPopwindow();
                break;
        }
    }

    private void ShowPopwindow() {
        final View contentView = View.inflate(getActivity(), R.layout.item_pop_first, null);
        final FragmentActivity activity = getActivity();


        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);//设置窗口触摸
        popupWindow.setFocusable(true);

        popupWindow.setAnimationStyle(R.style.pop_shop_anim);//设置popupwindow弹出动画
        popupWindow.setOutsideTouchable(true);//设置窗口外部可触摸   要结合setBackgroundDrawable来使用
        //设置popupwindow背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 180);//相对于父窗体底部
        View pop_dismiss = contentView.findViewById(R.id.pop_dismiss);
        pop_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        TextView green_math = contentView.findViewById(R.id.green_math);//运行
        TextView blue_math = contentView.findViewById(R.id.blue_math);//静止
        TextView red_math = contentView.findViewById(R.id.red_math);//报警
        TextView white_math = contentView.findViewById(R.id.white_math);//离线

    }
}
