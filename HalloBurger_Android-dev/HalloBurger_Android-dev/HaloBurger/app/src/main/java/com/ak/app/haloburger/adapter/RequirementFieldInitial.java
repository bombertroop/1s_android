package com.ak.app.haloburger.adapter;

import android.widget.CheckBox;
import android.widget.EditText;

public class RequirementFieldInitial {

	private EditText editText;
	private CheckBox checkBox;
	private String type;
	private int point;

	public RequirementFieldInitial() {

	}

	public EditText getEditText() {
		return editText;
	}

	public void setEditText(EditText editText) {
		this.editText = editText;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
