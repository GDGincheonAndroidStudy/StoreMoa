package com.seunghyo.storemoa;

/**
 * Created by kimjason on 15. 12. 6..
 */

/**
 * Created by Jason Kim on 2015-03-16.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;


public class Util {

    private static final boolean DEBUG = true;

    private static final String TAG = "Util";

    public static final int ANDROID_BUILD_GINGERBREAD = 9;
    public static final int ANDROID_BUILD_ICS = 14;
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 0x06;
    public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 0x07;

    private final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    // Instance of this class
    private static Util m_instance;

    private static Toast m_toast;

    private static final String TAG_SCREEN = "TURN_SCREEN";

    private static PowerManager.WakeLock m_wakeLock;

    private static final boolean DEBUGMODE = true;

    private Util() {
        printLog(DEBUG, TAG, "[Util]");
    }

    public static Util getInstance() {
        if (m_instance == null) {
            m_instance = new Util();
        }
        return m_instance;
    }

    public void printLog(boolean bPrint, String tag, String msg) {
        if (DEBUGMODE) {
            if (bPrint) {
                Log.d(tag, msg);
            }
        }
    }

    public void showSingleBtnAlertDlg(Context context, String strTitle, String strBtn, String strMsg) {
        printLog(DEBUG, TAG, "[showSingleBtnAlertDlg] strTitle : " + strTitle + ", strBtn : " + strBtn + ", strMsg : " + strMsg);
        new AlertDialog.Builder(context).setTitle(strTitle).setMessage(strMsg).setPositiveButton(strBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public boolean hasContryCode(String number) {
        return number.charAt(0) == '+' && number.charAt(1) == '8' && number.charAt(2) == '2';
    }

    public String getPhoneNumber(Context context) {
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String number = telManager.getLine1Number();
        if (number == null) {
            number = "";
        }
        if (number.length() >= 10) {
            if (hasContryCode(number)) {
                number = "0" + number.substring(3);
            }
        }
        return number;
    }

    public void showToast(Context context, String content, int a) {
        printLog(DEBUG, TAG, "[showToast] content : " + content);
        if (m_toast == null) {
            if (a == 0) // short
                m_toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            if (a == 1)
                m_toast = Toast.makeText(context, null, Toast.LENGTH_LONG);
        }
        m_toast.setText(content);
        m_toast.show();
//		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        return;
    }


    public boolean isNetworkConnected(Context context) {
        printLog(DEBUG, TAG, "[isNetworkConnected]");
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            Log.d("wifi status", String.valueOf(wifi.isConnected()));

            if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null && manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) == null) {
                //Log.d("Wifi Tablet","in rutin");
                printLog(DEBUG, TAG, "[isNetworkConnected] Mobile & Wifi is none");
                return false;
            } else {

                if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null) {
                    if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                        return true;
                    } else
                        return false;
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getYearFromSysMillis(long millis) {
        printLog(DEBUG, TAG, "[getYearFromSysMillis] millis : " + millis);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.get(Calendar.YEAR);
    }

    public boolean isPortrait(Context context) {
        boolean portrait;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            portrait = true;
        } else {
            portrait = false;
        }
        return portrait;
    }
}
