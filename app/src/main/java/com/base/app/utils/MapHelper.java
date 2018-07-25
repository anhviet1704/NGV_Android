package com.base.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.base.app.ui.callback.OnLocationResult;
import com.github.florent37.rxgps.RxGps;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MapHelper {
    private static Disposable mDisposable;

    public static CameraUpdate onZoomToLocation(LatLng location, float zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)
                .zoom(zoom).build();
        return CameraUpdateFactory.newCameraPosition(cameraPosition);
    }

    @SuppressLint("CheckResult")
    public static void onGetLocation(Activity activity, boolean isRealTime, final OnLocationResult locationClick) {
        new RxGps(activity).locationLowPower()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                })
                .subscribe(location -> {
                    if (isRealTime) {
                        locationClick.onReturnLocation(new com.google.android.gms.maps.model.LatLng(location.getLatitude(), location.getLongitude()));
                    } else {
                        locationClick.onReturnLocation(new com.google.android.gms.maps.model.LatLng(location.getLatitude(), location.getLongitude()));
                        mDisposable.dispose();
                    }
                }, throwable -> {
                    if (throwable instanceof RxGps.PermissionException) {
                        locationClick.onPermissionEnable(false);
                        //the user does not allow the permission
                    } else if (throwable instanceof RxGps.PlayServicesNotAvailableException) {
                        //the user do not have play services
                    }
                });
    }
}
