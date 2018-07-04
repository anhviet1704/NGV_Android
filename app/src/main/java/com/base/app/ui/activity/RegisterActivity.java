package com.base.app.ui.activity;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityRegisterBinding;
import com.base.app.model.BaseValueItem;
import com.base.app.model.CountryItem;
import com.base.app.model.CountryResponse;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.ui.adapter.WorkTypeAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.ui.callback.OnClickMaster;
import com.base.app.utils.DialogHelper;
import com.base.app.utils.DialogMaster;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.Response;
import com.base.app.viewmodel.RegisterActivityVM;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RegisterActivity extends BaseActivity<RegisterActivityVM, ActivityRegisterBinding> {

    private int mPosOfImage = -1;
    private int mPosOfOffice = -1;
    private int mPosOfCountry = -1;
    private CountryResponse mCountryItem;
    private List<RoleItem> mRoles = new ArrayList<>();
    private List<BaseValueItem> mOffices = new ArrayList<>();
    private List<CountryItem> mCountries = new ArrayList<>();
    private DialogMaster mDialogOffices;
    private DialogMaster mDialogCountries;
    private WorkTypeAdapter mWorkAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected Class<RegisterActivityVM> getViewModel() {
        return RegisterActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        KeyboardUtils.hideSoftInput(this);
        onSetupDialog();
        bind.etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowDate();
            }
        });
        bind.viewFrontCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosOfImage = 1;
                onOpenGallery();
            }
        });
        bind.viewBackCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosOfImage = 2;
                onOpenGallery();
            }
        });
        bind.tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogCountries.show();
                viewModel.getCountries().observe(RegisterActivity.this, new android.arch.lifecycle.Observer<ResponseObj<List<CountryItem>>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<List<CountryItem>> countryItemResponseObj) {
                        if (countryItemResponseObj.getResponse() == Response.SUCCESS) {
                            mCountries = countryItemResponseObj.getObj();
                            mDialogCountries.setData(mCountries);
                        } else {
                            Toast.makeText(RegisterActivity.this, countryItemResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        bind.tvDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogOffices.show();
                viewModel.getOffices().observe(RegisterActivity.this, new Observer<ResponseObj<List<BaseValueItem>>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<List<BaseValueItem>> listResponseObj) {
                        if (listResponseObj.getResponse() == Response.SUCCESS) {
                            mOffices = listResponseObj.getObj();
                            mDialogOffices.setData(mOffices);
                        } else {
                            Toast.makeText(RegisterActivity.this, listResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isTrimEmpty(bind.etName.getText().toString())
                        || TextUtils.isEmpty(bind.etBirthday.getText().toString())
                        || TextUtils.isEmpty(bind.tvCountry.getText().toString())
                        || TextUtils.isEmpty(bind.etPhone.getText().toString())
                        || TextUtils.isEmpty(bind.etEmail.getText().toString())
                        || TextUtils.isEmpty(bind.tvDepartment.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.tv_error_01), Toast.LENGTH_SHORT).show();
                } else {
                    String upRoles = "";
                    for (int i = 0; i < mRoles.size(); i++) {
                        if (mRoles.get(i).isCheck()) {
                            upRoles = upRoles + "," + mRoles.get(i).getId();
                        }
                    }
                    RegisterObj registerObj = new RegisterObj();
                    registerObj.setFullname(bind.etName.getText().toString());
                    registerObj.setBirthday(bind.etBirthday.getText().toString());
                    registerObj.setCountry(mCountries.get(mPosOfCountry).getId());
                    registerObj.setPhone(bind.etPhone.getText().toString());
                    registerObj.setEmail(bind.etEmail.getText().toString());
                    registerObj.setRole(upRoles.substring(1));
                    registerObj.setOffice(String.valueOf(mOffices.get(mPosOfOffice).getId()));
                    registerObj.setStatus(1);//set active
                    //null param
                    registerObj.setAddress("46 Bach Dang");
                    registerObj.setAvatar("");
                    registerObj.setDescription("");
                    registerObj.setFamily_register_img("");
                    registerObj.setGender(0);
                    registerObj.setIdentity_id("");
                    registerObj.setIdentity_img("");
                    registerObj.setProfile_img("");
                    registerObj.setScore(0);

                    onRegister(viewModel, registerObj);
                }

            }
        });
        viewModel.getRoles().observe(RegisterActivity.this, new Observer<ResponseObj<List<RoleItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<RoleItem>> listResponseObj) {
                if (listResponseObj.getResponse() == Response.SUCCESS) {
                    mRoles = listResponseObj.getObj();
                    onSetupWorkType();
                } else {
                    Toast.makeText(RegisterActivity.this, listResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void onRegister(RegisterActivityVM viewModel, RegisterObj registerObj) {
        viewModel.postRegister(registerObj).observe(RegisterActivity.this, new Observer<ResponseObj<RegisterItem>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<RegisterItem> registerItemResponseObj) {
                if (registerItemResponseObj.getResponse() == Response.SUCCESS) {
                    Intent intent = new Intent(RegisterActivity.this, RegisterConfirmActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                    startActivity(intent, options.toBundle());
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, registerItemResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onSetupDialog() {
        mDialogOffices = new DialogMaster(this);
        mDialogOffices.onShowMasterData(new OnClickMaster() {
            @Override
            public void onClickItem(int pos) {
                mPosOfOffice = pos;
                bind.tvDepartment.setText(mOffices.get(pos).getValue());
            }
        });
        mDialogOffices.setTitle(getString(R.string.tv_home_004));
        mDialogCountries = new DialogMaster(this);
        mDialogCountries.onShowMasterData(new OnClickMaster() {
            @Override
            public void onClickItem(int pos) {
                mPosOfCountry = pos;
                bind.tvCountry.setText(mCountries.get(pos).getName());
            }
        });
        mDialogCountries.setTitle(getString(R.string.tv_register_007));
    }

    private void onShowDate() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String day = NGVUtils.onFormatDayMonthZero(dayOfMonth);
                        String month = NGVUtils.onFormatDayMonthZero(monthOfYear + 1);
                        bind.etBirthday.setText(day + "/" + month + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void onOpenGallery() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.ALL)
                .folderMode(false)
                .toolbarFolderTitle("Folder")
                .toolbarImageTitle("Tap to select")
                .toolbarArrowColor(Color.BLACK)
                .single()
                .limit(1)
                .showCamera(true)
                .imageDirectory("Camera")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (mPosOfImage == 1) {
                Glide.with(this).load(image.getPath()).apply(NGVUtils.onGetRound(6)).into(bind.ivFrontCard);
            } else if (mPosOfImage == 2) {
                Glide.with(this).load(image.getPath()).apply(NGVUtils.onGetRound(6)).into(bind.ivBackCard);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onSetupWorkType() {
        mWorkAdapter = new WorkTypeAdapter(RegisterActivity.this, mRoles, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                mRoles.get(pos).setCheck(!mRoles.get(pos).isCheck());
                mWorkAdapter.onUpdateData(mRoles);

            }
        });
        bind.rvWork.setLayoutManager(new GridLayoutManager(RegisterActivity.this, 2));
        bind.rvWork.setHasFixedSize(true);
        bind.rvWork.setNestedScrollingEnabled(false);
        bind.rvWork.setItemAnimator(new DefaultItemAnimator());
        bind.rvWork.setAdapter(mWorkAdapter);
    }
}
