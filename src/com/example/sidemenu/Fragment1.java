package com.example.sidemenu;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import com.utils.InternetConnection;
import com.utils.UtilityData;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Fragment1 extends SherlockFragment {
    Boolean isInternetPresent=false;
    AlertDialog alertDialog=null;
    databasehelper db;
    ProductTable model1;
    int counter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        Button btn=(Button) rootView.findViewById(R.id.noSharing);
        final InternetConnection internetConnection=new InternetConnection(getActivity());
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// get Internet status
				counter=	getItem();
				if(counter!=20000)
				{
				counter++;
				additem(counter);
					
					
				
                isInternetPresent = internetConnection.isConnectingToInternet();
 
                // check for Internet status
                if (!isInternetPresent) {
                	Intent i=new Intent(getActivity(), Text_Conversion.class);
    				startActivity(i);
                    
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                	// TODO Auto-generated method stub
            		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            				getActivity());

            		// set title
            		alertDialogBuilder.setTitle("You have no Internet Connection");

            		alertDialogBuilder
            				.setMessage(" Do you want to exit ?? ")
            				.setCancelable(true)
            				.setPositiveButton("Exit",
            						new DialogInterface.OnClickListener() {

            							@Override
            							public void onClick(DialogInterface dialog,
            									int which) {
            								

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
				}
				
				
			}
		});
        
        return rootView;
    }
 
	public void additem(int counter) {
		//counter++;
		db = new databasehelper(getActivity());
		db.getWritableDatabase();
		model1 = new ProductTable();
		model1.idno = counter;
		// model1.idno=24;
		db.addProduct(model1);
		// Log.d("id",model1.idno);
	//	Toast.makeText(getActivity(), "Your New data has been saved.",
			//	Toast.LENGTH_LONG).show();
	}

	 public int getItem() {
			Object tb = null;
			db = new databasehelper(getActivity());
			db.getWritableDatabase();
			model1 = new ProductTable();

			db.getproducts();
			Log.d("amama", "amaj");
			// Stack st=new Stack();
			ArrayList al = new ArrayList();

			al = db.getproducts();
			int size = al.size();
			if (size == 0)
				tb = 0;
			else
				tb = al.get(size - 1);
			int tb1 = (Integer) tb;
			counter = tb1;
			//Toast.makeText(getActivity(), String.valueOf(counter),
				//	Toast.LENGTH_LONG).show();
			
			Log.d("ckckc", String.valueOf(counter));
			
			return counter;
			
		}

}




