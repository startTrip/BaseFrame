package com.mihua.frameproject.design_mode.factory.simplefactory;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/12
 *     desc   :
 * </pre>
 */
public class Test {

    public static void main(String[] args) {

        Product product = Factory.create(IOSProduct.class);
        product.show();
    }

}
