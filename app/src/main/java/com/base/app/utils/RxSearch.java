package com.base.app.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearch {
    public static Observable<String> fromView(EditText searchView) {

        final PublishSubject<String> subject = PublishSubject.create();
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                subject.onComplete();
            }
        });
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });*/

        return subject;
    }

    /*Use
    RxSearch.fromView(searchView)
            .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
        @Override
        public boolean test(String text) throws Exception {
            if (text.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    })
            .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<String>>() {
        @Override
        public ObservableSource<String> apply(String query) throws Exception {
            return dataFromNetwork(query);
        }
    })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<String>() {
        @Override
        public void accept(String result) throws Exception {
            textViewResult.setText(result);
        }
    });*/
}
