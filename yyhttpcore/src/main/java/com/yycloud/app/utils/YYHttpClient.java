package com.yycloud.app.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YYHttpClient extends WAPI {
	private static final String TAG = "YYHttpClient";

	private static AsyncHttpClient client = new AsyncHttpClient(false,
			SERVER_PORT, SERVER_PORT);
	private static final String testToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI1NGQ4MWJmNGMxYzRmYzI5MGQ5MDQ4MzIiLCJleHBpcmVzIjoxNDI0MTQzMTMyNzY1LCJpYXQiOjE0MjM1MzgzMzJ9.cTZn3e0QwD69IXX39TgAK_ZWD_gJ_ZX_fGagw9NXDBw";

	static {
		// TODO remove
		// client.addHeader("authorization", testToken);
		// client.setTimeout(15000);
		client.setTimeout(30000);
	}

	public static void addHeader(String token) {
		client.addHeader("authorization", "Bearer " + token);
	}

	public static void get(String url, RequestParams params,
						   AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
							AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void put(String url, RequestParams params,
						   AsyncHttpResponseHandler responseHandler) {
		client.put(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void delete(String url, RequestParams params,
							  AsyncHttpResponseHandler responseHandler) {
		// client.delete(getAbsoluteUrl(url), params, responseHandler);
		client.delete(getAbsoluteUrl(url), responseHandler);
	}

	public static void get(String url, RequestParams params,
						   JsonHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
							JsonHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void put(String url, RequestParams params,
						   JsonHttpResponseHandler responseHandler) {
		client.put(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void delete(String url, RequestParams params,
							  JsonHttpResponseHandler responseHandler) {
		// client.delete(getAbsoluteUrl(url), responseHandler);
		client.delete(null, getAbsoluteUrl(url), null, params, responseHandler);
	}

	public static String getAbsoluteUrl(String relativeUrl) {
		// return MZClientContants.BASE_URL + ":"+ MZClientContants.PORT +
		// relativeUrl;
		return WAPI_HTTP_URL + relativeUrl;
	}

	public static void initParams(RequestParams params) {
		String uuid = "YY_SMARTGATE";
		params.put("dev_guid", uuid);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(date);

		params.put("timestamp", timestamp);
	}

	/*
	 * public static void doDemo() { YYHttpClient.get(YYNetContants.ROOMS_URL,
	 * null, new JsonHttpResponseHandler() {
	 * 
	 * @Override public void onSuccess(int statusCode, Header[] headers,
	 * JSONArray response) { super.onSuccess(statusCode, headers, response);
	 * Log.d(TAG, "onSuccess " + statusCode); }
	 * 
	 * @Override public void onSuccess(int statusCode, Header[] headers,
	 * JSONObject response) { super.onSuccess(statusCode, headers, response);
	 * Log.d(TAG, "onSuccess " + statusCode); }
	 * 
	 * @Override public void onFailure(int statusCode, Header[] headers, String
	 * responseBody, Throwable e) { super.onFailure(statusCode, headers,
	 * responseBody, e); Log.d(TAG, "onFailure " + statusCode); }
	 * 
	 * }); }
	 */
}
