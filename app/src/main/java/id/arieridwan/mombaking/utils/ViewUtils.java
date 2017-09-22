package id.arieridwan.mombaking.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by arie on 9/20/17.
 */

public class ViewUtils {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 360;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        return noOfColumns;
    }

}
