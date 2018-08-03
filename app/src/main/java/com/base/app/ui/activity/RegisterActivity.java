package com.base.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.base.app.model.CountryResponse;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.UploadItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.ui.adapter.WorkTypeAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.utils.DialogMaster;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.Response;
import com.base.app.viewmodel.RegisterActivityVM;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import gun0912.tedbottompicker.TedBottomPicker;
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class RegisterActivity extends BaseActivity<RegisterActivityVM, ActivityRegisterBinding> {

    private int mPosOfImage = -1;
    private String mOfficeId;
    private String mOfCountryId;
    private int mGenderId = -1;
    private CountryResponse mCountryItem;
    private List<RoleItem> mRoles = new ArrayList<>();
    private List<BaseValueItem> mOffices = new ArrayList<>();
    private List<BaseValueItem> mGender = new ArrayList<>();
    private List<BaseValueItem> mCountries = new ArrayList<>();
    private DialogMaster mDialogOffices;
    private DialogMaster mDialogCountries;
    private DialogMaster mDialogGender;
    private WorkTypeAdapter mWorkAdapter;
    @Inject
    LoginItem mLoginItem;
    private Uri selectedUri;

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
        bind.tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogGender.show();
            }
        });
        bind.tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogCountries.show();
                viewModel.getCountries().observe(RegisterActivity.this, new android.arch.lifecycle.Observer<ResponseObj<List<BaseValueItem>>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<List<BaseValueItem>> countryItemResponseObj) {
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
                if (StringUtils.isTrimEmpty(bind.etPhone.getText().toString())
                        || TextUtils.isEmpty(bind.etPassword.getText().toString())
                        || TextUtils.isEmpty(bind.etPasswordConfirm.getText().toString())
                        || TextUtils.isEmpty(bind.etName.getText().toString())
                        || TextUtils.isEmpty(bind.etBirthday.getText().toString())
                        || TextUtils.isEmpty(bind.tvGender.getText().toString())
                        || TextUtils.isEmpty(bind.etAddress.getText().toString())
                        || TextUtils.isEmpty(bind.tvCountry.getText().toString())
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
                    registerObj.setCountry(mOfCountryId);
                    registerObj.setPhone(bind.etPhone.getText().toString());
                    registerObj.setEmail(bind.etEmail.getText().toString());
                    registerObj.setRole(upRoles.substring(1));
                    registerObj.setOffice(mOfficeId);
                    registerObj.setStatus(1);//set active
                    //null param
                    registerObj.setAddress(bind.etAddress.getText().toString());
                    registerObj.setAvatar("");
                    registerObj.setDescription("");
                    registerObj.setFamily_register_img("");
                    registerObj.setGender(mGenderId);
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
        mDialogOffices.onShowMasterData(new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {
                mOfficeId = ((BaseValueItem) object).getId();
                bind.tvDepartment.setText(((BaseValueItem) object).getValue());
            }
        });
        mDialogOffices.setTitle(getString(R.string.tv_home_004));
        mDialogCountries = new DialogMaster(this);
        mDialogCountries.onShowMasterData(new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {
                mOfCountryId = ((BaseValueItem) object).getId();
                bind.tvCountry.setText(((BaseValueItem) object).getValue());
            }
        });
        mDialogCountries.setTitle(getString(R.string.tv_register_007));

        mDialogGender = new DialogMaster(this);
        mDialogGender.onShowMasterData(new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {
                mGenderId = Integer.parseInt(((BaseValueItem) object).getId());
                bind.tvGender.setText(((BaseValueItem) object).getValue());
            }
        });
        mGender.add(new BaseValueItem("1", getString(R.string.tv_register_032)));
        mGender.add(new BaseValueItem("2", getString(R.string.tv_register_033)));
        mDialogGender.setData(mGender);
        mDialogGender.setTitle(getString(R.string.tv_register_031));
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
        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(RegisterActivity.this)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onImageSelected(final Uri uri) {
                        if (mPosOfImage == 1) {
                            Glide.with(RegisterActivity.this).load(new File(uri.getPath())).apply(NGVUtils.onGetRound(6)).into(bind.ivFrontCard);
                        } else if (mPosOfImage == 2) {
                            Glide.with(RegisterActivity.this).load(new File(uri.getPath())).apply(NGVUtils.onGetRound(6)).into(bind.ivBackCard);
                        }
                        final String fileName = NGVUtils.onGenFileName(FileUtils.getFileExtension(FileUtils.getFileName(uri.getPath())));
                        selectedUri = uri;
                        new Compressor(getApplicationContext())
                                .compressToFileAsFlowable(new File(uri.getPath()))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<File>() {
                                    @Override
                                    public void accept(File file) throws Exception {
                                        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                        MultipartBody.Part filePart = MultipartBody.Part.createFormData(fileName, fileName, body);
                                        viewModel.uploadFile(filePart).observe(RegisterActivity.this, new Observer<ResponseObj<UploadItem>>() {
                                            @Override
                                            public void onChanged(@Nullable ResponseObj<UploadItem> objectResponseObj) {
                                                if (objectResponseObj != null)
                                                    if (objectResponseObj.getResponse() == Response.SUCCESS)
                                                        Toast.makeText(RegisterActivity.this, "upload file success + hide loading", Toast.LENGTH_SHORT).show();
                                                    else
                                                        Toast.makeText(RegisterActivity.this, "upload file failse + " + objectResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Toast.makeText(getApplicationContext(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                })
                .setPeekHeight(ScreenUtils.getScreenHeight())
                .setSelectedUri(selectedUri)
                .setTitle("Chọn hình ảnh")
                .create();
        bottomSheetDialogFragment.show(getSupportFragmentManager());

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
