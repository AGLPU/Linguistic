package com.example.sidemenu;

import org.json.JSONException;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TranslatorTask extends AsyncTask<String, Integer, String>{
	private ProgressDialog dialog;
	Context context;
	public TranslatorTask(Context context){
		this.context=context;
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub 
				Text weather = new Text();
			    String data = ( (new TranslateHttpClient()).getTranslateData(arg0[0]));
			    System.out.println(data);
			    try {
			        weather = JSONTextParser.getText(data);
			        System.out.println(weather.responseText);
			        
			    } catch (JSONException e) {                
			        e.printStackTrace();
			    }
			   
			    return weather.responseText;
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		dialog=new ProgressDialog(context);
		this.dialog.setMessage("Translating ... Please Wait !!");
        this.dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//MainActivity.result=result;
		//MainActivity.tv.setText(result);
		dialog.dismiss();
		//Text_Conversion.my_text.setText(result);
		Converted_Text.my_text.setText(result);
		//Text_Conversion.translated_string=result;
	}


}
