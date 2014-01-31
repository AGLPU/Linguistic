package com.example.sidemenu;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
 
public class Fragment3 extends SherlockFragment {
	public static int SPLASH_TIME_OUT=3000;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		  View rootView = inflater.inflate(R.layout.fragment3, container, false);
	        String htmlAsAString="<html>" +
	        		"<body>" +
	        		"Lingistic is an android application that acts as an interpretation medium in between " +
	        		"two people of different Locales. It also helps people to learn a series of different languages." +
	        		"It not help people to convert text from one language to another language but also " +
	        		"actually deals with the voice recognization and speakes a series of languages like" +
	        		"Greek, English, French , Spanish and a lot more ." +
	        		"It is a lot more flexible and beautifully designed with a simple and ease interface."+"</body>"+
	        		"</html>";
	        TextView description=(TextView) rootView.findViewById(R.id.helpdescription);
	        description.setText(Html.fromHtml(htmlAsAString));
	        TextView help=(TextView) rootView.findViewById(R.id.help);
	        help.setText(Html.fromHtml("<b><u>HELP (How to USE !!)</u></b>"));
	        description.setMovementMethod(new ScrollingMovementMethod());
	        return rootView;
    }
 
}