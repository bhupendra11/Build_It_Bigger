package com.bhupendra.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


import com.bhupendra.gradle.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by Bhupendra Singh on 27/7/16.
 */

public class GetJokesAsyncTask extends AsyncTask<Void, Void, String> {
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
