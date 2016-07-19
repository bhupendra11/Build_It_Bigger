package com.bhupendra.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bhupendra.gradle.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import bhupendrashekhawat.me.joketellerandroidlib.JokeActivity;


public class MainActivity extends ActionBarActivity {

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext =getApplicationContext();
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view){

        new EndpointsAsyncTask().execute(mContext);
        /*Joker joker = new Joker();
        String joke =joker.getJoke();

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("JOKE", joke);
        startActivity(intent);
*/
        // Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }

    public void startJokeActivity(String joke){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("JOKE", joke);
        startActivity(intent);
    }



}

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;


    @Override
    protected String doInBackground(Context... params) {

        mContext = params[0];

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://dotted-wind-137723.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }


    @Override
    protected void onPostExecute(String joke) {
        // Toast.makeText(context, result, Toast.LENGTH_LONG).show();


       Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra("JOKE", joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);



    }

}
