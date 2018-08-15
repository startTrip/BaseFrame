package com.mihua.frameproject.frame_code.andfix;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/12
 *     desc   :
 * </pre>
 */
public class DxManager {

    Context mContext;

    public DxManager(Context context) {
        mContext = context;
    }

    public void loadDex(File dexFile){

        File outputFile = new File(mContext.getCacheDir(),dexFile.getName());
        if (outputFile != null) {
            outputFile.delete();
        }
        try {
            DexFile dexfile = DexFile.loadDex(dexFile.getAbsolutePath(), outputFile.getAbsolutePath(), Context.MODE_PRIVATE);

            Enumeration<String> entries = dexfile.entries();
            // 如果有更多的元素,也就是类
            while (entries.hasMoreElements()){
                // 找到类名
                String className = entries.nextElement();
                // 打印出要修复的类
                Logger.t("修复好的类").d(className);
                // 根据 类名去加载类
                Class dexClass = dexfile.loadClass(className, mContext.getClassLoader());
                // 进行修复
                fix(dexClass);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修复
    private void fix(Class dexClass) {
        // 得到所有的方法包括父类的
        Method[] declaredMethods = dexClass.getDeclaredMethods();
        // 得到所有的公共方法不包括父类
        Method[] methods = dexClass.getMethods();
        // 打印方法
        Logger.t("declared").d(methods);
        Logger.d(declaredMethods);

        for (Method declaredMethod : declaredMethods) {
            // 得到方法的注解
            Replace replace = declaredMethod.getAnnotation(Replace.class);
            if(replace==null){
                continue;
            }
            String errorClassName = replace.className();
            // 错误的类名
            Logger.t("注解上面标记错误的类名").d(errorClassName);
            String errorMethodName = replace.methodName();
            try {
                // 根据类名去得到类对象
                Class aClass = Class.forName(errorClassName);
                TypeVariable[] typeParameters = declaredMethod.getTypeParameters();
                Logger.t("TypeVariable").d(typeParameters);
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                Logger.t("parameterTypes").d(parameterTypes);
                Method errorMethod = aClass.getMethod(errorMethodName, parameterTypes);

                replaceMethod(declaredMethod,errorMethod);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private void replaceMethod(Method declaredMethod, Method errorMethod) {
        Logger.d("用jni去使用正确的方法替换错误的方法");
    }

}
