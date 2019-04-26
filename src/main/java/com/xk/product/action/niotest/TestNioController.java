package com.xk.product.action.niotest;

import com.xk.product.service.file.TCPEchoClient;
import com.xk.product.service.file.TCPEchoServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.InetSocketAddress;

@Controller
@RequestMapping("/test")
public class TestNioController {

    @RequestMapping("/testnioServer")
    public void testNioServer() throws Exception {
        Thread thread = new Thread(new TCPEchoServer(8081));
        thread.start();
        Thread.sleep(100000);
        /*结束服务器线程*/
        thread.interrupt();
    }
    @RequestMapping("/testnioClient")
    public void testNioClient() throws Exception {
        InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 8081);

        Thread ta = new Thread(new TCPEchoClient("thread a", remoteAddress));
        Thread tb = new Thread(new TCPEchoClient("thread b", remoteAddress));
        Thread tc = new Thread(new TCPEchoClient("thread c", remoteAddress));
        Thread td = new Thread(new TCPEchoClient("thread d", remoteAddress));

        ta.start();
        tb.start();
        tc.start();

        Thread.sleep(5000);

        /*结束客户端a*/
        ta.interrupt();

        /*开始客户端d*/
        td.start();
    }
}
