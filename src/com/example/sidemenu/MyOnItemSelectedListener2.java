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

		//Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

		Text_Conversion.position=pos;
		
		switch(pos){
		case 0:
			
			Text_Conversion.to_code="en";
			Text_Conversion.to_lang="English";
			break;
		case 1:
			
			Text_Conversion.to_code="fr";
			Text_Conversion.to_lang="French";
			break;
		case 2:
			
			Text_Conversion.to_code="de";
			Text_Conversion.to_lang="German";
			break;
		
		case 3:
			
			Text_Conversion.to_code="vi";
			Text_Conversion.to_lang="Vietnamese";
			break;
		case 4:
			
			Text_Conversion.to_code="hi";
			Text_Conversion.to_lang="Hindi";
			break;
		case 5:
			
			Text_Conversion.to_code="el";
			Text_Conversion.to_lang="Geek";
			break;
		case 6:
			
			Text_Conversion.to_code="pl";
			Text_Conversion.to_lang="Polish";
			break;
		case 7:
			
			Text_Conversion.to_code="es";
			Text_Conversion.to_lang="Spanish";
			break;
		default:
			
			Text_Conversion.to_code="en";
			Text_Conversion.to_lang="English";
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
