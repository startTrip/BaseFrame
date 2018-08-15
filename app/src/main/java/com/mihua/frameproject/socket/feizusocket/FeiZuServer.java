package com.mihua.frameproject.socket.feizusocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/22
 *     desc   : 非阻塞式 Socket,   只是用 Selector 来管理路由，也就是许多的通信渠道
 *
 * </pre>
 */
public class FeiZuServer {

    Selector mSelector = null;

    public void init() throws IOException {

        mSelector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // socket 地址
        SocketAddress s = new InetSocketAddress(InetAddress.getLocalHost(),30000);
        // 得到 serverSocket 也就是 端口的 地址并绑定到 ip地址
        serverSocketChannel.socket().bind(s);
        // 设置 ServerSocket 以非阻塞方式工作
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(mSelector, SelectionKey.OP_ACCEPT);

        while (mSelector.select()>0){
            for (SelectionKey selectionKey : mSelector.selectedKeys()) {

                mSelector.selectedKeys().remove(selectionKey);

            }
        }
    }
}
