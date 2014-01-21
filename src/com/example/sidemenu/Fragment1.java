package com.example.sidemenu;

import com.actionbarsherlock.app.SherlockFragment;
import com.utils.UtilityData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Fragment1 extends SherlockFragment {
	ImageButton btn_speak;
	Spinner from_language, to_language;
	static String from = "en";
	static String to = "en";
	static String from_lang = "English";
	static String to_lang = "English";
	View rootView;

	@Override
	public void onResume() {
		getDefaultValue();
		super.onResume();
	}

	@Override
	public void onStart() {
		getDefaultValue();
		super.onStart();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		intitializeControls(inflater, container);
		getDefaultValue();
		return rootView;
	}

	private void intitializeControls(LayoutInflater inflater,
			ViewGroup container) {
		rootView = inflater.inflate(R.layout.fragment1, container, false);
		from_language = (Spinner) rootView.findViewById(R.id.fromCountry);

		to_language = (Spinner) rootView.findViewById(R.id.toCountry);

		from_language.setOnItemSelectedListener(new MyOnItemSelectedListener());

		to_language.setOnItemSelectedListener(new MyOnItemSelectedListener2());

		btn_speak = (ImageButton) rootView.findViewById(R.id.btn_speak);

		btn_speak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getActivity(), Text_Conversion.class);
				i.putExtra("from_lang", from_lang);
				i.putExtra("to_lang", to_lang);
				i.putExtra("from", from);
				i.putExtra("to", to);
				startActivity(i);

			}
		});
	}

	// To set Default Value of spinner
	private void getDefaultValue() {
		SharedPreferences sharedPrefrence = this.getActivity()
				.getSharedPreferences(UtilityData.KEY_TAG,
						getActivity().MODE_PRIVATE);
		from_language.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_FROM, 0));
		to_language.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_TO, 0));

	}
}
