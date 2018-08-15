package com.mihua.frameproject.design_mode.factory.absfactory;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/12
 *     desc   : 工厂类用于生产产品的工厂
 * </pre>
 */
public class Factory {

    public static Product create(Class<? extends Product> tClass){

        try {
            Product product = (Product) Class.forName(tClass.getCanonicalName()).newInstance();
            return product;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
