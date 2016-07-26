package com.bhupendra.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by Bhupendra Singh on 20/7/16.
 */

public class GetJokeTest extends AndroidTestCase {


    public void testNonEmptyJoke() {

        new GetJokesAsyncTask(
                new GetJokesAsyncTask.Listener() {
                    @Override
                    public void onJokeLoaded(String joke) {
                        assert joke instanceof String ;
                        assert !(joke.isEmpty());
                    }
                }
        ).execute();
    }
}
