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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        new GetJokesAsyncTask(
                new GetJokesAsyncTask.Listener() {
                    @Override
                    public void onJokeLoaded(String joke) {
                        Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
                        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
                        startActivity(intent);
                    }
                }
        ).execute();
    }
}

class GetJokesAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Listener mListener;

    public interface Listener{
        void onJokeLoaded(String joke);
    }

    public GetJokesAsyncTask(Listener listener){
        this.mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {

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
        mListener.onJokeLoaded(joke);
    }

}
