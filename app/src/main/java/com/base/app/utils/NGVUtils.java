package com.base.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.Group;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.base.app.R;
import com.base.app.ui.callback.OnClickGroup;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class NGVUtils {

    public static String covertStringToChar(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatCurrency(Context ctx, double value) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(value) + ctx.getResources().getString(R.string.tv_work_022);
    }

    public static ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException nfe) {
            }
        }
        return result;
    }

    public static void onClicListener(Activity ctx, Group group, final OnClickGroup click) {
        int refIds[] = group.getReferencedIds();
        for (int id : refIds) {
            ctx.findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClickGroup(view);
                }
            });
        }
    }

    public static RequestOptions onGetRound(int dp) {
        RequestOptions options = new RequestOptions();
        options.transform(new MultiTransformation<>(new CenterCrop(), new RoundedCornersTransformation(ConvertUtils.dp2px(dp), 0)));
        return options;
    }

    public static RequestOptions onGetCircleCrop() {
        RequestOptions options = new RequestOptions();
        options.transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()));
        return options;
    }

    public static void startActivity(Context context, Class<?> cls, View v) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
        context.startActivity(new Intent(context, cls), options.toBundle());
    }

    public static String toJsonFromList(List<Object> datas) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {
        }.getType();
        return gson.toJson(datas, type);
    }

    public static List<Object> toListFromJson(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {
        }.getType();
        return gson.fromJson(data, type);
    }

    public static String toJsonFromObject(Object data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    public static Object toObjectFromJson(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, Object.class);
    }

    private static String onConvertVer2(String date) {
        //2018-07-12 23:43:36
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return "";
    }

    public static String onCaculatorDate(Context ctx, String date) {
        //2018-07-12 23:43:36
        String mDate = "";
        /*String[] temp = getFormatDate(date).split("/");
        int mHour = Integer.parseInt(temp[0]);
        int mMinutes = Integer.parseInt(temp[1]);
        int mDay = Integer.parseInt(temp[2]);
        int mMonth = Integer.parseInt(temp[3]);
        int mYear = Integer.parseInt(temp[4]);*/

        String[] temp = date.split(" ");
        String[] dates = temp[0].split("-");
        String[] times = temp[1].split(":");

        int mHour = Integer.parseInt(times[0]);
        int mMinutes = Integer.parseInt(times[1]);
        int mDay = Integer.parseInt(dates[2]);
        int mMonth = Integer.parseInt(dates[1]);
        int mYear = Integer.parseInt(dates[0]);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH/mm/dd/MM/yyyy");
        Date now = new Date();
        String[] dateNow = dateFormatter.format(now).split("/");
        int mHourNow = Integer.parseInt(dateNow[0]);
        int mMinutesNow = Integer.parseInt(dateNow[1]);
        int mDayNow = Integer.parseInt(dateNow[2]);
        int mMonthNow = Integer.parseInt(dateNow[3]);
        int mYearNow = Integer.parseInt(dateNow[4]);
        if (mYearNow > mYear) {
            mDate = mHour + ":" + mMinutes + " " + mDay + "/" + mMonth + "/" + mYear;
        } else if (mYearNow == mYear) {
            if (mMonthNow == mMonth) {
                if (mDayNow == mDay) {
                    if (mHourNow == mHour) {
                        if (mMinutesNow - mMinutes > 0) {
                            mDate = ctx.getResources().getString(R.string.tv_home_005, mMinutesNow - mMinutes);
                        } else if (mMinutesNow - mMinutes == 0) {
                            mDate = ctx.getResources().getString(R.string.tv_home_006);
                        }
                    } else if (mHourNow > mHour) {
                        mDate = ctx.getResources().getString(R.string.tv_home_007, mHourNow - mHour);
                    }
                } else if (mDayNow > mDay) {
                    //mDate = mHour + ":" + mMinutes + " " + onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
                    mDate = onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
                }
            } else if (mMonthNow > mMonth) {
                mDate = onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
                //mDate = mHour + ":" + mMinutes + " " + onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
            }
        } else if (mYearNow > mYear) {
            //mDate = mHour + ":" + mMinutes + " " + onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
            mDate = onFormatDayMonthZero(mDay) + "/" + onFormatDayMonthZero(mMonth) + "/" + mYear;
        }

        return mDate;
    }

    public static String getFormatDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            try {
                value = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //SimpleDateFormat dateFormatter = new SimpleDateFormat("HH/mm/dd/MM/yyyy");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            dateFormatter.setTimeZone(TimeZone.getDefault());
            String dt = dateFormatter.format(value);
            return dt;
        } catch (Exception e) {
            return dateString;
        }
    }

    public static String onFormatDayMonthZero(int value) {
        String sValue = null;
        if (value < 10) {
            sValue = "0" + String.valueOf(value);
        } else {
            sValue = String.valueOf(value);
        }
        return sValue;
    }


}
