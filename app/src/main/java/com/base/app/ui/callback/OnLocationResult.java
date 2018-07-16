package com.base.app.ui.callback;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by admin on 04/16/18.
 */

public interface OnLocationResult {
    void onReturnLocation(LatLng latLng);

    void onPermissionEnable(boolean isGrand);
}
