package com.base.app.ui.fragment;


import android.os.Bundle;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentHomeBinding;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.viewmodel.MainActivityVM;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;

    @Override
    public Class<WorkMapFragmentVM> getViewModel() {
        return WorkMapFragmentVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, WorkMapFragmentVM viewModel) {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
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
        getChildFragmentManager().beginTransaction().replace(R.id.google_map, mapFragment).commit();
         /*viewModel.getPopularMovies()
                .observe(this, listResource -> dataBinding.setResource(listResource));*/
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work_map;
    }

}
