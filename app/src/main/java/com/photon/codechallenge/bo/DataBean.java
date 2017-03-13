package com.photon.codechallenge.bo;

/**
 * Created by Praveen on 3/13/2017.
 */

public class DataBean implements Comparable<DataBean>{

    private int value;
    private int xIndex;
    private int yIndex;



    public DataBean(int value, int xIndex, int yIndex) {
        super();
        this.value = value;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getxIndex() {
        return xIndex;
    }

    public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getyIndex() {
        return yIndex;
    }

    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }

    @Override
    public String toString() {
        return "DataBean [value=" + value + ", xIndex=" + xIndex + ", yIndex="
                + yIndex + "]";
    }

    @Override
    public int compareTo(DataBean dataBean) {
        if(value > dataBean.getValue()){
            return 1;
        } else if (value < dataBean.getValue()){
            return -1;
        } else {
            return 0;
        }
    }

}
