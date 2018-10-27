package com.ak.app.haloburger.custom.ui;



import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

public class CustomEditEmail extends CustomEditText {

	public CustomEditEmail(Context context) {
		super(context);
	}

	public CustomEditEmail(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomEditEmail(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init() {

		View v = mInflater.inflate(R.layout.custom_edit_email, this, true);

		editText = (EditText) v.findViewById(R.id.edit_custom_email);
		editImage = (ImageView) v
				.findViewById(R.id.edit_action);

		editText.setFilters(new InputFilter[] { AppHelper.spaceFilter });
		AppHelper.clearEditText(editText, editImage);
		MyTypeface.setFieldFont(editText, MainActivity.getInstance());

	}

}
