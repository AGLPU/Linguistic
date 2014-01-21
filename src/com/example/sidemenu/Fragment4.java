package com.example.sidemenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.utils.UtilityData;

public class Fragment4 extends SherlockFragment {

	Spinner translate_from, translate_to;
	SharedPreferences sharedPrefrence;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment4, container,
				false);

		sharedPrefrence = this.getActivity().getSharedPreferences(
				UtilityData.KEY_TAG, getActivity().MODE_PRIVATE);

		translate_from = (Spinner) rootView.findViewById(R.id.spinner1);
		translate_to = (Spinner) rootView.findViewById(R.id.spinner2);

		translate_from.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_FROM, 0));
		translate_to.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_TO, 0));

		translate_from.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = sharedPrefrence.edit();
				editor.putInt(UtilityData.KEY_TRANSLATE_FROM, pos);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		translate_to.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = sharedPrefrence.edit();
				editor.putInt(UtilityData.KEY_TRANSLATE_TO, pos);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		return rootView;
	}
}