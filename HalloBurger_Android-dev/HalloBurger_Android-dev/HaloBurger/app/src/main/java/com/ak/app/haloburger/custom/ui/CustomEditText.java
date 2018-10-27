package com.ak.app.haloburger.custom.ui;



import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

public class CustomEditText extends RelativeLayout {

	LayoutInflater mInflater;
	EditText editText;
	ImageView editImage;

	public CustomEditText(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		init();
	}

	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mInflater = LayoutInflater.from(context);
		init();
	}

	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mInflater = LayoutInflater.from(context);
		init();
	}

	public void init() {

		View v = mInflater.inflate(R.layout.custom_edit_text, this, true);

		editText = (EditText) v.findViewById(R.id.edit_custom);
		editImage = (ImageView) v.findViewById(R.id.edit_action);

		editText.setFilters(new InputFilter[] { AppHelper.spaceFilter });
		AppHelper.clearEditText(editText, editImage);
		MyTypeface.setFieldFont(editText, MainActivity.getInstance());
	}

	public EditText getEditText() {
		return this.editText;
	}

	public void setHint(int resid) {
		getEditText().setHint(resid);
	}

	public ImageView getButton() {
		return this.editImage;
	}
}
