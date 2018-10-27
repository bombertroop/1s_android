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
import com.ak.app.haloburger.util.PhoneNumberFilter;
import com.ak.app.haloburger.util.PhoneNumberTextWatcher;


public class CustomEditPhone extends CustomEditText {

	public CustomEditPhone(Context context) {
		super(context);
	}

	public CustomEditPhone(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomEditPhone(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init() {

		View v = mInflater.inflate(R.layout.custom_edit_phone, this, true);

		editText = (EditText) v.findViewById(R.id.edit_custom_phone);
		editImage = (ImageView) v
				.findViewById(R.id.edit_action);

		editText.setFilters(new InputFilter[] { AppHelper.spaceFilter });
		editText.addTextChangedListener(new PhoneNumberTextWatcher());
		editText.setFilters(new InputFilter[] { new PhoneNumberFilter(),
				new InputFilter.LengthFilter(12) });
		AppHelper.clearEditText(editText, editImage);
		MyTypeface.setFieldFont(editText, MainActivity.getInstance());
	}

}
