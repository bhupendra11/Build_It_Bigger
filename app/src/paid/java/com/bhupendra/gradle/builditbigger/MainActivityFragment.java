package com.bhupendra.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import bhupendrashekhawat.me.joketellerandroidlib.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Button mButton;
    private Context mContext;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = root.getContext();
        mButton = (Button) root.findViewById(R.id.showJokeButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetJokesAsyncTask(
                        new GetJokesAsyncTask.Listener() {
                            @Override
                            public void onJokeLoaded(String joke) {
                                Intent intent = new Intent(mContext, JokeActivity.class);
                                intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
                                startActivity(intent);
                            }
                        }
                ).execute();

            }
        });

        return root;

    }

}
