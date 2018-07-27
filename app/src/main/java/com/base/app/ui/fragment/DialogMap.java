package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.LoginItem;
import com.base.app.ui.callback.OnLocationResult;
import com.base.app.utils.MapHelper;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.TravelMode;
import com.ivankocijan.magicviews.views.MagicTextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogMap extends DialogFragment {
    @BindView(R.id.tv_title)
    MagicTextView tvTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.view_toolbar)
    RoundRectView viewToolbar;
    @BindView(R.id.google_map)
    FrameLayout googleMap;
    private static final int overview = 0;
    Unbinder unbinder;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @Inject
    LoginItem mLoginItem;
    private GoogleMap mMap;
    private LatLng mLocation;
    @BindView(R.id.view_root)
    ViewGroup viewRoot;
    private MarkerOptions options = new MarkerOptions();
    private Marker myLocation;
    private LatLng mEndLocation;
    private Marker mMarkerStart;
    private Marker mMarkerEnd;

    public static DialogMap newInstance() {
        DialogMap fragment = new DialogMap();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        setStyle(STYLE_NO_TITLE, R.style.AppThemeNoToolBar);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_map, container, false);
        getDialog().setTitle("Simple Dialog");
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mMapFragment = MapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.google_map, mMapFragment).commit();
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                MapHelper.onGetLocation(getActivity(), false, new OnLocationResult() {
                    @Override
                    public void onReturnLocation(LatLng latLng) {
                        mLocation = latLng;
                        onUpdateUI(mLocation);
                        onAddMarkerStart(mLocation);
                        onAddMarkerEnd(mEndLocation);
                        DirectionsResult results = getDirectionsDetails(new com.google.maps.model.LatLng(mLocation.latitude, mLocation.longitude),
                                new com.google.maps.model.LatLng(mEndLocation.latitude, mEndLocation.longitude), TravelMode.DRIVING);
                        if (results != null) {
                            addPolyline(results, googleMap);
                            //positionCamera(results.routes[overview], googleMap);
                            //addMarkersToMap(results, googleMap);
                        }
                    }

                    @Override
                    public void onPermissionEnable(boolean isGrand) {
                        Toast.makeText(getActivity(), getString(R.string.tv_error_02), Toast.LENGTH_SHORT).show();
                    }
                });
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        /*if (mMap.getCameraPosition().zoom < 18)
                            circle.setVisible(false);
                        else
                            circle.setVisible(true);*/
                    }
                });
            }

        });
    }

    private void onUpdateUI(LatLng location) {
        myLocation = mMap.addMarker(new MarkerOptions().position(location));
        myLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 19));
    }

    private void onAddMarkerStart(LatLng location) {
        mMarkerStart = mMap.addMarker(new MarkerOptions().position(location));
        mMarkerStart.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 19));
    }

    private void onAddMarkerEnd(LatLng location) {
        mMarkerEnd = mMap.addMarker(new MarkerOptions().position(location));
        mMarkerEnd.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tagret));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 19));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_close, R.id.iv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_location:
                mMap.animateCamera(MapHelper.onZoomToLocation(mLocation, 19));
                onUpdateUI(mLocation);
                break;
        }
    }

    public void setData(JobCurrentItem item) {
        //mEndLocation = new LatLng( item.getLatitude(), item.getLongitude());
        mEndLocation = new LatLng(10.801053, 106.666267);
    }

    private void onAddPolyLine() {
        List<LatLng> path = new ArrayList();

        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBrPt88vvoPDDn_imh-RzCXl5Ha2F2LYig").build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, "41.385064,2.173403", "40.416775,-3.70379");
        try {
            DirectionsResult res = req.await();
            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }
    }

    private GeoApiContext getGeoContext() {
        return new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_maps_key))
                .queryRateLimit(3)
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();
    }

    private DirectionsResult getDirectionsDetails(com.google.maps.model.LatLng origin, com.google.maps.model.LatLng destination, TravelMode mode) {
        DateTime now = new DateTime();
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(mode)
                    .origin(origin)
                    .destination(destination)
                    .departureTime(now)
                    .await();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addPolyline(DirectionsResult results, GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[overview].overviewPolyline.getEncodedPath());
        PolylineOptions polyline = new PolylineOptions()
                .addAll(decodedPath)
                .width(25f)
                .color(Color.parseColor("#FF869B"));
        mMap.addPolyline(polyline);
    }
}
