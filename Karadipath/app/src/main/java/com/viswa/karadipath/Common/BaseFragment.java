package com.viswa.karadipath.Common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

/**
 * Created by VichU on 17-04-2020.
 */

public class BaseFragment extends Fragment  {

    private Activity thisActivity;
    GlobalCache gCache;

    ProgressDialog mProgressDialog;
    AlertDialog.Builder mAlertDialogBuilder;

    public BaseFragment() {
        gCache = GlobalCache.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        thisActivity = getActivity();
        mAlertDialogBuilder = new AlertDialog.Builder(thisActivity);
        mProgressDialog = new ProgressDialog(thisActivity);
        mProgressDialog.setCancelable(false);
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }


    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }



}
