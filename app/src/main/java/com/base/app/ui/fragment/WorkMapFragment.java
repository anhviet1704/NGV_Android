package com.base.app.ui.fragment;


import android.os.Bundle;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;

    @Override
    public Class<WorkMapFragmentVM> getViewModel() {
        return WorkMapFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        //SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        SupportMapFragment mMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.google_map);
        if (mMapFragment == null) {
            mMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.google_map, mMapFragment).commit();
            mMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    LatLng seattle = new LatLng(10.813831, 106.6691083);
                    mMap.addMarker(new MarkerOptions().position(seattle).title("Công ty"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle));
                    mMap.setMinZoomPreference(5);
                    mMap.setMaxZoomPreference(20);
                }
            });
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work_map;
    }

}
