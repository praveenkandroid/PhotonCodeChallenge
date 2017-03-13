package com.photon.codechallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.photon.codechallenge.bo.DataBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Praveen on 3/12/2017.
 */

public class ResultFragment extends Fragment {
    //Variables declaration
    private int count =0;
    private int pathOfLowestCost = 0;
    private String status = "NO";
    private int rowLength = 0;
    private GridSelectionActivity parent;
    private TextView tvMin,tvSurrounding,tvSum;
    private String pathString="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (GridSelectionActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.result_fragment,null);
         tvMin = (TextView) linearLayout.findViewById(R.id.tv1);
         tvSurrounding = (TextView) linearLayout.findViewById(R.id.tv2);
         tvSum = (TextView) linearLayout.findViewById(R.id.tv3);
        rowLength=parent.matrix.length;
        findSurroundingElements(parent.matrix,0,0);
        tvMin.setText(status);
        tvSurrounding.setText("["+pathString+"]");
        tvSum.setText(pathOfLowestCost+"");


        return linearLayout;
    }
    /*
	 * this method used to find the surrounding elements*/
    public  void findSurroundingElements(int[][] m, int indexX, int indexY) {
        count++;
        if (m == null) {
            throw new NullPointerException("The input matrix cannot be null");
        }
        DataBean dBean = null;
        List<DataBean> eleList = new ArrayList<DataBean>();

        int i = indexX;
        int j = indexY;

        for(int i1= i+1;(i1<=i+1) && (rowLength >= i1);i1++)
        {
            for (int j1=Math.min(0, j);j1<=j+1;j1++)
            {
                dBean = new DataBean(m[j1][i1], i1, j1);
                if(dBean != null){
                    eleList.add(dBean);
                }
            }
        }

        if(count<=rowLength){
            findMinimumValueFromList(eleList,m);
        }
    }

    /*
     * This method used to find the lowest element in surrounding elements list*/
    public  void findMinimumValueFromList(List<DataBean> eleList, int[][] m) {
        Collections.sort(eleList);
        pathOfLowestCost += eleList.get(0).getValue();
        if(pathString.length()==0)
            pathString=pathString+ eleList.get(0).getValue();
        else
        pathString=pathString+","+ eleList.get(0).getValue();

        System.out.println(eleList.get(0).getValue());
        if(eleList.get(0).getyIndex()==m.length){
            status = "YES";
        }
        findSurroundingElements(m,eleList.get(0).getxIndex(),eleList.get(0).getyIndex());
    }
}
