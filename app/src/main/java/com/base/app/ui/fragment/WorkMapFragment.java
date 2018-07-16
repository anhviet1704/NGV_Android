package com.base.app.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkMapBinding;
import com.base.app.viewmodel.WorkMapFragmentVM;
import com.github.florent37.rxgps.RxGps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WorkMapFragment extends BaseFragment<WorkMapFragmentVM, FragmentWorkMapBinding> {
    private GoogleMap mMap;

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
        final RxGps rxGps = new RxGps(getActivity());
        SupportMapFragment mMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.google_map);
        if (mMapFragment == null) {
            rxGps.lastLocation()

                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {

                        }
                    })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(location -> {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                        mMap.animateCamera(CameraUpdateFactory.zoomIn());
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                    }, throwable -> {
                        if (throwable instanceof RxGps.PermissionException) {
                            //displayError(throwable.getMessage());
                        } else if (throwable instanceof RxGps.PlayServicesNotAvailableException) {
                            // displayError(throwable.getMessage());
                        }
                    });
            mMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.google_map, mMapFragment).commit();
            mMapFragment.getMapAsync(new OnMapReadyCallback() {
                @SuppressLint("MissingPermission")
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    LatLng demoMatker = new LatLng(10.813831, 106.6691083);
                    mMap.addMarker(new MarkerOptions().position(demoMatker).title("Công ty"));
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(demoMatker));
                    mMap.setMinZoomPreference(5);
                    mMap.setMaxZoomPreference(20);
                    mMap.setMyLocationEnabled(true);
                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                    // Zoom in, animating the camera.
                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                }
            });
        }
    }


}
