package com.photon.codechallenge.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Praveen on 3/13/2017.
 */

public class Utils {
    public static Fragment replaceFragment(Fragment newFrag, int containerID, FragmentManager fragmentManager, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, newFrag);

        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

        fragmentManager.executePendingTransactions();

        return newFrag;
    }
}
