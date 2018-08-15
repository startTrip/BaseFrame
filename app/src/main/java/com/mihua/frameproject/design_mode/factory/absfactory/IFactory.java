package com.mihua.frameproject.design_mode.factory.absfactory;

import com.mihua.code.http.okhttp.IRequestApi;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/10/17
 *     desc   :
 * </pre>
 */
public interface IFactory {

    IRequestApi getOkHttp();


}
