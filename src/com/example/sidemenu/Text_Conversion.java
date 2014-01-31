package com.example.sidemenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.utils.UtilityData;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Text_Conversion extends SherlockActivity implements
		OnInitListener, OnUtteranceCompletedListener {


	private static final int REQ_TTS_STATUS_CHECK = 0;

	protected static final int RESULT_SPEECH = 1;
	private static final int PICK_CONTACT = 0;
	AlertDialog alertDialog = null;
	private static final String TAG = "TTS Demo";
	String number,id;
	private TextToSpeech mTts;
	private int uttCount = 0;
	private int lastUtterance = -1;
	private HashMap<String, String> params = new HashMap<String, String>();

	static int position=0;
	SharedPreferences sharedPrefrence;
	
	static EditText from_language, to_language;
	Button to_speak,from_speak,from_listen,translate;
	Spinner choose_language,choose_from_language;
	static String from_lang,to_lang;
	static String from_code="";
	static String to_code="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate_my_text);
		
				// Enable ActionBar app icon to behave as action to toggle nav drawer
				//getSupportActionBar().setHomeButtonEnabled(false);
				//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

				// Set Action color
				getSupportActionBar().setBackgroundDrawable(
						new ColorDrawable(Color.parseColor("#2F302B")));
				getSupportActionBar().setTitle("Linguistic");
		
		initializeControls();
		
		choose_language.setOnItemSelectedListener(new MyOnItemSelectedListener());
		choose_from_language.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				

				switch(pos){
				case 0:
					Text_Conversion.to_lang="English";
					Text_Conversion.to_code="en";
					break;
				case 1:
					Text_Conversion.to_lang="French";
					Text_Conversion.to_code="fr";
					break;
				case 2:
					Text_Conversion.to_lang="German";
					Text_Conversion.to_code="de";
					break;
				
				case 3:
					Text_Conversion.to_lang="Vietnamese";
					Text_Conversion.to_code="vi";
					break;
				case 4:
					Text_Conversion.to_lang="Hindi";
					Text_Conversion.to_code="hi";
					break;
				case 5:
					Text_Conversion.to_lang="Greek";
					Text_Conversion.to_code="el";
					break;
				case 6:
					Text_Conversion.to_lang="Polish";
					Text_Conversion.to_code="pl";
					break;
				case 7:
					Text_Conversion.to_lang="Spanish";
					Text_Conversion.to_code="es";
					break;
				default:
					Text_Conversion.to_lang="English";
					Text_Conversion.to_code="en";
				}
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

			
		});
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
		mTts = new TextToSpeech(this, this);
		from_listen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);
					
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Opps! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
				
			}
		});
		from_speak.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mTts.setLanguage(new Locale(from_code));
				doSpeak("from");
				
			}
		});
		to_speak.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mTts.setLanguage(new Locale(to_code));
				doSpeak("to");
			}
		});
		translate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!from_language.getText().toString().equals("")){
					TranslatorTask task = new TranslatorTask(Text_Conversion.this);
					task.execute(from_language.getText().toString());
					}
				
			}
		});
		
		
	}
	private void initializeControls(){
		from_speak=(Button)findViewById(R.id.button2);
		to_speak=(Button)findViewById(R.id.button3);
		from_listen=(Button)findViewById(R.id.button1);
		//to_listen=(ImageButton)findViewById(R.id.button4);
		translate=(Button)findViewById(R.id.button5);
		from_language=(EditText)findViewById(R.id.editText1);
		to_language=(EditText)findViewById(R.id.editText2);
		
		choose_language=(Spinner) findViewById(R.id.spinner1);
		choose_from_language=(Spinner) findViewById(R.id.spinner2);
		choose_language.setSelection(position);
		sharedPrefrence = getSharedPreferences(
				UtilityData.KEY_TAG, MODE_PRIVATE);
		choose_from_language.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_FROM, 0));
		choose_language.setSelection(sharedPrefrence.getInt(
				UtilityData.KEY_TRANSLATE_TO, 0));
		
	}
	
	public void doSpeak(String type) {

		StringTokenizer st;
		if(type.equals("from")){
			 st = new StringTokenizer(from_language.getText().toString(),
					",.");
		}else{
			 st = new StringTokenizer(to_language.getText().toString(),
					",.");
		}
		
		while (st.hasMoreTokens()) {
			params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
					String.valueOf(uttCount++));
			mTts.speak(st.nextToken(), TextToSpeech.QUEUE_ADD, params);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == RESULT_SPEECH) {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				from_language.setText(text.get(0));
			}
		}else if(requestCode == PICK_CONTACT){
			if (resultCode == RESULT_OK) {
		        Uri contactData = data.getData();
		        Cursor c =  getContentResolver().query(contactData, null, null, null, null);
		        if (c.moveToFirst()) {
		          String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		         //String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		          id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

		          if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
			            Cursor pCur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id },
			                    null);
			            while (pCur.moveToNext()) {
			                number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			            
			            }
			        }
		          
		          Toast.makeText(getApplicationContext(),number, Toast.LENGTH_LONG).show();
		          // TODO Whatever you want to do with the selected contact name.
		      
					String message=to_language.getText().toString();
					if(!message.equals("")){
					Intent smsIntent = new Intent(Intent.ACTION_VIEW);

			            smsIntent.putExtra("sms_body", message); 
			            smsIntent.putExtra("address", number);
			            smsIntent.setType("vnd.android-dir/mms-sms");

			            startActivity(smsIntent);
					}
		        }
		        
		      }else if(requestCode == REQ_TTS_STATUS_CHECK){
					switch (resultCode) {
					case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
						
						from_speak.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								doSpeak("from");

							}
						});
						to_speak.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								
								doSpeak("to");
							}
						});
						break;

					case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
					case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
					case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
						Intent installIntent = new Intent();
						installIntent
								.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
						startActivity(installIntent);
						break;
					case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
					default:
						Log.e(TAG, "Got a failure. TTS not available");
					}
					
				}
		}
	}
	
	
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result =mTts.setLanguage(new Locale(from_code));
			mTts.setSpeechRate((float) 0.5);

			mTts.setOnUtteranceCompletedListener(this);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				doSpeak("from");
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mTts != null)
			mTts.stop();
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// super.onDestroy();
		super.onDestroy();
		mTts.shutdown();
	}

	public void onUtteranceCompleted(String uttId) {
		lastUtterance = Integer.parseInt(uttId);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Text_Conversion.this);

		// set title
		alertDialogBuilder.setTitle("Exit Application");

		alertDialogBuilder
				.setMessage(" Do you want to exit ?? ")
				.setCancelable(true)
				.setPositiveButton("Exit",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent i=new Intent(Text_Conversion.this,MainActivity.class);
								startActivity(i);
								finish();

							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								alertDialog.dismiss();
							}
						});
		// create alert dialog
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
       return true;
    	
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if (item.getItemId() == R.id.menu_load ) {
        	String message=to_language.getText().toString();
			if(!message.equals("")){
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}else{
				Toast.makeText(this, "Nothing to Share !!", Toast.LENGTH_SHORT).show();
			}
        }else if (item.getItemId() == R.id.share){
        	
        	
        }
        return super.onOptionsItemSelected(item);
    }


}
