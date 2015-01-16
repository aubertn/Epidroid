package com.epidroid.epitech.epidroid;

import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aubert_n on 15/01/2015.
 */

public class NetworkManager {

    public static void getJSON(String address) {


        System.out.println("BEFORE THREAD");
        new Thread(new Runnable() {
            //Thread to stop network calls on the UI thread
            public void run() {
                //Request the HTML
                try {
                    HttpParams httpParameters = new BasicHttpParams();
                  //  HttpConnectionParams.setConnectionTimeout(httpParameters, 10);
                  //  HttpConnectionParams.setSoTimeout(httpParameters, 10);

                    HttpClient client = new DefaultHttpClient(httpParameters);
                    HttpPost request = new HttpPost("http://epitech-api.herokuapp.com/");
                    System.out.println("TRY TO ACCESS : http://epitech-api.herokuapp.com/");
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("login", R.id.login));
                    nameValuePairs.add(new BasicNameValuePair("password", R.id.password));
                    request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = client.execute(request);

                    String responseString = new BasicResponseHandler().handleResponse(response);
                    System.out.println(responseString);

                    //Do something with the response
                } catch (IOException e) {
                    Log.e("Tag", "Could not get HTML: " + e.getMessage());
                }
            }
        }).start();
    }
}
        /*


        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        try{
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
            } else {
                Log.e(MainActivity.class.toString(), "Failedet JSON object");
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }*/

