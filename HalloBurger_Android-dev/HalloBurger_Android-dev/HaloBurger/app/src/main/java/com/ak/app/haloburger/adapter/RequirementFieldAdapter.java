package com.ak.app.haloburger.adapter;



import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.ak.app.haloburger.util.AppHelper;

import java.util.ArrayList;

public class RequirementFieldAdapter {

	private ArrayList<RequirementFieldInitial> RFList;

	private ImageView button;
	private RelativeLayout buttonRelative;
	private int totalPoint;

	public RequirementFieldAdapter(ImageView button) {
		RFList = new ArrayList<RequirementFieldInitial>();
		this.button = button;
		AppHelper.setEnableButton(button, false);
	}

	public RequirementFieldAdapter(RelativeLayout button) {
		RFList = new ArrayList<RequirementFieldInitial>();
		this.buttonRelative = button;
		AppHelper.setEnableButton(button, false);
	}

	public void addRequirementField(EditText editText, String type) {
		RequirementFieldInitial RFInitial = new RequirementFieldInitial();
		SetTextWatcherForAmountEditView(editText);
		RFInitial.setEditText(editText);
		RFInitial.setType(type);
		RFList.add(RFInitial);

	}

	public void addRequirementField(CheckBox checkBox, String type) {
		RequirementFieldInitial RFInitial = new RequirementFieldInitial();
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				checkRequirementField();
			}
		});
		RFInitial.setCheckBox(checkBox);
		RFInitial.setType(type);
		RFList.add(RFInitial);

	}

	private void SetTextWatcherForAmountEditView(final EditText amountEditText) {
		TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				checkRequirementField();
			}

		};
		amountEditText.addTextChangedListener(fieldValidatorTextWatcher);
	}

	private int checkEmailRequirement(EditText editText) {
		if (AppHelper.filterLongEnough(editText)
				/*&& AppHelper.isValidEmail(editText.getText().toString())*/) {
			return 1;
		} else {
			return 0;
		}
	}

	private int checkRequirementPassword(EditText editText) {
		if (AppHelper.filterLongEnoughPassword(editText))
			return 1;
		else
			return 0;
	}

	private int checkRequirementCheckBox(CheckBox checkBox) {
		if (checkBox.isChecked())
			return 1;
		else
			return 0;
	}

	private int checkRequirementName(EditText editText) {
		if (AppHelper.filterLongEnoughName(editText))
			return 1;
		else
			return 0;
	}

	private int checkRequirementPhoneNumber(EditText editText) {
		if (AppHelper.filterLongEnoughPhone(editText))
			return 1;
		else
			return 0;
	}

	private int checkRequirementLocation(EditText editText) {
		if (AppHelper.filterLongEnoughName(editText))
			return 1;
		else
			return 0;
	}

	private int checkCommonEditRequirement(EditText editText) {
		if (AppHelper.filterLongEnough(editText)) {
			return 1;
		} else {
			return 0;
		}
	}

	private int getPoint(RequirementFieldInitial RF) {
		String type = RF.getType();
		int tampPoint = 0;
		switch (type) {
		case AppHelper.EDIT_EMAIL:
			tampPoint = checkEmailRequirement(RF.getEditText());
			Log.i("elang", "elang cek EDIT_EMAIL " + tampPoint);
			break;
		case AppHelper.EDIT_PASSWORD:
			tampPoint = checkRequirementPassword(RF.getEditText());
			Log.i("elang", "elang cek EDIT_PASSWORD " + tampPoint);
			break;
		case AppHelper.EDIT_NAME:
			tampPoint = checkRequirementName(RF.getEditText());
			Log.i("elang", "elang cek EDIT_NAME " + tampPoint);
			break;
		case AppHelper.EDIT_PHONE:
			tampPoint = checkRequirementPhoneNumber(RF.getEditText());
			Log.i("elang", "elang cek EDIT_PHONE " + tampPoint);
			break;
		case AppHelper.EDIT_LOCATION:
			tampPoint = checkRequirementLocation(RF.getEditText());
			Log.i("elang", "elang cek EDIT_LOCATION " + tampPoint);
			break;
		case AppHelper.CHECKBOX:
			tampPoint = checkRequirementCheckBox(RF.getCheckBox());
			Log.i("elang", "elang cek CHECKBOX " + tampPoint);
			break;
		case AppHelper.EDIT_COMMON:
			tampPoint = checkCommonEditRequirement(RF.getEditText());

			break;
		default:
			break;
		}

		return tampPoint;
	}

	public void checkRequirementField() {
		totalPoint = 0;
		for (int i = 0; i < RFList.size(); i++) {
			RequirementFieldInitial RFTamp = RFList.get(i);

			RFTamp.setPoint(getPoint(RFTamp));

			totalPoint = totalPoint + RFTamp.getPoint();
		}

		Log.i("elang", "elang RF p: " + totalPoint);
		Log.i("elang", "elang RF s: " + RFList.size());


		if (totalPoint == RFList.size()) {
		    if (button!=null)
		        AppHelper.setEnableButton(button, true);
		    else
                AppHelper.setEnableButton(buttonRelative, true);

		} else {
            if (button!=null)
                AppHelper.setEnableButton(button, false);
            else
                AppHelper.setEnableButton(buttonRelative, false);

		}
	}
}
