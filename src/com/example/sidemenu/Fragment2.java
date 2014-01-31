package com.example.sidemenu;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class Fragment2 extends SherlockFragment {
	 int counter;
	    databasehelper db;
	    ProductTable model1;
		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment2, container, false);
	       
		Button	btn1 = (Button) rootView.findViewById(R.id.button1);
		TextView	textview1=(TextView) rootView.findViewById(R.id.textview1);
	        counter=getItem();
			if(counter==20000)
			{
				textview1.setText("You have already Subscribed");
			    btn1.setVisibility(View.INVISIBLE);
			}
	     btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intent1=new Intent(getActivity(),PaypalActivity.class);
			startActivity(intent1);
			}
		});
	        return rootView;
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
				
				
				Log.d("ckckc", String.valueOf(counter));
				
				return counter;
				
			}
	}

