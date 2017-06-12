package com.udacity.gradle.builditbigger;


/**
 * Interface defining method to be called when GCE call returns
 *
 * @author Lucian Piros
 */
public interface OnTaskCompleted {
    void onTaskCompleted(String result);
}