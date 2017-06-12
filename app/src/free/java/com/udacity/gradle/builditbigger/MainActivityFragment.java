package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.androidjokedisplay.DisplayActivity;

import java.util.Random;


/**
 * Fragment containg a simple view
 * Paid version contains ads
 *
 * @author Lucian Piros
 */
public class MainActivityFragment extends Fragment implements OnTaskCompleted {

    private InterstitialAd mInterstitialAd;
    private String mJoke;
    private boolean mAddOnScreen;

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

        final Random random = new Random();

        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
                // only display randomly every the add every second tap
                int r = 1 + random.nextInt(2);
                if (mInterstitialAd.isLoaded() && r % 2 == 0) {
                    mInterstitialAd.show();
                    mAddOnScreen = true;
                }
            }
        });

        mAddOnScreen = false;

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                mAddOnScreen = false;
                showJokeActivity();
            }

        });

        return root;
    }

    void startAsyncTask() {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
        mJoke = null;
    }

    @Override
    public void onTaskCompleted(String result) {
        mJoke = result;
        showJokeActivity();
    }

    private void showJokeActivity() {
        if(!mAddOnScreen) {
            if(mJoke != null) {
                Intent myIntent = new Intent(getActivity(), DisplayActivity.class);
                myIntent.putExtra(DisplayActivity.JOKE_TEXT,mJoke);
                startActivity(myIntent);
            }
        }
    }
}
