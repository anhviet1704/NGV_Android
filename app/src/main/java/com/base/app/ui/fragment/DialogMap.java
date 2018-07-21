package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.LoginItem;
import com.base.app.model.joblasted.JobNewItem;
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
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.List;

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
    @BindView(R.id.view_root)
    LinearLayout viewRoot;
    Unbinder unbinder;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @Inject
    LoginItem mLoginItem;
    private GoogleMap mMap;
    private LatLng mLocation;
    private MarkerOptions options = new MarkerOptions();
    private Marker myLocation;

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
        myLocation = mMap.addMarker(new MarkerOptions().position(location).title("Công ty"));
        myLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location));
        mMap.animateCamera(MapHelper.onZoomToLocation(location, 19));
    }

    private void onAddMarker(List<JobNewItem> jobsMap) {
        for (JobNewItem point : jobsMap) {
            options.position(new LatLng(point.getLatitude(), point.getLongitude()));
            //options.title("someTitle");
            //options.snippet("someDesc");
            mMap.addMarker(options).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
        }
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
        //item.getLatitude();
        //item.getLongitude();
    }
}
