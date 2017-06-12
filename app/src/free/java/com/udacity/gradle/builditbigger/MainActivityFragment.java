package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.androidjokedisplay.DisplayActivity;


/**
 * Fragment containg a simple view
 * Paid version contains ads
 *
 * @author Lucian Piros
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button tellJokeBtn = (Button)root.findViewById(R.id.tell_joke_btn);

        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
            }
        });

        return root;
    }

    void startAsyncTask() {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    @Override
    public void onTaskCompleted(String result) {
        Intent myIntent = new Intent(getActivity(), DisplayActivity.class);
        myIntent.putExtra(DisplayActivity.JOKE_TEXT,result/*jokes.getJoke()*/);
        startActivity(myIntent);
    }
}
