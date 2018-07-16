package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewDetailItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.ui.callback.OnClickMaster;
import com.base.app.ui.callback.OnLocationResult;
import com.base.app.utils.DialogMaster;
import com.base.app.utils.MapHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;
    private LatLng mLocation;
    private Circle circle;
    @Inject
    LoginItem mLoginItem;
    private DialogMaster mDialogRadius;
    private List<JobNewDetailItem> mJobsMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();

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
        mDialogRadius = new DialogMaster(getContext());
        mDialogRadius.onShowMasterData(new OnClickMaster() {
            @Override
            public void onClickItem(int pos) {

            }
        });
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
                        viewModel.getJobsMap(mLoginItem.getId(), latLng.latitude, latLng.longitude, 15, 0, 1)
                                .observe(getActivity(), new Observer<ResponseObj<JobNewResponse>>() {
                                    @Override
                                    public void onChanged(@Nullable ResponseObj<JobNewResponse> response) {
                                        if (response != null)
                                            if (response.getResponse() == Response.SUCCESS) {
                                                mJobsMap = response.getObj().getData();
                                                onAddMarker(mJobsMap);
                                            }
                                    }
                                });
                        mLocation = latLng;
                        onUpdateUI(mLocation);
                    }

                    @Override
                    public void onPermissionEnable(boolean isGrand) {
                        Toast.makeText(getContext(), getString(R.string.tv_error_02), Toast.LENGTH_SHORT).show();
                    }
                });
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        if (mMap.getCameraPosition().zoom < 18)
                            circle.setVisible(false);
                        else
                            circle.setVisible(true);
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
        bind.viewRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    private void onAddMarker(List<JobNewDetailItem> jobsMap) {
        for (JobNewDetailItem point : jobsMap) {
            options.position(new LatLng(point.getLatitude(), point.getLongitude()));
            //options.title("someTitle");
            //options.snippet("someDesc");
            mMap.addMarker(options).setIcon(BitmapDescriptorFactory.defaultMarker());
        }

    }

}
