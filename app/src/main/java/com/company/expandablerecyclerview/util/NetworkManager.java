package com.company.expandablerecyclerview.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Andrew on 14.01.2018.
 */

public class NetworkManager {

    private Context mContext;

    public NetworkManager(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        } else {
            return false;
        }
        return netInfo != null && netInfo.isConnected();
    }
}
