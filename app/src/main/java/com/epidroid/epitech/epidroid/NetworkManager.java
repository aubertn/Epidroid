package com.epidroid.epitech.epidroid;

import android.app.Activity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aubert_n on 15/01/2015.
 */

public class NetworkManager extends Activity{

    public void loginConnect (final String address, final String login, final String password) {

        System.out.println("BEFORE THREAD");
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpParams httpParameters = new BasicHttpParams();
                    HttpClient client = new DefaultHttpClient(httpParameters);
                    HttpPost request = new HttpPost(address);
                    System.out.println("TRY TO ACCESS : "+address);
                    List<NameValuePair> nameValuePairs = new ArrayList<>(2);

                    System.out.println("DEBUG |LOGIN :"+login+" |PASSWORD:"+password); //debug

                    nameValuePairs.add(new BasicNameValuePair("login", login));
                    nameValuePairs.add(new BasicNameValuePair("pass", password));

                    request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = client.execute(request); //  UNAUTHORIZED
                    String responseString = new BasicResponseHandler().handleResponse(response);
                    System.out.println("BEGIN****");
                    System.out.println(responseString);
                    System.out.println("****END");
                } catch (IOException e) {
                    System.out.println("CANT ACCESS : ERROR IS ["+e+"]");
                }
            }
        }).start();
    }
}
