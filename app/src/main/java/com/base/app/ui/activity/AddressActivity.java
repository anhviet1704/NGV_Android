package com.base.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.base.app.R;
import com.base.app.automap.AddressResponse;
import com.base.app.automap.PlaceItem;
import com.base.app.automap.PredictionsItem;
import com.base.app.automap.RestClient;
import com.base.app.ui.adapter.AddressCell;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class AddressActivity extends AppCompatActivity {


    @BindView(R.id.et_seach)
    MagicEditText etSeach;
    @BindView(R.id.tv_close)
    MagicTextView tvClose;
    @BindView(R.id.rv_search)
    SimpleRecyclerView rvSearch;
    @BindView(R.id.view_loading)
    LinearLayout viewLoading;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final long DELAY_IN_MILLIS = 500;
    public static final int MIN_LENGTH_TO_START = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        addOnAutoCompleteTextViewTextChangedObserver(etSeach);
    }

    private void addOnAutoCompleteTextViewTextChangedObserver(final MagicEditText autoCompleteTextView) {
        Observable<AddressResponse> autocompleteResponseObservable = RxTextView.textChangeEvents(autoCompleteTextView)
                .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                .observeOn(Schedulers.io())
                .switchMap(s -> {
                    return RestClient.INSTANCE.getGooglePlacesClient().autocomplete(s);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry();
        compositeDisposable.add(
                autocompleteResponseObservable.subscribe(
                        addressResponse -> {
                            List<AddressCell> cells = new ArrayList<>();
                            List<PlaceItem> list = new ArrayList<>();
                            rvSearch.removeAllCells();
                            if (addressResponse.getPredictions().size() > 0) {
                                for (PredictionsItem prediction : addressResponse.getPredictions()) {
                                    PlaceItem item = new PlaceItem(prediction.getStructuredFormatting().getMainText(), prediction.getDescription(), prediction.getPlaceId());
                                    list.add(item);
                                    AddressCell cell = new AddressCell(item);
                                    cell.setOnCellClickListener(new SimpleCell.OnCellClickListener<PlaceItem>() {
                                        @Override
                                        public void onCellClicked(@NonNull PlaceItem item) {
                                            startActivity(new Intent(AddressActivity.this, MapActivity.class));
                                            EventBus.getDefault().postSticky(item);
                                        }
                                    });
                                    cells.add(cell);
                                }
                                rvSearch.addCells(cells);
                                viewLoading.setVisibility(View.GONE);
                                rvSearch.setVisibility(View.VISIBLE);
                            } else {
                                viewLoading.setVisibility(View.VISIBLE);
                                rvSearch.setVisibility(View.GONE);
                            }
                        },
                        e -> Log.e("vinh123", "onError", e),
                        () -> Log.i("vinh123", "onCompleted")));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(String event) {
    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        finish();
    }
}
