package com.example.car_message.fragment;

import android.content.Context;

import android.os.Bundle;

import android.util.Pair;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import com.amap.api.maps.model.animation.AlphaAnimation;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.example.car_message.R;
import com.example.car_message.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends BaseFragment implements View.OnClickListener, AMap.OnMarkerClickListener {

    MapView mMapView = null;
    AMap aMap;
    private double[] coords;//路线坐标点数据,不断清空复用
    private List<LatLng> carsLatLng;//静态车辆的数据
    private List<LatLng> goLatLng;
    //    List<LatLng> carsLatLng = new ArrayList<>(); //出发集合
    //    List<LatLng> goLatLng = new ArrayList<>();//终点集合
    List<Marker> showMarks;//静态车辆图标
    private List<SmoothMoveMarker> smoothMarkers;//平滑移动图标集合
    //经度
    private double lng = 0.0;
    //纬度
    private double lat = 0.0;
    private Button put;
    private Button run;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        ImageView img_back = view.findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        TextView text_show = view.findViewById(R.id.tal_text_show);
        text_show.setText("首页");
        initMap();
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        put = view.findViewById(R.id.put);
        run = view.findViewById(R.id.run);

        click();
    }

    public void click() {
        //放入静态车辆
        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空地图覆盖物
                if (smoothMarkers != null) {//清空动态marker
                    for (int i = 0; i < smoothMarkers.size(); i++) {
                        smoothMarkers.get(i).destroy();
                    }
                }
                //清除旧集合
                if (showMarks == null) {
                    showMarks = new ArrayList<Marker>();
                }
                for (int j = 0; j < showMarks.size(); j++) {
                    showMarks.get(j).remove();
                }
                //依次放入静态图标
                for (int i = 0; i < carsLatLng.size(); i++) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.car_up);
                    lng = Double.valueOf(carsLatLng.get(i).longitude);
                    lat = Double.valueOf(carsLatLng.get(i).latitude);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat, lng))
                            .icon(icon);
                    showMarks.add(aMap.addMarker(markerOptions));

                    Animation startAnimation = new AlphaAnimation(0, 1);
                    startAnimation.setDuration(600);
                    //设置所有静止车的角度
//                            showMarks.get(i).setRotateAngle(Float.valueOf(listBaseBean.datas.get(i).angle));
                    showMarks.get(i).setAnimation(startAnimation);
                    //   showMarks.get(i).setRotateAngle(new Random().nextInt(359));
                    showMarks.get(i).startAnimation();
                }
            }
        });
        /**
         * 展示平滑移动车辆
         */
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smoothMarkers != null) {//清空动态marker
                    for (int i = 0; i < smoothMarkers.size(); i++) {
                        smoothMarkers.get(i).destroy();
                    }
                }
                //清除旧集合
                if (showMarks == null) {
                    showMarks = new ArrayList<Marker>();
                }
                //清除静态marker
                for (int j = 0; j < showMarks.size(); j++) {
                    showMarks.get(j).remove();
                }
                smoothMarkers = null;//清空旧数据
                smoothMarkers = new ArrayList<SmoothMoveMarker>();
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.car_up);
                //循环
                for (int i = 0; i < carsLatLng.size(); i++) {
                    //放入路线
                    double[] newoords = {Double.valueOf(carsLatLng.get(i).longitude), Double.valueOf(carsLatLng.get(i).latitude),
                            Double.valueOf(goLatLng.get(i).longitude), Double.valueOf(goLatLng.get(i).latitude)};
                    coords = newoords;
                    //移动车辆
                    movePoint(icon);
                }
            }
        });
    }

    private void initMap() {

        //出发地
        LatLng car1 = new LatLng(39.902138, 116.391415);
        LatLng car2 = new LatLng(39.935184, 116.328587);
        LatLng car3 = new LatLng(39.987814, 116.488232);
        //出发地坐标集合
        showMarks = new ArrayList<Marker>();
        carsLatLng = new ArrayList<>();
        if (0 == carsLatLng.size()) {
            carsLatLng.add(car1);
            carsLatLng.add(car2);
            carsLatLng.add(car3);
        }

        //目的地
        LatLng go1 = new LatLng(39.96782, 116.403775);
        LatLng go2 = new LatLng(39.891225, 116.322235);
        LatLng go3 = new LatLng(39.883322, 116.415619);
        //目的地坐标集合
        goLatLng = new ArrayList<>();
        if (0 == goLatLng.size()) {
            goLatLng.add(go1);
            goLatLng.add(go2);
            goLatLng.add(go3);
        }

    }

    public void movePoint(BitmapDescriptor bitmap) {
        // 获取轨迹坐标点
        List<LatLng> points = readLatLngs();
//        LatLngBounds bounds = new LatLngBounds(points.get(0), points.get(points.size() - 2));
//        aMap
//         .animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

//        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();//谷歌新集合，替代hashmap
        SmoothMoveMarker smoothMarker = new SmoothMoveMarker(aMap);
        smoothMarkers.add(smoothMarker);
        int num = smoothMarkers.size() - 1;
        // 设置滑动的图标
        smoothMarkers.get(num).setDescriptor(bitmap);
        LatLng drivePoint = points.get(0);
        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());

        // 设置滑动的轨迹左边点
        smoothMarkers.get(num).setPoints(subList);
        // 设置滑动的总时间
        smoothMarkers.get(num).setTotalDuration(30);
        // 开始滑动
        smoothMarkers.get(num).startSmoothMove();
    }

    //获取路线
    private List<LatLng> readLatLngs() {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < coords.length; i += 2) {
            points.add(new LatLng(coords[i + 1], coords[i]));
        }
        return points;
    }

    @Override
    protected void onShow() {
        super.onShow();
        click();
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onReloading(String type) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tal_img_back:
//                Toast.makeText(getContext(), "点击了返回", Toast.LENGTH_SHORT).show();
//                break;
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        for (int i = 0; i < showMarks.size(); i++) {
            if (marker.equals(showMarks.get(i))) {
                if (aMap != null) {
                    Toast.makeText(getContext(), "" + i + marker.getId(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }

}
