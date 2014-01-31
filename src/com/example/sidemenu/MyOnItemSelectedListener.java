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
		//Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();

		switch(pos){
		case 0:
			
			Text_Conversion.from_lang="English";
			Text_Conversion.from_code="en";
			break;
		case 1:
			
			Text_Conversion.from_lang="French";
			Text_Conversion.from_code="fr";
			break;
		case 2:
			
			Text_Conversion.from_lang="German";
			Text_Conversion.from_code="de";
			break;
		
		case 3:
			
			Text_Conversion.from_lang="Vietnamese";
			Text_Conversion.from_code="vi";
			break;
		case 4:
			
			Text_Conversion.from_lang="Hindi";
			Text_Conversion.from_code="hi";
			break;
		case 5:
			
			Text_Conversion.from_lang="Greek";
			Text_Conversion.from_code="el";
			break;
		case 6:
			
			Text_Conversion.from_lang="Polish";
			Text_Conversion.from_code="pl";
			break;
		case 7:
			
			Text_Conversion.from_lang="Spanish";
			Text_Conversion.from_code="es";
			break;
		default:
			
			Text_Conversion.from_lang="English";
			Text_Conversion.from_code="en";
		}
		
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
