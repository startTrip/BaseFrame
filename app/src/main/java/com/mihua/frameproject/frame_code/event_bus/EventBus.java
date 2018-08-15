package com.mihua.frameproject.frame_code.event_bus;

import com.mihua.frameproject.annotation.Subscribe;
import com.mihua.frameproject.annotation.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/01
 *     desc   :
 * </pre>
 */
public class EventBus {

    public static HashMap<Object,List<SubscribeModal>> mHashMap = new HashMap<>();
    private  volatile static EventBus sEventBus;

    public static EventBus getDefault(){

        if(sEventBus==null){
            synchronized (EventBus.class){
                if (sEventBus == null) {
                    sEventBus = new EventBus();
                }
            }
        }
        return sEventBus;
    }

    public void register(Object a){
        // 如果包含则什么都不做
        if(!mHashMap.containsKey(a)){
            List<SubscribeModal> list = findSubscribeModal(a);
            if (list != null) {
                mHashMap.put(a,list);
            }
        }

    }

    public List<SubscribeModal> findSubscribeModal(Object a) {

        List<SubscribeModal> list = new CopyOnWriteArrayList<>();
        Class<?> aClass = a.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Subscribe annotation = declaredMethod.getAnnotation(Subscribe.class);
            if(annotation!=null){
                ThreadMode threadMode = annotation.threadMode();
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                if(parameterTypes.length!=1){
                    throw new IndexOutOfBoundsException("参数必须有且仅有一个");
                }
                Class aclass = parameterTypes[0];
                SubscribeModal subscribeModal = new SubscribeModal(threadMode,declaredMethod,aclass);
                list.add(subscribeModal);
            }
        }
        return list;
    }

    /**
     *
     * @param targetModel  发送的对象
     *           根据对象去找到  集合中对应的方法并调用
     */
    public void post(Object targetModel){

        Set<Object> keySet = mHashMap.keySet();
        Iterator<Object> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            Object next = iterator.next();
            List<SubscribeModal> subscribeModals = mHashMap.get(next);
            for (SubscribeModal subscribeModal : subscribeModals) {
                Class classType = subscribeModal.getClassType();
                if (classType.isAssignableFrom(targetModel.getClass())){
                    // 调用里面的方法
                    invokeMethod(next,subscribeModal.getMethod(),targetModel);
                }
            };
        };
    }

    private static void invokeMethod(Object next, Method method, Object targetModel) {

        try {
            method.invoke(next,targetModel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
