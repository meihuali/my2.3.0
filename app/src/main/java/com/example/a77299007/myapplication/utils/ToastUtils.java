/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.a77299007.myapplication.utils;

import android.text.TextUtils;
import android.widget.Toast;
import com.example.a77299007.myapplication.App;


/**
 * Toast工具类，解决多个Toast同时出现的问题
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class ToastUtils {

    private static Toast mToast;

    /********************** 非连续弹出的Toast ***********************/
    public static void showSingleToast(int resId) { //R.string.**
        showSingleToast(App.getsInstance().getString(resId));
    }

    public static void showSingleToast(String text) {

        if (TextUtils.isEmpty(text) || text.contains("failed to connect"))
            return;


        getSingleToast(text, Toast.LENGTH_SHORT);
    }

    public static void showPermissionFail() {
        showSingleToast("获取权限失败");
    }


    /*********************** 连续弹出的Toast ************************/

    public static void getSingleToast(String text, int duration) {

        if (mToast == null) {
            mToast = Toast.makeText(App.getsInstance(), text, duration);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }


}
