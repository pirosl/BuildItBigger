package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lucian on 12/06/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
    class MockupOnTaskComplete implements OnTaskCompleted {

        @Override
        public void onTaskCompleted(String result) {

        }
    }

    @Test
    public void testDoInBackground() throws Exception{
        try {
            MockupOnTaskComplete mockupOnTaskComplete = new MockupOnTaskComplete();
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(mockupOnTaskComplete);
            endpointsAsyncTask.execute();
            String result = endpointsAsyncTask.get(30, TimeUnit.SECONDS);

            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e){
            Log.e("EndpointsAsyncTaskTest", "testDoInBackground: Timed out");
        }
    }

}
