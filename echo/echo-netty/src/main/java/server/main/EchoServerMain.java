package server.main;

import server.EchoServer;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-04 9:04
 * @Describe 服务器端运行
 */

public class EchoServerMain {
    public static void main(String[] args) throws Exception {
        new EchoServer().run();
    }
}
