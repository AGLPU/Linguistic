package com.example.sidemenu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelectedListener2 implements OnItemSelectedListener {

	//to_language
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub

		Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

		switch(pos){
		case 0:
			Fragment1.to="en";
			Fragment1.to_lang="English";
			break;
		case 1:
			Fragment1.to="fr";
			Fragment1.to_lang="French";
			break;
		case 2:
			Fragment1.to="de";
			Fragment1.to_lang="German";
			break;
		
		case 3:
			Fragment1.to="vi";
			Fragment1.to_lang="Vietnamese";
			break;
		case 4:
			Fragment1.to="hi";
			Fragment1.to_lang="Hindi";
			break;
		case 5:
			Fragment1.to="el";
			Fragment1.to_lang="Geek";
			break;
		case 6:
			Fragment1.to="pl";
			Fragment1.to_lang="Polish";
			break;
		case 7:
			Fragment1.to="es";
			Fragment1.to_lang="Spanish";
			break;
		default:
			Fragment1.to="en";
			Fragment1.to_lang="English";
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
