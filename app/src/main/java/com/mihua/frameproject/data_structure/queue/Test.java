package com.mihua.frameproject.data_structure.queue;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/25
 *     desc   :
 * </pre>
 */
public class Test {

    public static void main(String[] args){

        WQueue<String> queue = new WQueue<>();
        queue.addElement("a1");
        queue.addElement("a2");
        queue.addElement("a3");
        queue.addElement("a4");

        PrintlnAllElement(queue);

        queue.pollElement();
        queue.pollElement();

        PrintlnAllElement(queue);
        queue.addElement("哈哈");
        PrintlnAllElement(queue);
    }

    private static void PrintlnAllElement(WQueue<String> queue) {

        for (WQueue.Link<String> link= queue.getHeadLink().next;link!=queue.getHeadLink(); link = link.next) {
            System.out.println(link.data);
        }
    }

}
