package com.gamemobile.sql2.model;

/**
 * Created by hands on 10/21/2017.
 */

public class Them {

    private int mID;
    private String mTen;
    private String mNguyenlieu;
    private String mCachlam;

    public Them(String mTen, String mNguyenlieu, String mCachlam) {
        this.mTen = mTen;
        this.mNguyenlieu = mNguyenlieu;
        this.mCachlam = mCachlam;
    }

    public Them(int mID, String mTen, String mNguyenlieu, String mCachlam) {
        this.mID = mID;
        this.mTen = mTen;
        this.mNguyenlieu = mNguyenlieu;
        this.mCachlam = mCachlam;
    }

    public Them() {

    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public String getmNguyenlieu() {
        return mNguyenlieu;
    }

    public void setmNguyenlieu(String mNguyenlieu) {
        this.mNguyenlieu = mNguyenlieu;
    }

    public String getmCachlam() {
        return mCachlam;
    }

    public void setmCachlam(String mCachlam) {
        this.mCachlam = mCachlam;
    }
}
