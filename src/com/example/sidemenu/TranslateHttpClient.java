package com.example.sidemenu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class TranslateHttpClient {
	private static String BASE_URL = "http://mymemory.translated.net/api/get?q=";
	public String getTranslateData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;
        String newLocation=location.replaceAll(" ", "%20");
        System.out.println(newLocation);

        try {
        	Log.d("URL",BASE_URL+newLocation+"&langpair="+Text_Conversion.fromCode+"|"+Text_Conversion.toCode);
            con = (HttpURLConnection) ( new URL(BASE_URL+newLocation+"&langpair="+Text_Conversion.fromCode+"|"+Text_Conversion.toCode)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            
            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");
            
            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
                
    }

}
