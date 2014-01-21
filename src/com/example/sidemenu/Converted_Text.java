package com.example.sidemenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class Converted_Text extends SherlockActivity implements OnInitListener, OnUtteranceCompletedListener {
	static EditText my_text;
	static String result="";
	AlertDialog alertDialog=null;
	ImageButton btn_speak,btn_share;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	protected static final int RESULT_SPEECH = 1;
	private static final int PICK_CONTACT = 0;
	private static final String TAG = "TTS Demo";
	private TextView to,from;
	private TextToSpeech mTts;
	private int uttCount = 0;
	static String fromCode="en";
	static String toCode="en";
	private int lastUtterance = -1;
	private HashMap<String, String> params = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.converted_text);
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        my_text=(EditText) findViewById(R.id.editText1);
        to=(TextView) findViewById(R.id.to);
        from=(TextView) findViewById(R.id.from);
    	btn_speak=(ImageButton) findViewById(R.id.btn_speak);
		
		btn_share=(ImageButton)findViewById(R.id.btn_share);
       Log.d("test3","Intent");
        Intent i=getIntent();
       Log.d("test4",i.getStringExtra("from_lang"));
       //Log.d("test5",i.getStringExtra("translated_string"));
        to.setText("From Language :"+i.getStringExtra("from_lang"));
        from.setText("To Language :"+i.getStringExtra("to_lang"));
        fromCode=i.getStringExtra("fromCode");
        toCode=i.getStringExtra("toCode");
       TranslatorTask task = new TranslatorTask(Converted_Text.this);
		 
	   task.execute(result);
        my_text.setText(result);
        //System.out.print("The retrived record is :"+i.getStringExtra("result"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00497F")));
        
        
        btn_share.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
				
			}
		});
        
        mTts = new TextToSpeech(this,this);
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
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
 
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
               // doSpeak();
            }
 
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
 
	  }
	 
	  @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void onUtteranceCompleted(String uttId) {
	    lastUtterance = Integer.parseInt(uttId);
	  }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Converted_Text.this);
		 
		// set title
		alertDialogBuilder.setTitle("Exit Application");
		
		alertDialogBuilder
			.setMessage(" Do you want to exit ?? ")
			.setCancelable(true)
			.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Intent i=new Intent(Converted_Text.this,MainActivity.class);
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
        	startActivity(new Intent(Converted_Text.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


}
