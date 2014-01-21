package com.example.sidemenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
 
public class Fragment1 extends SherlockFragment {
	ImageButton btn_speak;
	Spinner from_language,to_language;
	static String from="en";
	static String to="en";
	static String from_lang="English";
	static String to_lang="English";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        from_language=(Spinner) rootView.findViewById(R.id.fromCountry);
		to_language=(Spinner) rootView.findViewById(R.id.toCountry);
		from_language.setOnItemSelectedListener(new MyOnItemSelectedListener());
		to_language.setOnItemSelectedListener(new MyOnItemSelectedListener2());
        //btn_listen=(ImageButton)rootView.findViewById(R.id.btnSpeak1);
        btn_speak=(ImageButton)rootView.findViewById(R.id.btn_speak);
        btn_speak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getActivity(),Text_Conversion.class);
				i.putExtra("from_lang",from_lang);
				i.putExtra("to_lang",to_lang);
				i.putExtra("from",from);
				i.putExtra("to",to);
				startActivity(i);
				
			}
		});
       
		return rootView;
		
 
	}
	
}
