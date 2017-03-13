package com.photon.codechallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.photon.codechallenge.utilities.Utils;

import java.util.ArrayList;

/**
 * Created by Praveen on 3/12/2017.
 */

public class GridFragment extends Fragment {
   private GridView gridView;

   private ArrayList<Integer> valuesList ;
   private GridSelectionActivity parent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (GridSelectionActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.grid_layout,null);
        gridView = (GridView) linearLayout.findViewById(R.id.gridView);
        getValues();
        valuesList = new ArrayList<Integer>();
        Button btSubmit = (Button) linearLayout.findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllEntered = true;

                for(int i=0; i <parent.gridSize;i++)
                {
                    View gridItem = gridView.getChildAt(i);
                    EditText etNum = (EditText) gridItem.findViewById(R.id.etNum);
                    if(etNum.length()==0)
                    {
                        isAllEntered = false;
                        Toast.makeText(parent,"Please Enter all the values in Grid",Toast.LENGTH_LONG).show();
                        valuesList.clear();
                        break;
                    }
                    valuesList.add(Integer.parseInt(etNum.getText().toString()));
                }
                if(isAllEntered)
                {
                    parent.matrix = new int[parent.numRows][parent.numCols];
                    int count=0;
                    for(int a = 0; a<parent.numRows ; a++){
                        for(int b = 0;b<parent.numCols ; b++){
                            Log.d("COUNT::",":::"+count);
                            parent.matrix[a][b] = valuesList.get(count);
                            Log.d("matrix::",":::"+ parent.matrix[a][b]);
                            count++;
                        }
                    }
                    Toast.makeText(parent,"Shortest Path To is....",Toast.LENGTH_LONG).show();
                navigateToResult();
                }
            }
        });
        return linearLayout;
    }

    private void navigateToResult() {
        ResultFragment resultFragment = new ResultFragment();

        Utils.replaceFragment(resultFragment,R.id.mainFrameLayout,parent.fragmentManager,false);
    }

    private void getValues() {
        if(parent.getIntent().getExtras()!=null)
        {
            parent.numRows= parent.getIntent().getIntExtra("numRows",0);
            parent.numCols= parent.getIntent().getIntExtra("numCols",0);

        }
        gridView.setNumColumns(parent.numCols);
        parent.gridSize = parent.numCols*parent.numRows;
        gridView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return parent.gridSize;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = parent.getLayoutInflater().inflate(R.layout.row_grid_item,null);

            return view;
        }
    }

}
