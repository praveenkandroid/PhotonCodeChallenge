package com.photon.codechallenge;

import android.os.AsyncTask;
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
    private int pathOfLowestCost = 0;
    private String status = "NO";
    private int rowLength = 0;
    private int colLength = 0;
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
        colLength =  parent.matrix[0].length;

        LowCostAsync lowCostAsync = new LowCostAsync();
        lowCostAsync.execute();

        return linearLayout;
    }
    /*
    * Asynctask to check the pathofLow cost*/
    private class LowCostAsync extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... voids) {
            findSurroundingElements(parent.matrix,0,0);

            return pathString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvMin.setText(status);
            tvSurrounding.setText("["+pathString+"]");
            tvSum.setText(pathOfLowestCost+"");
        }
    }

/* this method used to find the surrounding elements*/
    public  void findSurroundingElements(int[][] m, int indexX, int indexY) {
        Boolean flag = false;
        if (m == null) {
            throw new NullPointerException("The input matrix cannot be null");
        }
        DataBean dBean = null;
        List<DataBean> eleList = new ArrayList<DataBean>();

        int i = indexX;
        int j = indexY;

        for (int i1 = j+1; (i1<=(j+1) && i1<(colLength)); i1++)
        {
            for (int j1= ((i-1) == -1 ? 0 : (i-1)) ; j1<=Math.min(rowLength-1,j+1); j1++)
            {
                flag = true;
                dBean = new DataBean(m[j1][i1], j1, i1);
                if(dBean != null){
                    eleList.add(dBean);
                }
            }
        }
        if(flag){
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
        if(eleList.get(0).getxIndex()== rowLength-1){
            status = "YES";
        }
        findSurroundingElements(m,eleList.get(0).getxIndex(),eleList.get(0).getyIndex());
    }
}
