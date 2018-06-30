package com.base.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.app.R;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;

public class ImageFragment extends Fragment {

    private ImageView mIvContent;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIvContent = view.findViewById(R.id.iv_content);
        Glide.with(getActivity()).load(url).apply(NGVUtils.onGetRound(0)).into(mIvContent);
    }

    public String getDetail() {
        return url;
    }

    public void setDetail(String detail) {
        this.url = detail;
    }

}