package org.wlgzs.bio.server;

import org.wlgzs.info.HostInfo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-03 8:45
 * @Describe BIO服务器端
 */
public class BIOEchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(HostInfo.PORT);
            boolean flag = true ;
            System.out.println("服务器端已经启动，监听的端口为：" + HostInfo.PORT);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while(flag) {
                Socket client = serverSocket.accept() ;
                executorService.submit(new EchoClientHandler(client)) ;
            }
            executorService.shutdown() ;
            serverSocket.close() ;
        } catch (IOException e) { }
    }

    private static class EchoClientHandler implements Runnable {
        // 每一个客户端都需要启动一个任务(task)来执行。
        private Socket client;
        private Scanner scanner;
        private PrintStream out;
        // 循环标记
        private boolean flag = true;

        public EchoClientHandler(Socket client) {
            // 保存每一个客户端操作
            this.client = client;
            try {
                this.scanner = new Scanner(this.client.getInputStream());
                // 设置换行符
                this.scanner.useDelimiter("\n");
                this.out = new PrintStream(this.client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            while (this.flag) {
                // 现在有数据进行输入
                if (this.scanner.hasNext()) {
                    // 去掉多余的空格内容
                    String val = this.scanner.next().trim();
                    System.err.println("{服务器端}" + val);
                    if ("byebye".equalsIgnoreCase(val)) {
                        this.out.println("ByeByeBye...");
                        this.flag = false;
                    } else {
                        out.println("【ECHO】" + val);
                    }
                }
            }
            this.scanner.close();
            this.out.close();
            try {
                this.client.close();
            } catch (IOException e) { }
        }
    }
}
