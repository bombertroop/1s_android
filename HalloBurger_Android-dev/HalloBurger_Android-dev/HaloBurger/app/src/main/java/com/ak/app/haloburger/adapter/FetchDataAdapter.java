package com.ak.app.haloburger.adapter;

import android.util.Log;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by el on 28/05/18.
 */

public class FetchDataAdapter {

    public interface AsyncResponse {
        void processFinish(JSONObject response);
    }

    private Main2Activity mActivity = Main2Activity.getInstance();
    public AsyncResponse delegate = null;

    public FetchDataAdapter() {
    }

    public FetchDataAdapter(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    public void requestGET(String url, String tag, final HashMap<String,String> customHeaders){
        getResponse(Request.Method.GET, url, tag, customHeaders, null);
    }

    public void requestPOST(String url, String tag, JSONObject postparams){
        getResponse(Request.Method.POST, url, tag, null, postparams);
    }

    public void requestPostHeader(String url, String tag, final HashMap<String,String> customHeaders){
//        JSONObject post = new JSONObject();
//        try {
//            post.put("login","a@a.com");
//
//            post.put("password","password");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        getResponse(Request.Method.POST, url, tag, null, new JSONObject(customHeaders));
//        getResponse(Request.Method.POST, url, tag, customHeaders, null);
    }


    private void getResponse(final int method, String url, String tag, final HashMap<String,String> customHeaders, JSONObject postparams){
        String baseURL = Main2Activity.getInstance().getResources().getString(R.string.base_url2);
        Log.i("elang","elang hit url: "+baseURL+url);
        mActivity.progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, baseURL+url, postparams, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("elang","elang response: "+response);

                mActivity.progressDialog.dismiss();
                delegate.processFinish(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("elang","elang response error: "+error);
                mActivity.progressDialog.dismiss();

                NetworkResponse response = error.networkResponse;


                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }else
                    AlertDialogs.showMsgDialog(Main2Activity.getInstance().getResources().getString(R.string.constant_title_message), Main2Activity.getInstance().getResources().getString(R.string.error_failed_api), Main2Activity.getInstance());



            }
        }){

            public Map<String,String> getHeaders() throws AuthFailureError {

                HashMap<String,String> headers = new HashMap();
                if (customHeaders!=null){
                    headers = customHeaders;
                }

                headers.put("Content-Type", "application/json");

                return headers;
            }
        };
        Main2Activity.getInstance().addToRequestQueue(jsonObjReq, tag);
    }

}
