package com.study.rijiben;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Pack200;

/**
 * 工具类
 */

public class Utils {
    /**
     * 获得当前的格式化后的时间
     *
     * @return 格式化后的时间
     */
    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resultTime = df.format(new Date());
        return resultTime;
    }

    /**
     * 设置EditText不能写
     *
     * @param editText 要设置的EditText
     */
    public static void setEditCanNotWrite(EditText editText) {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    /**
     * 设置EditText能写
     *
     * @param editText 要设置的EditText
     */
    public static void setEditCanWrite(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
    }

    /**
     * 隐藏输入法
     *
     * @param context 应用的Context
     */
    public static void hideInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        //得到InputMethodManager的实例
        if (imm.isActive()) {
            //如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    public static int getStringInStringsIndex(String[] inStrings, String inString) {
        for (int i = 0; i < inStrings.length; i++) {
            if (inString.equals(inStrings[i]))
                return i;
        }
        return 0;
    }
}
