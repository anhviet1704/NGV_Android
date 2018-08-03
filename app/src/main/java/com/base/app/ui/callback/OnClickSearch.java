package com.base.app.ui.callback;

import android.view.View;

/**
 * Created by admin on 04/16/18.
 */

public interface OnClickSearch<T> {
    void onClickItem(View v, T object);
}
