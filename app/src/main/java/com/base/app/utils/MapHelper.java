package com.base.app.utils;

import android.app.Activity;
import android.location.Location;

import com.base.app.ui.callback.OnLocationResult;
import com.github.florent37.rxgps.RxGps;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapHelper {
    private static Disposable mDisposable;

    public static CameraUpdate onZoomToLocation(LatLng location, float zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)
                .zoom(zoom).build();
        return CameraUpdateFactory.newCameraPosition(cameraPosition);
    }

    public static void onGetLocation(Activity activity, boolean isRealTime, final OnLocationResult mLocation) {
        new RxGps(activity).locationLowPower()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Location>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Location location) {
                        if (isRealTime) {
                            mLocation.onReturnLocation(new LatLng(location.getLatitude(), location.getLongitude()));
                        } else {
                            mLocation.onReturnLocation(new LatLng(location.getLatitude(), location.getLongitude()));
                            mDisposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
