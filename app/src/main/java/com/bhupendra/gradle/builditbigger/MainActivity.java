package com.bhupendra.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import bhupendrashekhawat.me.joketellerandroidlib.JokeActivity;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

