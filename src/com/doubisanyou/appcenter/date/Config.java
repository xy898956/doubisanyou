package com.doubisanyou.appcenter.date;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Handler;

import com.doubisanyou.appcenter.bean.User;

public class Config {

	public static final String DIR_NAME = null;

	public static String CHARSET_UTF8 = "utf-8";

	public static String TOKEN = "token";

	public static User user = new User();

	public static String PROJECT_ID = "com.doubisanyou.tea";

	public static String SERVICE_URL = "http://192.168.0.111:8080/chazhongservice/";

	public static final String AppCenters_Path = "doubisanyou"; // 应用中心路径

	public static final String AppCenters_ImagePath = "image";

	public static final Map<String, Bitmap> BITMAPCATCH = new HashMap<String, Bitmap>();

	public static final String XMPP_HOST = "192.168.0.111";

	public static final int XMPP_PORT = 5222;

	public static final String XMPP_SERVICE_NAME = "localhost";

	public static final String DEFAULT_DATABASE = "teaDatabase_blook.db3";

	public static String getToken(Context context) {
		return context.getSharedPreferences(PROJECT_ID, Context.MODE_PRIVATE)
				.getString(TOKEN, null);
	}

	public static void setToken(Context context, String token) {
		Editor ed = context.getSharedPreferences(PROJECT_ID,
				Context.MODE_PRIVATE).edit();
		ed.putString(TOKEN, token);
		ed.commit();
	}

	// 用于设置中的图片处理
	public static Handler handler;
	//注册时临时存放图片地址
	public static String TEMPAVATAR="";
}
