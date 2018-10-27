package com.ak.app.haloburger.custom.ui;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;


public class CustomEditPasswordHint extends CustomEditText {

	private static InputFilter EMOJI_FILTER = new InputFilter() {

		@Override
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			for (int index = start; index < end; index++) {

				int type = Character.getType(source.charAt(index));

				if (type == Character.SURROGATE) {
					return "";
				}
			}
			return null;
		}
	};

	public CustomEditPasswordHint(Context context) {
		super(context);
	}

	public CustomEditPasswordHint(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomEditPasswordHint(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init() {

		View v = mInflater.inflate(R.layout.custom_edit_password_hint, this,
				true);

		editText = (EditText) v.findViewById(R.id.edit_custom_password_hint);
		editImage = (ImageView) v.findViewById(R.id.edit_action);

		editText.setFilters(new InputFilter[] { AppHelper.spaceFilter,
				EMOJI_FILTER });

		AppHelper.clearEditText(editText, editImage);
		MyTypeface.setFieldFont(editText, MainActivity.getInstance());
	}
}
