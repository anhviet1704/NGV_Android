package com.base.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.automap.PlaceItem;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityRegisterBinding;
import com.base.app.model.BaseValueItem;
import com.base.app.model.CategoryItem;
import com.base.app.model.CountryResponse;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
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
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.DecimalFormat;
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
    private List<CategoryItem> mCategoryItems = new ArrayList<>();
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
    PlaceItem mPlaceItem;
    List<BaseValueItem> mUploadFiles = new ArrayList<>();
    List<BaseValueItem> mUploadCategory = new ArrayList<>();
    private String fileName1;
    private String fileName2;
    private double lat;
    private double lon;

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
        bind.etAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getOffices().observe(RegisterActivity.this, new Observer<ResponseObj<List<BaseValueItem>>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<List<BaseValueItem>> listResponseObj) {
                        startActivity(new Intent(RegisterActivity.this, AddressActivity.class));
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
                    for (int i = 0; i < mCategoryItems.size(); i++) {
                        if (mCategoryItems.get(i).isCheck()) {
                            mUploadCategory.add(new BaseValueItem(String.valueOf(i), String.valueOf(mCategoryItems.get(i).getId())));
                        }
                    }
                    RegisterObj registerObj = new RegisterObj();
                    registerObj.setFullname(bind.etName.getText().toString());
                    registerObj.setPassword(bind.etPassword.getText().toString());
                    registerObj.setBirthday(bind.etBirthday.getText().toString());
                    registerObj.setCountry(mOfCountryId);
                    registerObj.setPhone(bind.etPhone.getText().toString());
                    registerObj.setEmail(bind.etEmail.getText().toString());
                    registerObj.setOffice(mOfficeId);
                    registerObj.setStatus(1);//set active
                    registerObj.setGender(mGenderId);
                    registerObj.setIdentity_img(new Gson().toJsonTree(mUploadFiles).toString());
                    registerObj.setJob_id(new Gson().toJsonTree(mUploadCategory).toString());
                    registerObj.setAddress(bind.etAddress.getText().toString());
                    registerObj.setLat(lat);
                    registerObj.setLon(lon);
                    onRegister(viewModel, registerObj);
                }

            }
        });
        viewModel.getCategories().observe(RegisterActivity.this, new Observer<ResponseObj<List<CategoryItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<CategoryItem>> listResponseObj) {
                if (listResponseObj.getResponse() == Response.SUCCESS) {
                    mCategoryItems = listResponseObj.getObj();
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
                        final String fileName = NGVUtils.onGenFileName(FileUtils.getFileExtension(FileUtils.getFileName(uri.getPath())));
                        selectedUri = uri;
                        if (mPosOfImage == 1) {
                            fileName1 = fileName;
                            Glide.with(RegisterActivity.this).load(new File(uri.getPath())).apply(NGVUtils.onGetRound(6)).into(bind.ivFrontCard);
                        } else if (mPosOfImage == 2) {
                            fileName2 = fileName;
                            Glide.with(RegisterActivity.this).load(new File(uri.getPath())).apply(NGVUtils.onGetRound(6)).into(bind.ivBackCard);
                        }

                        new Compressor(getApplicationContext())
                                .compressToFileAsFlowable(new File(uri.getPath()))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(file -> {
                                    File compressedImage = file;
                                    ContentResolver cR = getContentResolver();
                                    MimeTypeMap mime = MimeTypeMap.getSingleton();
                                    String type = mime.getExtensionFromMimeType(cR.getType(uri));
                                    String type2 = cR.getType(uri);
                                    RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), compressedImage);
                                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", fileName, body);
                                    viewModel.uploadFile(filePart).observe(RegisterActivity.this, new Observer<ResponseObj<UploadItem>>() {
                                        @Override
                                        public void onChanged(@Nullable ResponseObj<UploadItem> objectResponseObj) {
                                            if (objectResponseObj != null)
                                                if (objectResponseObj.getResponse() == Response.SUCCESS) {
                                                    if (fileName1.equals(objectResponseObj.getObj().getFileNameUpload())) {
                                                        mUploadFiles.add(new BaseValueItem("1", objectResponseObj.getObj().getImageName()));
                                                    } else if (fileName2.equals(objectResponseObj.getObj().getFileNameUpload())) {
                                                        mUploadFiles.add(new BaseValueItem("2", objectResponseObj.getObj().getImageName()));
                                                    }
                                                } else
                                                    Toast.makeText(RegisterActivity.this, objectResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Toast.makeText(getApplicationContext(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                        String mime = getContentResolver().getType(selectedUri);

                    }
                })
                .setPeekHeight(ScreenUtils.getScreenHeight())
                .setSelectedUri(selectedUri)
                .setTitle("Chọn hình ảnh")
                .create();
        bottomSheetDialogFragment.show(getSupportFragmentManager());

    }

    public String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private void onSetupWorkType() {
        mWorkAdapter = new WorkTypeAdapter(RegisterActivity.this, mCategoryItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                mCategoryItems.get(pos).setCheck(!mCategoryItems.get(pos).isCheck());
                mWorkAdapter.onUpdateData(mCategoryItems);

            }
        });
        bind.rvWork.setLayoutManager(new GridLayoutManager(RegisterActivity.this, 2));
        bind.rvWork.setHasFixedSize(true);
        bind.rvWork.setNestedScrollingEnabled(false);
        bind.rvWork.setItemAnimator(new DefaultItemAnimator());
        bind.rvWork.setAdapter(mWorkAdapter);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(PlaceItem item) {
        mPlaceItem = item;
        bind.etAddress.setText(mPlaceItem.getDes());
        lat = mPlaceItem.getLat();
        lon = mPlaceItem.getLng();
    }
}
