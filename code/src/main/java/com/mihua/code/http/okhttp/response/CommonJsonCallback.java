package com.mihua.code.http.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import com.mihua.code.http.okhttp.exception.OkHttpException;
import com.mihua.code.http.okhttp.listener.DisposeDataHandle;
import com.mihua.code.http.okhttp.listener.DisposeDataListener;
import com.mihua.code.http.okhttp.listener.DisposeHandleCookieListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

	/**
	 * the logic layer exception, may alter in different app
	 */
	protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
	protected final int RESULT_CODE_VALUE = 0;
	protected final String ERROR_MSG = "emsg";
	protected final String EMPTY_MSG = "";
	protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
														// can has the value of
														// set-cookie2

	/**
	 * the java layer exception, do not same to the logic error
	 */
	protected final int NETWORK_ERROR = -1; // the network relative error
	protected final int JSON_ERROR = -2; // the JSON relative error
	protected final int OTHER_ERROR = -3; // the unknow error

	/**
	 * 将其它线程的数据转发到UI线程
	 */
	private Handler mDeliveryHandler;
	private DisposeDataListener mListener;
	private Class<?> mClass;
	private final Gson mGson;

	public CommonJsonCallback(DisposeDataHandle handle) {
		this.mListener = handle.mListener;
		this.mClass = handle.mClass;
		this.mDeliveryHandler = new Handler(Looper.getMainLooper());
		mGson = new Gson();
	}

	@Override
	public void onFailure(final Call call, final IOException ioexception) {
		/**
		 * 此时还在非UI线程，因此要转发
		 */
		mDeliveryHandler.post(new Runnable() {
			@Override
			public void run() {
				ioexception.printStackTrace();
				mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception.getMessage()));
			}
		});
	}

	@Override
	public void onResponse(final Call call, final Response response) throws IOException {
		final String result = response.body().string();
		final ArrayList<String> cookieLists = handleCookie(response.headers());
		mDeliveryHandler.post(new Runnable() {
			@Override
			public void run() {
				handleResponse(result);
				/**
				 * handle the cookie
				 */
				if (mListener instanceof DisposeHandleCookieListener) {
					((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
				}
			}
		});
	}

	private ArrayList<String> handleCookie(Headers headers) {
		ArrayList<String> tempList = new ArrayList<String>();
		for (int i = 0; i < headers.size(); i++) {
			if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
				tempList.add(headers.value(i));
			}
		}
		return tempList;
	}

	private void handleResponse(Object responseObj) {

		if (responseObj == null) {
			// 网络错误回调，错误码和错误信息
			mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
			return;
		}

		try {
			JSONObject result = new JSONObject(responseObj.toString());

			if (result.has(RESULT_CODE)) {
				// 如果返回正确的结果码
				if (result.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
					if (mClass == null) {
						// 直接返回 JsonObject
						mListener.onSuccess(result);
					} else {
						Object obj = mGson.fromJson(responseObj.toString(), mClass);
						if (obj != null) {
							//返回类对象
							mListener.onSuccess(obj);
						} else {
							// Json 解析错误
							mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
						}
					}
				} else {
					// 如果返回错误码
					if (result.has(ERROR_MSG)) {
						mListener.onFailure(
								new OkHttpException(result.optInt(RESULT_CODE), result.optString(ERROR_MSG)));
					} else {
						// 返回空的信息
						mListener.onFailure(new OkHttpException(result.optInt(RESULT_CODE), EMPTY_MSG));
					}
				}
			} else {
				if (result.has(ERROR_MSG)) {
					mListener.onFailure(new OkHttpException(OTHER_ERROR, result.optString(ERROR_MSG)));
				}
			}
		} catch (Exception e) {
			// 其他的
			mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()+"\r\n"+responseObj.toString()));
			e.printStackTrace();
		}
	}
}