package com.mihua.code.update;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class UpdateInfo {


    /**
     * code : 200
     * datas : {"version":"1.36","url":"http://shop.trqq.com/TRShop_1.36.apk"}
     */

    private int code;
    /**
     * version : 1.36
     * url : http://shop.trqq.com/TRShop_1.36.apk
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String version;
        private String url;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
