package com.epidroid.epitech.epidroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
    public NetworkManager netManager = new NetworkManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onButtonConnectClick();
    }

    public void onButtonConnectClick(){
        Button btnConnect = (Button)findViewById(R.id.buttonConnect);
        btnConnect.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg)
            {
                String login, pwd;
                EditText e1 = (EditText)findViewById(R.id.login);
                EditText e2 = (EditText)findViewById(R.id.password);
                login = e1.getText().toString();
                pwd = e2.getText().toString();
                netManager.loginConnect("http://epitech-api.herokuapp.com/login", login, pwd);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
