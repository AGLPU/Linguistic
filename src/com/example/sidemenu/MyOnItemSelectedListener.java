package com.example.sidemenu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

		switch(pos){
		case 0:
			Fragment1.from="en";
			Fragment1.from_lang="English";
			break;
		case 1:
			Fragment1.from="fr";
			Fragment1.from_lang="French";
			break;
		case 2:
			Fragment1.from="de";
			Fragment1.from_lang="German";
			break;
		
		case 3:
			Fragment1.from="vi";
			Fragment1.from_lang="Vietnamese";
			break;
		case 4:
			Fragment1.from="hi";
			Fragment1.from_lang="Hindi";
			break;
		case 5:
			Fragment1.from="el";
			Fragment1.from_lang="Greek";
			break;
		case 6:
			Fragment1.from="pl";
			Fragment1.from_lang="Polish";
			break;
		case 7:
			Fragment1.from="es";
			Fragment1.from_lang="Spanish";
			break;
		default:
			Fragment1.from="en";
			Fragment1.from_lang="English";
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
