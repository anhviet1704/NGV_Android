package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.model.BaseValueItem;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobMapAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.ui.callback.OnClickMaster;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.ui.callback.OnLocationResult;
import com.base.app.utils.DialogMaster;
import com.base.app.utils.MapHelper;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;
    private LatLng mLocation;
    private Circle circle;
    @Inject
    LoginItem mLoginItem;
    @Inject
    PrefHelper mPrefHelper;
    private DialogMaster mDialogRadius;
    private List<JobNewItem> mJobsMap;
    private MarkerOptions options = new MarkerOptions();
    private boolean isShowMap = true;
    private JobMapAdapter mWorkAdapter;
    private List<BaseValueItem> mRadiusList = new ArrayList<>();
    private int mRadius = 5;
    private Marker myLocation;
    private Marker oldMarker;

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
        mDialogLoading.show();
        bind.tvRadius.setText(String.format(getString(R.string.tv_work_024), "5km"));
        mRadiusList.add(new BaseValueItem("5", "5km"));
        mRadiusList.add(new BaseValueItem("10", "10km"));
        mRadiusList.add(new BaseValueItem("15", "15km"));
        mRadiusList.add(new BaseValueItem("20", "20km"));
        mDialogRadius = new DialogMaster(getContext());
        mDialogRadius.onShowMasterData(new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {

                onGetJobFromRadius(Integer.parseInt(((BaseValueItem) object).getId()));
                mRadius = Integer.parseInt(((BaseValueItem) object).getId());
                bind.tvRadius.setText(String.format(getString(R.string.tv_work_024), ((BaseValueItem) object).getValue()));
            }
        });
        mDialogRadius.setData(mRadiusList);
        mDialogRadius.setTitle(getString(R.string.tv_work_023));
        mWorkAdapter = new JobMapAdapter(getContext(), mJobsMap, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                EventBus.getDefault().postSticky(mJobsMap.get(pos));
                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.rvJob.setItemAnimator(new SlideInUpAnimator());
        bind.rvJob.setAdapter(mWorkAdapter);

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
                        onGetJobFromRadius(mRadius);
                        onUpdateUI(mLocation, mRadius);
                    }

                    @Override
                    public void onPermissionEnable(boolean isGrand) {
                        Toast.makeText(getContext(), getString(R.string.tv_error_02), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        bind.ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mMap.clear();
                mMap.animateCamera(MapHelper.onZoomToLocation(mLocation, 19));
                //onUpdateUI(mLocation, mRadius);
            }
        });
        bind.viewRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogRadius.show();
            }
        });
        bind.ivSwitchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowMap = !isShowMap;
                if (isShowMap) {
                    bind.rvJob.setVisibility(View.GONE);
                    bind.googleMap.setVisibility(View.VISIBLE);
                    bind.ivLocation.setVisibility(View.VISIBLE);
                } else {
                    bind.rvJob.setVisibility(View.VISIBLE);
                    bind.googleMap.setVisibility(View.GONE);
                    bind.ivLocation.setVisibility(View.GONE);
                }

            }
        });
    }

    private void onUpdateUI(LatLng location, int radius) {
        myLocation = mMap.addMarker(new MarkerOptions().position(location).title("Công ty"));
        myLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 19));
        circle = mMap.addCircle(new CircleOptions()
                .center(location)
                .radius(radius * 1000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.parseColor("#1A1C89FF")));
    }

    private void onAddMarker(List<JobNewItem> jobsMap) {
        for (JobNewItem point : jobsMap) {
            options.position(new LatLng(point.getLatitude(), point.getLongitude()));
            //options.title("someTitle");
            //options.snippet("someDesc");
            mMap.addMarker(options).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(myLocation)) {
                } else {
                    if (oldMarker != null) {
                        oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
                    }
                    oldMarker = marker;
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_select));
                }
                return false;
            }
        });
    }

    private void onGetJobFromRadius(int radius) {
        viewModel.getJobsMap(mLoginItem.getId(), mLocation.latitude, mLocation.longitude, radius, 0, 100)
                .observe(getActivity(), new Observer<ResponseObj<JobNewResponse>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<JobNewResponse> response) {
                        if (response != null)
                            if (response.getResponse() == Response.SUCCESS) {
                                mMap.clear();
                                mJobsMap = response.getObj().getData();
                                onAddMarker(mJobsMap);
                                mWorkAdapter.onUpdateData(mJobsMap);
                                onUpdateUI(mLocation, mRadius);
                            } else if (response.getResponse() == Response.UNAUTHORIZED) {
                                NGVUtils.showAuthorized(getActivity(), MainActivity.mViewRoot, mPrefHelper);
                            }
                        mDialogLoading.dismiss();
                    }
                });
    }

}
