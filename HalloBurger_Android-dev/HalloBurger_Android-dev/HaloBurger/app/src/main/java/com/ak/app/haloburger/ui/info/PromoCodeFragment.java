package com.ak.app.haloburger.ui.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.api.PromoCodeReq;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.model.DeviceBean;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PromoCodeFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse {

    private TextView textPageTitle;
    private MainActivity mActivity;
    private View rootView;
    private TextView caseText, contactText,contactDescription, promocodeEditTitle;
    private EditText promocodeEdit;
    private ImageView submitBtn;
    private RequirementFieldAdapter RFAdapter;

    private PromoCodeReq mPromoCodeReq;
    private LogInResponse mLogInResponse;

    private FetchDataAdapter fetchDataAdapter;

    public PromoCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_promo_code, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Promo Page");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }

    InputFilter promoCodeFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if ( !Character.isLetterOrDigit(source.charAt(i)) ) {
                    return "";
                }
            }
            return null;
        }
    };

    private void fetchingData() throws JSONException {
        String requestObj = JsonConverter.getGson().toJson(mPromoCodeReq);
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/promocode", "/promocode",  obj);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.promocode_image_submit:
                if(AppHelper.getNetworkAvailable(mActivity)) {
                    String promocode = promocodeEdit.getText().toString();
                    String authToken = mActivity.getPreference().getString(
                            getString(R.string.pref_auth_token), "");

                    mPromoCodeReq.setAuth_token(authToken);
                    mPromoCodeReq.setCode(promocode);
                    mPromoCodeReq.setForce("true");

                    try {
                        fetchingData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    AlertDialogs.showMsgDialog(
                            getResources().getString(R.string.constant_title_message),
                            mActivity
                                    .getString(R.string.error_network_services), mActivity);
                }
                break;
            case R.id.promocode_text_contactus:
                String messages = "";
                String email = mActivity.getPreference().getString( getString(R.string.pref_login_id), "");
                if (!email.equals(""))
                    messages = new DeviceBean(mActivity).getDeviceName();
                AppHelper.onContactUsCreate(getString(R.string.email_faq_contact_us), "Halo Burger - Promo code assistance", messages, mActivity);
                break;
        }
    }

    @Override
    public void initView() {
        promocodeEditTitle = rootView.findViewById(R.id.promocode_edit_title);
        caseText = rootView
                .findViewById(R.id.promocode_edit_case);
        promocodeEdit = rootView
                .findViewById(R.id.promocode_edit_promo);
        submitBtn = rootView
                .findViewById(R.id.promocode_image_submit);
        contactText = rootView
                .findViewById(R.id.promocode_text_contactus);
        contactDescription = rootView
                .findViewById(R.id.promocode_text_description);
        submitBtn.setOnClickListener(this);
        contactText.setOnClickListener(this);

        promocodeEdit.setFilters(new InputFilter[] { promoCodeFilter });

        RFAdapter = new RequirementFieldAdapter(submitBtn);
        RFAdapter.addRequirementField(promocodeEdit,
                AppHelper.EDIT_COMMON);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_promo_code));
        mPromoCodeReq = new PromoCodeReq();
    }

    @Override
    public void setFontView() {
        MyTypeface.setPromoDescBoldFont(promocodeEditTitle ,mActivity);

        MyTypeface.setPromoDescFont(caseText, mActivity);
        MyTypeface.setFieldFont(promocodeEdit, mActivity);

        MyTypeface.setPromoDescFont(contactDescription, mActivity);
        MyTypeface.setPromoLinkFont(contactText, mActivity);
    }

    @Override
    public void processFinish(JSONObject response) {
        final Gson gson = new Gson();

        mLogInResponse = gson.fromJson(String.valueOf(response), LogInResponse.class);


        AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), mLogInResponse.getMessage(), mActivity);


    }
}
