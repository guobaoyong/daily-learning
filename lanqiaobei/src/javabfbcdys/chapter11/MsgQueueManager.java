package javabfbcdys.chapter11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-15 16:31
 * @description 总消息队列管理
 */
public class MsgQueueManager {

    //消息总队列
    public final BlockingQueue<Message> messageQueue;

    private MsgQueueManager() {
        messageQueue = new LinkedTransferQueue<>();
    }

    public void put(Message msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Message take() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

}


