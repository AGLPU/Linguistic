package com.example.sidemenu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Text_Conversion extends SherlockActivity implements OnInitListener, OnUtteranceCompletedListener {
	static EditText my_text;
	//static String translated_string="";
	AlertDialog alertDialog=null;
	ImageButton btn_speak,btn_translate,btn_listen;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	protected static final int RESULT_SPEECH = 1;
	private static final String TAG = "TTS Demo";
	private TextView to,from;
	private TextToSpeech mTts;
	private int uttCount = 0;
	String to_lang,from_lang="";
	static String fromCode="en";
	static String toCode="en";
	private int lastUtterance = -1;
	private HashMap<String, String> params = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate_my_text);
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        my_text=(EditText) findViewById(R.id.editText1);
        to=(TextView) findViewById(R.id.to);
        from=(TextView) findViewById(R.id.from);
    	btn_speak=(ImageButton) findViewById(R.id.btn_speak);
		
		btn_listen=(ImageButton)findViewById(R.id.btn_listen);
       Log.d("test1","Intent1");
        Intent i=getIntent();
       Log.d("test2",i.getStringExtra("from_lang"));
       to_lang=i.getStringExtra("to_lang");
       from_lang=i.getStringExtra("from_lang");
        to.setText("From Language :"+i.getStringExtra("from_lang"));
        from.setText("To Language :"+i.getStringExtra("to_lang"));
        fromCode=i.getStringExtra("from");
        toCode=i.getStringExtra("to");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00497F")));
        
        
        btn_listen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
		                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

		        try {
		            startActivityForResult(intent, RESULT_SPEECH);
		            my_text.setText("");
		        } catch (ActivityNotFoundException a) {
		            Toast t = Toast.makeText(getApplicationContext(),
		                    "Opps! Your device doesn't support Speech to Text",
		                    Toast.LENGTH_SHORT);
		            t.show();
		        }
				
			}
		});
        
    /*    btn_translate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 /*TranslatorTask task = new TranslatorTask(Text_Conversion.this);
				 if( mTts != null)
				      mTts.stop();
				 mTts.shutdown();
				 //task.execute(my_text.getText().toString());
				 Intent i=new Intent(Text_Conversion.this,Converted_Text.class);
					i.putExtra("from_lang",from_lang);
					i.putExtra("to_lang",to_lang);
					i.putExtra("from",fromCode);
					i.putExtra("to",toCode);
					System.out.println(my_text.getText());
					Converted_Text.result=my_text.getText().toString();
					//i.putExtra("result", my_text.getText());
					startActivity(i);
					Text_Conversion.this.finish();
			}
		});
		
		*/
		
        
       
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
        mTts = new TextToSpeech(this,this);
        btn_speak.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doSpeak();
				
			}
		});
        
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        if (requestCode == REQ_TTS_STATUS_CHECK) {
            switch (resultCode) {
            case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
                //mTts = new TextToSpeech(this, this);
                //int result = mTts.setLanguage(Locale.ENGLISH);
                //mTts.setSpeechRate((float) 0.3);
                btn_speak.setOnClickListener(new View.OnClickListener() {
        			
        			@Override
        			public void onClick(View arg0) {
        				// TODO Auto-generated method stub
        				doSpeak();
        				
        			}
        		});
                break;
                
            case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
            case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
            case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                break;
            case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
            default:
                Log.e(TAG, "Got a failure. TTS not available");
            }
        }else if(requestCode==RESULT_SPEECH){
        	if (resultCode == RESULT_OK && null != data) {
        		 
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 
                my_text.setText(text.get(0));
            }
            
        	
        }
        
        
    }
	public void doSpeak() {
		
	    StringTokenizer st = new StringTokenizer(my_text.getText().toString(),",.");
	    while (st.hasMoreTokens()) {
	        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,String.valueOf(uttCount++));
	      mTts.speak(st.nextToken(), TextToSpeech.QUEUE_ADD, params);
	       }
	  }
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.quizback, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			 
            int result = mTts.setLanguage(Locale.ENGLISH);
            mTts.setSpeechRate((float) 0.5);
 
            mTts.setOnUtteranceCompletedListener(this);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                doSpeak();
            }
 
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
 
	  }
	 
	  @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		  super.onPause();
		    if( mTts != null)
		      mTts.stop();
		   //this.finish();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//super.onDestroy();
		super.onDestroy();
        mTts.shutdown();
	}
	public void onUtteranceCompleted(String uttId) {
	    lastUtterance = Integer.parseInt(uttId);
	  }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Text_Conversion.this);
		 
		// set title
		alertDialogBuilder.setTitle("Exit Application");
		
		alertDialogBuilder
			.setMessage(" Do you want to exit ?? ")
			.setCancelable(true)
			.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Intent i=new Intent(Text_Conversion.this,MainActivity.class);
				startActivity(i);
					
					
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.dismiss();
				}
			});
		// create alert dialog
		 alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if (item.getItemId() == R.id.menu_load ) {
        	//startActivity(new Intent(Text_Conversion.this,MainActivity.class));
        	
			 //task.execute(my_text.getText().toString());
			Intent i=new Intent(Text_Conversion.this,Converted_Text.class);
				i.putExtra("from_lang",from_lang);
				i.putExtra("to_lang",to_lang);
				i.putExtra("from",fromCode);
				i.putExtra("to",toCode);
				System.out.println(my_text.getText());
				Converted_Text.result=my_text.getText().toString();
				//i.putExtra("result", my_text.getText());
				startActivity(i);
				//Text_Conversion.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
