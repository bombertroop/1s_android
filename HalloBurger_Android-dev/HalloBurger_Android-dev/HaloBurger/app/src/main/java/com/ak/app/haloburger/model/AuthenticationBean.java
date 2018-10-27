package com.ak.app.haloburger.model;

import org.json.JSONException;
import org.json.JSONObject;



import android.content.Context;
import android.util.Log;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.custom.ui.AlertDialogs;

public class AuthenticationBean extends ApiConnect implements ApiResponse {

	private String connectType;
	private String email;
	private String password;
	private String androidId;
	private String registerType;
	private String deviceToken;
	private String deviceId;
	private String phoneModel;
	private String deviceType;
	private String os;
	private String registerDeviceType;
	private String signInDeviceType;
	private JSONObject obj;

	private AppSession mAppSession;
	private Boolean status;

	public AuthenticationBean() {

	}

	public AuthenticationBean(Context context) {

	}

	@Override
	public void setValueFromJson(String output, Context context) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Log.i("elang", "elang trace: 1" + output);
		if (output != null && !output.equals("")) {
			try {
				JSONObject resObject = new JSONObject(output);
				String sucess = resObject.getString("status");

				if (sucess != null && !sucess.equals("")
						&& sucess.equals("true")) {
					setStatus(true);
					try {
						if (resObject.has("auth_token")) {
							setmAppSession(new AppSession());
							getmAppSession().setAuthToken(
									resObject.getString("auth_token"));
							getmAppSession().setCustomerId(
									resObject.getString("relevant_user_id"));

							super.setIsLoaded(true);
							super.setIsInterrupted(false);
						} else {
							super.setIsInterrupted(true);
						}
					} catch (Exception e) {
						Log.i("elang", "elang error: " + e.getMessage());
					}

				} else {
//						AppConstants.parseInput(output);
				}
			} catch (Exception e) {
				Log.i("elang", "elang trace: 2");
				super.setIsInterrupted(true);
				e.printStackTrace();

				AlertDialogs.showMsgDialog(context.getString(R.string.constant_title_message),
						context.getString(R.string.error_failed_api),
						context);

			}
		} else {
//			AppConstants.parseInput(output);
			AlertDialogs.showMsgDialog(context.getString(R.string.constant_title_message),
					context.getString(R.string.error_failed_api), context);
		}

	}

	public String getConnectType() {
		return connectType;
	}

	public void setConnectType(String connectType) {
		this.connectType = connectType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getRegisterDeviceType() {
		return registerDeviceType;
	}

	public void setRegisterDeviceType(String registerDeviceType) {
		this.registerDeviceType = registerDeviceType;
	}

	public JSONObject getObj() throws JSONException {
		obj = new JSONObject();
		obj.put("connect_type", getConnectType());
		obj.put("email", getEmail());
		obj.put("password", getPassword());
		obj.put("device_type", getDeviceType());
		obj.put("device_id", getDeviceId());
		obj.put("register_type", getRegisterType());
		obj.put("device_token", getDeviceToken());
		obj.put("phone_model", getPhoneModel());
		obj.put("os", getOs());
		obj.put("android_id", getAndroidId());

		if (!getRegisterDeviceType().equals("")
				&& getRegisterDeviceType() != null) {
			obj.put("register_device_type", getRegisterDeviceType());
		} else {
			obj.put("sign_in_device_type", getSignInDeviceType());
		}
		obj.put("appkey", MainActivity.getInstance().getResources().getString(R.string.appkey));
		return obj;
	}

	public void setObj(JSONObject obj) throws JSONException {

		this.obj = obj;

	}

	public AppSession getmAppSession() {
		return mAppSession;
	}

	public void setmAppSession(AppSession mAppSession) {
		this.mAppSession = mAppSession;
	}

	public String getSignInDeviceType() {
		return signInDeviceType;
	}

	public void setSignInDeviceType(String signInDeviceType) {
		this.signInDeviceType = signInDeviceType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
