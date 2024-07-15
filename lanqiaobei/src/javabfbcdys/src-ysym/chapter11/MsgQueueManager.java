package chapter11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 总消息队列管理
 *
 * @author tengfei.fangtf
 */
public class MsgQueueManager implements chapter11.IMsgQueue {

    /**
     * 消息总队列
     */
    public final BlockingQueue<chapter11.Message> messageQueue;

    private MsgQueueManager() {
        messageQueue = new LinkedTransferQueue<chapter11.Message>();
    }

    public void put(chapter11.Message msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public chapter11.Message take() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

}
