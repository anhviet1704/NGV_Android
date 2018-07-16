package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.ui.callback.OnLocationResult;
import com.base.app.utils.MapHelper;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.reactivex.disposables.Disposable;

public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;
    private LatLng mLocation;
    private Disposable mDisposable;
    private Circle circle;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work_map;
    }

    @Override
    public Class<WorkMapFragmentVM> getViewModel() {
        return WorkMapFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        //SupportMapFragment mMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.google_map);
        SupportMapFragment mMapFragment = SupportMapFragment.newInstance();
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
                    }
                });
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        float zoom = mMap.getCameraPosition().zoom;
                        if (zoom < 18) circle.setVisible(false);
                        else circle.setVisible(true);
                    }
                });
            }
        });

        bind.ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(MapHelper.onZoomToLocation(mLocation, 20));
            }
        });
    }

    private void onUpdateUI(LatLng location) {
        mMap.addMarker(new MarkerOptions().position(location).title("Công ty")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 20));
        circle = mMap.addCircle(new CircleOptions()
                .center(location)
                .radius(50)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.parseColor("#1A1C89FF")));
    }

}
