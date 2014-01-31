package com.example.sidemenu;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONTextParser {
	public static Text getText(String data) throws JSONException {
		// TODO Auto-generated method stub
		Text x=new Text();
		JSONObject jObj = new JSONObject(data);
		JSONObject response = jObj.getJSONObject("responseData");
		String responseText=response.getString("translatedText");
		String finalstring=responseText.replaceAll("&#39;","'");
		x.responseText=finalstring;
		return x;
	}

}
