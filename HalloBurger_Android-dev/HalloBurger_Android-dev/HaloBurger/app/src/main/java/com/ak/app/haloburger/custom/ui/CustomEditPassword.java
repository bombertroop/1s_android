package com.ak.app.haloburger.custom.ui;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;


public class CustomEditPassword extends CustomEditText {

	private Boolean isPasswordShowing = false;

	public CustomEditPassword(Context context) {
		super(context);
	}

	public CustomEditPassword(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomEditPassword(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init() {

		View v = mInflater.inflate(R.layout.custom_edit_password, this, true);

		editText = (EditText) v.findViewById(R.id.edit_custom_password);
		editImage = (ImageView) v.findViewById(R.id.edit_action);
		MyTypeface.setFieldFont(editText, MainActivity.getInstance());
		editText.setFilters(new InputFilter[] { AppHelper.spaceFilter });

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub

				if ((!hasFocus)) {
					editImage.setVisibility(View.INVISIBLE);
				} else {
					editImage.setVisibility(View.VISIBLE);
				}

			}
		});

		editImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isPasswordShowing) {
					AppHelper.showPassword(isPasswordShowing, editText,
							editImage);
					isPasswordShowing = false;

				} else {
					AppHelper.showPassword(isPasswordShowing, editText,
							editImage);
					isPasswordShowing = true;

				}
			}
		});

	}
}
