package com.photon.codechallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.photon.codechallenge.utilities.Utils;

/**
 * Created by Praveen on 3/12/2017.
 */

public class GridSelectionActivity extends FragmentActivity {

    public FragmentManager fragmentManager;
    public  int[][] matrix;
    public int numRows,numCols,gridSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        fragmentManager = getSupportFragmentManager();
        GridFragment gridFragment = new GridFragment();
        Utils.replaceFragment(gridFragment, R.id.mainFrameLayout, fragmentManager, false);

    }



}
