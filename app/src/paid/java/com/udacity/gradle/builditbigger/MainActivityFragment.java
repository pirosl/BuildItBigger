package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.androidjokedisplay.DisplayActivity;

/**
 * Fragment containg a simple view
 * Paid version doesn't contain ads
 *
 * @author Lucian Piros
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted {

    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeBtn = (Button)root.findViewById(R.id.tell_joke_btn);

        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
                startAsyncTask();
            }
        });

        mProgressBar = (ProgressBar)root.findViewById(R.id.pb_loading);

        return root;
    }

    void startAsyncTask() {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    @Override
    public void onTaskCompleted(String result) {
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        Intent myIntent = new Intent(getActivity(), DisplayActivity.class);
        myIntent.putExtra(DisplayActivity.JOKE_TEXT,result/*jokes.getJoke()*/);
        startActivity(myIntent);
    }
}
