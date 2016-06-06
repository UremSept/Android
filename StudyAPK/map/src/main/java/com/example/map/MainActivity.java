package com.example.map;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GeoCoder search;
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    private BDLocation bdLocation;
    LocationClient mLocationClient = null;
    BDLocationListener myListener = new MyLocationListener();
    private boolean booleanTraffic = false, booleanHeat = false;
    private Button mapTypeNormalBtn, mapTypeSatelliteBtn, mapTypeNoneBtn, mapTrafficBtn, mapHeatBtn, mapSignBtn,mapLocationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapTypeNormalBtn = (Button) findViewById(R.id.mapTypeNormalBtn);
        mapTypeNormalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            }
        });
        mapTypeSatelliteBtn = (Button) findViewById(R.id.mapTypeSatelliteBtn);
        mapTypeSatelliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//卫星地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            }
        });
        mapTypeNoneBtn = (Button) findViewById(R.id.mapTypeNoneBtn);
        mapTypeNoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
            }
        });
        mapTrafficBtn = (Button) findViewById(R.id.mapTrafficBtn);
        mapTrafficBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booleanTraffic = !booleanTraffic;
                //开启关闭交通图
                mBaiduMap.setTrafficEnabled(booleanTraffic);
            }
        });
        mapHeatBtn = (Button) findViewById(R.id.mapHeatBtn);
        mapHeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booleanHeat = !booleanHeat;
                //开启关闭城市热力图
                mBaiduMap.setBaiduHeatMapEnabled(booleanHeat);
            }
        });
        mapSignBtn = (Button) findViewById(R.id.mapSignBtn);
        mapSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //定义Maker坐标点
                LatLng point = new LatLng(39.963175, 116.400244);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_marka);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap)
                        .draggable(true);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
            }
        });
        mapLocationBtn = (Button) findViewById(R.id.mapLocationBtn);
        mapLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按钮点击就进行一次网络定位
                if (mLocationClient.isStarted()){
                    mLocationClient.stop();
                    return;
                }
                mLocationClient.start();


            }
        });
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        mBaiduMap.setMyLocationEnabled(true);
        search=GeoCoder.newInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(1000);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
/*            searchLocation();
            getLocation(location);*/
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                //bdLocation = location;
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
/*                searchLocation();
                getLocation(location);*/

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
/*                searchLocation();
                getLocation(location);*/
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
/*                searchLocation();
                getLocation(location);*/
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
    private void getLocation(BDLocation location){

        search.geocode(new GeoCodeOption().city(location.getCity()).address(location.getAddrStr()));
//        // 开启定位图层
//        Log.e("定位数据","--------------------------------");
//        // 构造定位数据
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location.getLatitude())
//                .longitude(location.getLongitude()).build();
//        // 设置定位数据
//        mBaiduMap.setMyLocationData(locData);
//        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.mipmap.icon_marka);
//        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
//        mBaiduMap.setMyLocationConfigeration(config);
        // 当不需要定位图层时关闭定位图层
        //mBaiduMap.setMyLocationEnabled(false);
    }

    private void searchLocation(){
        search.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                //当未能找到搜索的位置，或者网络问题，无法找到结果的时候执行
                if (geoCodeResult==null||geoCodeResult.error!= SearchResult.ERRORNO.NO_ERROR){
                    Toast.makeText(MainActivity.this, "抱歉未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                //设置我们搜素地点的图标标识
                mBaiduMap.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka)));
                //设置我们当前的地图的中心点
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation()));



//                String str=String.format("经度：%f 纬度%f",geoCodeResult.getLocation().latitude,geoCodeResult.getLocation().longitude);
//                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
//                //传入经度和纬度
//                LatLng point = new LatLng(geoCodeResult.getLocation().latitude,geoCodeResult.getLocation().longitude);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {}
        });
    }




}
