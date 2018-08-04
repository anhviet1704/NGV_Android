package com.base.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.automap.DetailResponse;
import com.base.app.automap.Location;
import com.base.app.automap.PlaceItem;
import com.base.app.automap.RestClient;
import com.blankj.utilcode.util.ActivityUtils;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    @BindView(R.id.tv_name)
    MagicTextView tvName;
    @BindView(R.id.iv_hide)
    ImageView ivHide;
    @BindView(R.id.bt_finish)
    MagicButton btFinish;
    @BindView(R.id.view_address)
    RoundRectView viewAddress;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final long DELAY_IN_MILLIS = 500;
    public static final int MIN_LENGTH_TO_START = 2;
    public static final int DEFAULT_ZOOM = 17;
    private GoogleMap mMap;
    private PlaceItem mPlaceItem;

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(PlaceItem item) {
        mPlaceItem = item;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvName.setText(mPlaceItem.getDes());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addOnAutoCompleteTextViewItemClickedSubscriber(mPlaceItem.getPlaceId());
    }

    @OnClick({R.id.iv_hide, R.id.bt_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hide:
                /*if (viewAddress.getVisibility() == View.VISIBLE)
                    viewAddress.setVisibility(View.GONE);
                else {
                    viewAddress.setVisibility(View.GONE);
                }*/
                break;
            case R.id.bt_finish:
                EventBus.getDefault().postSticky(mPlaceItem);
                ActivityUtils.finishActivity(AddressActivity.class);
                finish();
                break;
        }
    }

    private void addOnAutoCompleteTextViewItemClickedSubscriber(String id) {
        Observable<DetailResponse> adapterViewItemClickEventObservable =
                Observable.just(1)
                        .observeOn(Schedulers.io())
                        .switchMap(placeId -> RestClient.INSTANCE.getGooglePlacesClient().details(id))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry();

        compositeDisposable.add(adapterViewItemClickEventObservable.subscribe(
                placeDetailsResult -> {
                    Log.i("vinh123", placeDetailsResult.toString());
                    updateMap(placeDetailsResult);
                },
                throwable -> Log.e("vinh123", "onError", throwable),
                () -> Log.i("vinh123", "onCompleted")));
    }

    private void updateMap(DetailResponse placeDetailsResponse) {
        if (mMap != null) {
            mMap.clear();
            Location location = placeDetailsResponse.getResult().getGeometry().getLocation();
            LatLng latLng = new LatLng(location.getLat(), location.getLng());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .title(placeDetailsResponse.getResult().getName());
            Marker marker = mMap.addMarker(markerOptions);
            marker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
            mPlaceItem.setLat(location.getLat());
            mPlaceItem.setLng(location.getLng());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
