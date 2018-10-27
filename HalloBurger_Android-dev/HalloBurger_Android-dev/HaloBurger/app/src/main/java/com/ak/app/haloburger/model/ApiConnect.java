package com.ak.app.haloburger.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiConnect {

	private String serviceName;
	private String params;
	private String[] paramList;
	private Boolean isLoaded = false;
	private Boolean isInterrupted = false;
	private String authToken;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String[] getParamList() {
		return paramList;
	}

	public void setParamList(String[] paramList) {
		this.paramList = paramList;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Boolean getIsLoaded() {
		return isLoaded;
	}

	public void setIsLoaded(Boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public Boolean getIsInterrupted() {
		return isInterrupted;
	}

	public void setIsInterrupted(Boolean isInterrupted) {
		this.isInterrupted = isInterrupted;
	}

	public String checkJasonValue(JSONObject resObject, String name) {
		String value = null;
		if (resObject.has(name)) {
			try {
				value = resObject.getString(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			value = null;

		return value;
	};

	public int checkJasonIntValue(JSONObject resObject, String name) {
		int value = 0;
		if (resObject.has(name)) {
			try {
				value = resObject.getInt(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			value = 0;

		return value;
	};

	public JSONObject checkJasonObject(JSONObject resObject, String name) {
		JSONObject valueResObject = null;
		if (resObject.has(name)) {
			try {
				valueResObject = resObject.getJSONObject(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			valueResObject = null;

		return valueResObject;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
