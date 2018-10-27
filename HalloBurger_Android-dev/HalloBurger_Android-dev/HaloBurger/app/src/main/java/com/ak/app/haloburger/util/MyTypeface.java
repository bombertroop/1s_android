package com.ak.app.haloburger.util;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyTypeface {

    public static String fontProximaNovaBold = "ProximaNova-Bold.otf";
    public static String TradeGotBolTwo = "TradeGotBolTwo.ttf";
    public static String TradeGothic = "TradeGothic.otf";
    public static String TradeGotLig = "TradeGotLig.otf";
    public static String BebasNeueBold = "BebasNeueBold.ttf";
    public static String BebasNeueRegular = "BebasNeueRegular.otf";


    private final String colorTitle = "ffffff";
    private static final String colorHint = "aaaaaa";
    private final String colorLink = colorTitle;





    public MyTypeface() {

    }
	/*
     * set up font regular
	 */

    private static void setFontType(TextView textView, int size, String color,
                                    String fontType, Context context) {

        Typeface face = FontCache.getTypeface(fontType, context);
        setTextViewAttribute(textView, size, color, face);
    }

    private static void setTextViewAttribute(TextView textView, int size,
                                             String color, Typeface face) {
        textView.setTextSize(size);
        color = "#" + color;
        textView.setTextColor(Color.parseColor(color));
        textView.setTypeface(face/* ,Typeface.BOLD */);
    }

	/*
     * set up font bold
	 */

    private void setFontTypeBold(TextView textView, int size, String color,
                                 String fontType, Context context) {
        Typeface face = FontCache.getTypeface(fontType, context);
        setTextViewAttributeBold(textView, size, color, face);
    }

    private void setTextViewAttributeBold(TextView textView, int size,
                                          String color, Typeface face) {
        textView.setTextSize(size);
        color = "#" + color;
        textView.setTextColor(Color.parseColor(color));
        textView.setTypeface(face, Typeface.BOLD);
        // tv.setTextColor(Color.parseColor("#000000"))
    }

	/*
     * general font
	 */

    public static void setTableTitleFont(TextView textView, Context context) {
        textView.setText(textView.getText().toString().toUpperCase());
        setFontType(textView, 17, "000000", BebasNeueBold, context);
    }

    public static void setTableValueFont(TextView textView, Context context) {
        textView.setText(textView.getText().toString().toUpperCase());
        setFontType(textView, 15, "000000", BebasNeueRegular, context);
    }

    public static void setPageTitleFont(TextView textView, Context context) {
        textView.setText(textView.getText().toString().toUpperCase());
        setFontType(textView, 24, "ffffff", BebasNeueBold, context);
    }

    public static void setOrFont(TextView textView, Context context) {
        textView.setText(textView.getText().toString().toUpperCase());
        setFontType(textView, 24, "000000", BebasNeueBold, context);
    }

    public static void setSplashFont(TextView textView, Context context) {
        textView.setText(textView.getText().toString().toUpperCase());
        setFontType(textView, 24, "000000", BebasNeueBold, context);
    }

    public static void setFieldFont(EditText editText, Context context) {
        editText.setHintTextColor(Color.parseColor("#" + colorHint));
        setFontType(editText, 18, "8a8a8a", TradeGothic, context);
    }

    public static void setFieldFont(TextView textView, Context context) {
        textView.setHintTextColor(Color.parseColor("#" + colorHint));
        setFontType(textView, 18, "8a8a8a", TradeGothic, context);
    }

    public static void setButtonFont(TextView textView, Context context) {
        setFontType(textView, 20, "ffffff", BebasNeueRegular, context);
    }

    public static void setButtonFont(Button textView, Context context) {
        setFontType(textView, 20, "ffffff", BebasNeueRegular, context);
    }

	/*
     * Home View Font Setting
	 */

    public static void setHomeMenusFont(TextView textView, Context context) {
        setFontType(textView, 24, "ffffff", BebasNeueBold, context);
    }

    /*
     * Refer Friend View Font Setting
	 */

    public static void setInstructionFont(TextView textView, Context context) {
        setFontType(textView, 15, "4a4f55", BebasNeueRegular, context);
    }

    /*
     * Main Sign Up View Font Setting
	 */

    public static void setOptinFont(TextView textView, Context context) {
        setFontType(textView, 14, "000000", TradeGotLig, context);
    }

    public static void setToFFont(TextView textView, Context context) {
        setFontType(textView, 14, "222222", TradeGotLig, context);
    }

    public static void setToFLinkFont(TextView textView, Context context) {
        setFontType(textView, 14, "ee3124", TradeGotBolTwo, context);
    }

    public static void setReturningFont(TextView textView, Context context) {
        setFontType(textView, 17, "8A8A8A", TradeGotBolTwo, context);
    }

    public static void setForgotPasswordFont(TextView textView, Context context) {
        setFontType(textView, 16, "000000", TradeGothic, context);
    }

    public static void setFBButtonFont(TextView textView, Context context) {
        setFontType(textView, 20, "FFFFFF", fontProximaNovaBold, context);
    }

     /*
     * Info View Font Setting
	 */

    public static void setInfoMenusFont(TextView textView, Context context) {
        setFontType(textView, 24, "111111", fontProximaNovaBold, context);
    }

    /*
     * About Us View Font Setting
	 */

    public static void setAbutUsQFont(TextView textView, Context context) {
        setFontType(textView, 17, "000000", fontProximaNovaBold, context);
    }

    public static void setAbutUsAFont(TextView textView, Context context) {
        setFontType(textView, 15, "000000", fontProximaNovaBold, context);
    }

    /*
     * Snap Start View Font Setting
	 */

    public static void setSnapStartQFont(TextView textView, Context context) {
        setFontType(textView, 20, "EE3124", fontProximaNovaBold, context);
    }

    public static void setSnaptStartAFont(TextView textView, Context context) {
        setFontType(textView, 17, "111111", fontProximaNovaBold, context);
    }

    /*
     * Promo View Font Setting
	 */

    public static void setPromoDescBoldFont(TextView textView, Context context) {
        setFontType(textView, 18, "000000", TradeGotBolTwo, context);
    }

    public static void setPromoDescFont(TextView textView, Context context) {
        setFontType(textView, 18, "000000", TradeGothic, context);
    }

    public static void setPromoLinkFont(TextView textView, Context context) {
        setFontType(textView, 18, "EE3124", TradeGothic, context);
    }

     /*
     * Reward View Font Setting
	 */

    public static void setRewardPointTitleFont(TextView textView, Context context) {
        setFontType(textView, 18, "000000", fontProximaNovaBold, context);
    }

    public static void setRewardPointValueFont(TextView textView, Context context) {
        setFontType(textView, 18, "EE3124", TradeGotBolTwo, context);
    }

    /*
     * First Time Tutorial View Font Setting
	 */

    public static void setDescTutorialFont(TextView textView, Context context) {
        setFontType(textView, 18, "ffffff", fontProximaNovaBold, context);
    }

    /*
     * Earn Points View Font Setting
	 */

    public static void setFooterFont(TextView textView, Context context) {
        setFontType(textView, 14, "8A8A8A", TradeGotLig, context);
    }

    public static void setUserCodeFont(TextView textView, Context context) {
        setFontType(textView, 18, "000000", TradeGotBolTwo, context);
    }

    /*
     * Locations View Font Setting
	 */

    public static void setLocNameFont(TextView textView, Context context) {
        setFontType(textView, 20, "000000", BebasNeueBold, context);
    }

    public static void setLocAddrFont(TextView textView, Context context) {
        setFontType(textView, 15, "333333", BebasNeueRegular, context);
    }

    /*
     * Locations View Font Setting
	 */

    public static void setRewardTitleFont(TextView textView, Context context) {
        setFontType(textView, 16, "ffffff", fontProximaNovaBold, context);
    }

    public static void setRewardDateTitleFont(TextView textView, Context context) {
        setFontType(textView, 18, "ffffff", fontProximaNovaBold, context);
    }

    public static void setRewardDateFont(TextView textView, Context context) {
        setFontType(textView, 13, "ffffff", fontProximaNovaBold, context);
    }

    /*
     * Locations View Font Setting
	 */

    public static void setRewardClaimTitleFont(TextView textView, Context context) {
        setFontType(textView, 30, "000000", fontProximaNovaBold, context);
    }

    public static void setRewardClaimDescFont(TextView textView, Context context) {
        setFontType(textView, 14, "8A8A8A", TradeGotLig, context);
    }

    public static void setRewardClaimCodeFont(TextView textView, Context context) {
        setFontType(textView, 18, "111111", TradeGotLig, context);
    }


}
