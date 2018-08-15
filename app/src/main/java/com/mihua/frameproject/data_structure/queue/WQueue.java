package com.mihua.frameproject.data_structure.queue;

import java.util.NoSuchElementException;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/25
 *     desc   : 手写实现双向循环链式队列，
 *      队列顺序结构容易假溢出
 *      队列链式结构效率比较高
 *
 * </pre>
 */
public class WQueue<T> {

    private int size = 0;

    private Link<T>  headLink;

    public Link<T> getHeadLink() {
        return headLink;
    }

    // 初始化 队列的头 节点    将头结点赋值
    public WQueue() {

        headLink = new Link<>(null,null,null);
        headLink.previous=headLink;
        headLink.next = headLink;
    }

    /**
     *  入队
     *  将元素加入队列
     * @param t
     * @return
     */
    public boolean addElement(T t){
        // 旧的最后一个元素
        Link<T> olderLast = headLink.previous;
        // 先构造新的节点，头指针为以前队列的最后一个元素，尾指针为队列的对头
        Link<T> newLink = new Link<>(t,olderLast,headLink);
        olderLast.next = newLink;
        headLink.previous = newLink;
        size++;
        return true;
    }

    /**
     *  出队
     * @return
     */
    public T pollElement(){
        return (size ==0?null:removeFirst());

    }

    private T removeFirst() {
        Link<T> first = headLink.next;
        if (first != null) {
            // 找到倒数第二个节点
            Link<T> newLast = first.next;
            newLast.previous = headLink;
            headLink.next = newLast;
            size--;
            return first.data;
        }
        throw new NoSuchElementException();
    }

    public int getSize(){
        return size;
    }

    // 队列中的节点对象
    public static final class Link<ET>{
        ET data;
        Link<ET> previous,next;

        /**
         *
         * @param data  数据
         * @param previous 头指针 --- 指向上一个节点对象
         * @param next  尾指针 --- 指向下一个节点对象
         */
        public Link(ET data, Link<ET> previous, Link<ET> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
}
