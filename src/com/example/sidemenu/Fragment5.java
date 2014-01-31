package com.example.sidemenu;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.utils.RowItem;

public class Fragment5 extends SherlockFragment implements
OnItemClickListener {
	ListView listView;
	List<RowItem> rowItems;
	
	 public static final String[] titles = new String[] { "Ajay Jaiswal",
         "Dharmendra", "Aman Goel", "Deeraj Bhatra" };

	 public static final String[] descriptions = new String[] {
         "Software Intern At VenturePact Technologies",
         "Software Intern At VenturePact Technologies",
         "Software Intern At VenturePact Technologies",
         "Software Intern At VenturePact Technologies" };

	 public static final Integer[] images = { R.drawable.about,
         R.drawable.about, R.drawable.about, R.drawable.about };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment5, container, false);
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }
 
        listView = (ListView) rootView.findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(getActivity(),
                R.layout.mylayout, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        //Toast toast = Toast.makeText(getActivity(),
           // "Item " + (position + 1) + ": " + rowItems.get(position),
           // Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        //toast.show();
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ajayjaiswalbingo"));
    	startActivity(browserIntent);
    }
 
}