package com.ak.app.haloburger.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Created by el on 10/08/17.
 */

public class AppHelper {

    private String currentLocale;

    public final static String EDIT_EMAIL = "EDIT_EMAIL";
    public final static String EDIT_PASSWORD = "EDIT_PASSWORD";
    public final static String EDIT_NAME = "EDIT_NAME";
    public final static String EDIT_PHONE = "EDIT_PHONE";
    public final static String EDIT_LOCATION = "EDIT_LOCATION";
    public final static String EDIT_COMMON = "EDIT_COMMON";
    public final static String CHECKBOX = "CHECKBOX";


    public static boolean getNetworkAvailable(Context context) {
        boolean isNetworkEnable = false;
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState() == NetworkInfo.State.CONNECTED
                    || connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState() == NetworkInfo.State.CONNECTING) {
                isNetworkEnable = true;
            } else if (connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState() == NetworkInfo.State.CONNECTED
                    || connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState() == NetworkInfo.State.CONNECTING) {
                isNetworkEnable = true;
            } else
                isNetworkEnable = false;
        } catch (Exception e) {
            isNetworkEnable = false;
            e.printStackTrace();
        }
        Log.i("elang", "elang network enable: " + isNetworkEnable);
        return isNetworkEnable;
    }

    public static double getDistance(Location userLoc, Location offerLoc) {
        double distance = userLoc.distanceTo(offerLoc);
        distance = distance * 0.0621371;
        int idistance = (int) distance;
        distance = idistance;
        distance = distance / 100;
        return distance;

    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public final static boolean filterLongEnough(EditText amountEditText) {
        return amountEditText.getText().toString().trim().length() > 0;
    }

    public static boolean filterLongEnoughPhone(EditText amountEditText) {
        return amountEditText.getText().toString().trim().length() >= 12;
    }

    public static boolean filterLongEnoughName(EditText amountEditText) {
        return amountEditText.getText().toString().trim().length() > 1;
    }

    public static boolean filterLongEnoughPassword(EditText amountEditText) {
        return amountEditText.getText().toString().trim().length() > 3;
    }

    public boolean filterLongEnoughZipCode(EditText amountEditText) {
        return amountEditText.getText().toString().trim().length() > 4;
    }

    public void createQRCode(String barcodeData, Drawable d,
                             ImageView imageView, Activity object) {

        // barcode image
        Bitmap bitmap = null;

        changeScreenBrightness(object);

        int width = d.getIntrinsicWidth();
        int height = d.getIntrinsicHeight()/2;
        try {
            bitmap = MainActivity.getInstance().encodeAsBitmap(barcodeData,
                    BarcodeFormat.CODE_128, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);

    }

    public void createQRCodeReward(String barcodeData, Drawable d,
                             ImageView imageView, Activity object) {

        // barcode image
        Bitmap bitmap = null;

        changeScreenBrightness(object);

        int width = d.getIntrinsicWidth();
        int height = d.getIntrinsicHeight()* 4 / 5;
        try {
            bitmap = MainActivity.getInstance().encodeAsBitmap(barcodeData,
                    BarcodeFormat.CODE_128, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);

    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private int DEFAULT_BRIGHTNESS_VALUE = -1;
    public int DEFAULT_BRIGHTNESS = -1;

    // Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    // Window object, that will store a reference to the current window
    private Window window;

    public void changeScreenBrightness(Activity object) {
        // Get the content resolver
        cResolver = object.getContentResolver();

        // Get the current window
        window = object.getWindow();

        try {
            // Get the current system brightness
            DEFAULT_BRIGHTNESS_VALUE = Settings.System.getInt(cResolver,
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            // Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        if (DEFAULT_BRIGHTNESS_VALUE < 200) {
            // Set the system brightness using the brightness variable value
            Settings.System.putInt(cResolver,
                    Settings.System.SCREEN_BRIGHTNESS, 200);
        }
    }

    public void setDefaultBrightness(Activity object) {
        // Get the content resolver
        cResolver = object.getContentResolver();

        // Get the current window
        window = object.getWindow();

        try {
            // Get the current system brightness
            DEFAULT_BRIGHTNESS = Settings.System.getInt(cResolver,
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            // Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

    }

    public void changeScreenBrightnessToDefault(Activity object) {
        // Get the content resolver
        cResolver = object.getContentResolver();

        // Get the current window
        window = object.getWindow();

        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS,
                DEFAULT_BRIGHTNESS);

    }

    public Boolean isUSCurrentLocale() {
        Boolean result = false;
        if ("en_US".equals(this.currentLocale)) {
            result = true;
        }
        return result;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public void createDropDownList(Spinner mSpinner, String[] items,
                                   final AssetManager asset, final Resources resources, Context context) {
        Log.i("elang", "elang createDropDownList");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
//                R.layout.custom_simple_spinner_item, items) {
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View v = super.getView(position, convertView, parent);
//
//                if (position == getCount()) {
//                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
//                    ((TextView) v.findViewById(android.R.id.text1))
//                            .setHint(getItem(getCount())); // "Hint to be displayed"
//                }
//
//                Typeface externalFont = Typeface.createFromAsset(asset,
//                        "MontserratRegular.ttf");
//                ((TextView) v).setTypeface(externalFont);
//                ((TextView) v).setTextColor(Color.parseColor("#000000"));
//                ((TextView) v).setHintTextColor(Color.parseColor("#aaaaaa"));
//                int size;
//                if (resources.getDisplayMetrics().density < 2)
//                    size = 14;
//                else
//                    size = 16;
//
//                ((TextView) v).setTextSize(size);
//
//                return v;
//            }
//
//            @Override
//            public int getCount() {
//                return super.getCount() - 1; // you dont display
//                // last item. It
//                // is used as
//                // hint.
//            }
//        };
//        dataAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        mSpinner.setAdapter(dataAdapter);
//
//        mSpinner.setSelection(dataAdapter.getCount());
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public final static String getValueString(String value) {
        Log.i("elang", "elang error : " + (value == null));
        String result = null;
        try {
            result = ((value.equals("") && value == null && value
                    .equals("null")) ? "" : value);
        } catch (Exception e) {

        }

        return result;

    }

    public final static void clearEditText(final EditText editText,
                                           final ImageView xButton) {

        xButton.setVisibility(View.INVISIBLE);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)
                    xButton.setVisibility(View.VISIBLE);
                else
                    xButton.setVisibility(View.INVISIBLE);
            }
        });

        xButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    public static void showPassword(Boolean isPasswordShowing,
                                    EditText editPassword, ImageView indicatorButton) {
        if (isPasswordShowing) {
//            indicatorButton.setImageResource(R.drawable.eye_off);
            editPassword
                    .setTransformationMethod(new PasswordTransformationMethod());
        } else {
//            indicatorButton.setImageResource(R.drawable.eye_on);
            editPassword.setTransformationMethod(null);
        }
    }

    public final static InputFilter spaceFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (Character.isSpaceChar(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    };

    /*
     * Button Setting
     */
    public static void setEnableButton(RelativeLayout btnLayout, Boolean b) {
        btnLayout.setEnabled(b);
        if (b)
            setEnableButton(btnLayout);
        else
            setDisableButton(btnLayout);

    }

    public static void setEnableButton(ImageView btnLayout, Boolean b) {
        btnLayout.setEnabled(b);
        if (b)
            setEnableButton(btnLayout);
        else
            setDisableButton(btnLayout);

    }

    private static void setDisableButton(RelativeLayout btnLayout) {
        // btnLayout.setBackgroundResource(R.drawable.button_inactive_style);
        btnLayout.setAlpha((float) 0.5);
    }

    private static void setEnableButton(RelativeLayout btnLayout) {
        // btnLayout.setBackgroundResource(R.drawable.button_style);
        btnLayout.setAlpha((float) 1);
    }

    private static void setDisableButton(ImageView btnLayout) {
        btnLayout.setAlpha((float) 0.5);
    }

    private static void setEnableButton(ImageView btnLayout) {
        btnLayout.setAlpha((float) 1);
    }

    public static void setPageTitle(TextView textPageTitle, View rootView, String title) {
//        textPageTitle = (TextView) rootView.findViewById(R.id.text_page_title);
        try {
            textPageTitle.setText(title);
        } catch (Exception e) {
            Log.i("elang", "elang error: " + e.getMessage());
        }

//        MainActivity.getInstance().getTypeFace().setPageTitleFont(textPageTitle);
    }

    public static void setOptinRelevant(TextView textView) {
        SpannableString content = new SpannableString(textView.getText()
                .toString());

        ClickableSpan terOfUse = new ClickableSpan() {

            @Override
            public void onClick(View widget) {

//                MainActivity.getInstance().setDisplayView(new WebViewFragment(mActivity
//                        .getMyURL().getURL_TERMS_OF_USE(), "TERMS OF USE",
//                        "Terms Of Use"), false);

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.bgColor = Color.TRANSPARENT;
                ds.setColor(Color.parseColor("#641765"));
                ds.setUnderlineText(true);
            }
        };

        ClickableSpan privacyPolicy = new ClickableSpan() {

            @Override
            public void onClick(View widget) {
//                mActivity.setDisplayView(0);
//                mActivity.setDisplayView(new WebViewFragment(mActivity
//                        .getMyURL().getURL_PRIVACY_URL(), "PRIVACY POLICY",
//                        "Privacy Policy"), false);

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.bgColor = Color.TRANSPARENT;
                ds.setColor(Color.parseColor("#641765"));
                ds.setUnderlineText(true);
            }
        };

        content.setSpan(terOfUse, 42, 55, 0);
        content.setSpan(privacyPolicy, 60, 74, 0);

        textView.setText(content);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public static void shareEmail(String emailTitle, String emailBody, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + emailTitle + "&body="
                    + emailBody);
            intent.setData(data);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    public static void shareMessage(String message, Context context) {
        try {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("smsto:"));
            sendIntent.putExtra("sms_body", message);

            if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(sendIntent);

            } else {
                AlertDialogs.showMsgDialog("",
                        "Please install text messaging app", context);
            }

        } catch (Exception e) {
//            mActivity.doNotExitApp(false);
        }
    }

    public static String makeDate(String dateString) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        return formatter.format(dateString);
    }

    public static void onContactUsCreate(String to, String subject, String message, Context context) {
        String uriText = "mailto:" + to + "?subject=" + subject + "&body="
                + Uri.encode(message);
        Uri uri = Uri.parse(uriText);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(uri);
        context.startActivity(Intent.createChooser(emailIntent, "Email"));
        MainActivity.getInstance().doNotExitApp(true);
    }

    private void shareMessage(String message) {
        try {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("smsto:"));
            sendIntent.putExtra("sms_body", message);

            if (sendIntent.resolveActivity(MainActivity.getInstance().getPackageManager()) != null) {
                MainActivity.getInstance().startActivity(sendIntent);
                MainActivity.getInstance().doNotExitApp(true);
            } else {
                AlertDialogs.showMsgDialog("",
                        "Please install text messaging app", MainActivity.getInstance());
            }

        } catch (Exception e) {
            MainActivity.getInstance().doNotExitApp(false);
        }
    }

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
