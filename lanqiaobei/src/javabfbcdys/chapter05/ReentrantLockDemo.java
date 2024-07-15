package javabfbcdys.chapter05;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 20:55
 * @description 可重入锁（又叫递归锁）
 * ReentrantLock和synchronized都是典型的可重入锁
 *
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程外层方法获得锁的时候，在进入内层方法会自动获取锁
 *
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 * t1	 invoked sendSMS()
 * t1	 invoked sendEmail()
 * t2	 invoked sendSMS()
 * t2	 invoked sendEmail()
 */
public class ReentrantLockDemo implements Runnable{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        new Thread(() ->{
            reentrantLockDemo.sendSMS();
        },"t1").start();

        new Thread(() ->{
            reentrantLockDemo.sendSMS();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(reentrantLockDemo,"t3");
        Thread t4 = new Thread(reentrantLockDemo,"t4");
        t3.start();
        t4.start();
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
        }finally {
            lock.unlock();
        }
    }

    private volatile int state;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset = 0;
    private transient Thread exclusiveOwnerThread;
    private transient volatile Node head;
    private transient volatile Node tail;

    /**
     * 返回同步状态的当前值。
     * 此操作具有{@code volatile}读取的内存语义
     * @return 当前状态值
     */
    protected final int getState() {
        return state;
    }

    /**
     * 以原子方式将同步状态设置为给定的更新
     * 当前状态值等于预期值时的值。
     * 此操作具有{@code volatile}读取的内存语义和写。
     *
     * @param expect 期望预期值
     * @param update 更新新值
     * @return  {@code true}如果成功。 false返回表示实际值不等于预期值。
     */
    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }


    /**
     * 设置当前拥有独占访问权限的线程。
     * {@code null}参数表示没有线程拥有访问权限。
     *  此方法不会强制执行任何同步或
     *  {@code volatile}字段访问。
     * @param thread 线程所有者线程
     */
    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    /**
     * 返回{@code setExclusiveOwnerThread}最后设置的线程，
     * 或{@code null}如果从未设置过。 否则，此方法不会
     * 强加任何同步或{@code volatile}字段访问。
     */
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    /**
     * 设置同步状态的值。
     * 此操作具有{@code volatile}写入的内存语义。
     * @param newState 新的状态值
     */
    protected final void setState(int newState) {
        state = newState;
    }

    public final boolean hasQueuedPredecessors() {
        // The correctness of this depends on head being initialized
        // before tail and on head.next being accurate if the current
        // thread is first in queue.
        Node t = tail; // Read fields in reverse initialization order
        Node h = head;
        Node s;
        return h != t &&
                ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    /**
     * 非公平锁
     * 再次获取同步状态的处理逻辑
     * @param acquires
     * @return
     */
    final boolean nonfairTryAcquire(int acquires){
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0){
            if (compareAndSetState(0,acquires)){
                setExclusiveOwnerThread(current);
                return true;
            }
        }else if (current == getExclusiveOwnerThread()){
            int nextc = c + acquires;
            if (nextc < 0){
                throw new Error("Maximum lock count exceeded");
            }
            setState(nextc);
            return true;
        }
        return false;
    }

    /**
     * 公平锁
     * 再次获取同步状态的处理逻辑
     * @param acquires
     * @return
     */
    protected final boolean tryAcquire(int acquires){
        final Thread currrent = Thread.currentThread();
        int c = getState();
        if (c == 0){
            if (!hasQueuedPredecessors() && compareAndSetState(0,acquires)){
                setExclusiveOwnerThread(currrent);
                return true;
            }
        }else if (currrent == getExclusiveOwnerThread()){
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }

    /**
     * 释放锁时减少状态值
     * @param release
     * @return
     */
    protected final boolean tryRelease(int release){
        int c = getState() - release;
        if (Thread.currentThread() != getExclusiveOwnerThread()){
            throw new IllegalMonitorStateException();
        }
        boolean free = false;
        if (c == 0){
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }
}

class Node {
    /** Marker to indicate a node is waiting in shared mode */
    static final Node SHARED = new Node();
    /** Marker to indicate a node is waiting in exclusive mode */
    static final Node EXCLUSIVE = null;

    /** waitStatus value to indicate thread has cancelled */
    static final int CANCELLED =  1;
    /** waitStatus value to indicate successor's thread needs unparking */
    static final int SIGNAL    = -1;
    /** waitStatus value to indicate thread is waiting on condition */
    static final int CONDITION = -2;
    /**
     * waitStatus value to indicate the next acquireShared should
     * unconditionally propagate
     */
    static final int PROPAGATE = -3;

    /**
     * Status field, taking on only the values:
     *   SIGNAL:     The successor of this node is (or will soon be)
     *               blocked (via park), so the current node must
     *               unpark its successor when it releases or
     *               cancels. To avoid races, acquire methods must
     *               first indicate they need a signal,
     *               then retry the atomic acquire, and then,
     *               on failure, block.
     *   CANCELLED:  This node is cancelled due to timeout or interrupt.
     *               Nodes never leave this state. In particular,
     *               a thread with cancelled node never again blocks.
     *   CONDITION:  This node is currently on a condition queue.
     *               It will not be used as a sync queue node
     *               until transferred, at which time the status
     *               will be set to 0. (Use of this value here has
     *               nothing to do with the other uses of the
     *               field, but simplifies mechanics.)
     *   PROPAGATE:  A releaseShared should be propagated to other
     *               nodes. This is set (for head node only) in
     *               doReleaseShared to ensure propagation
     *               continues, even if other operations have
     *               since intervened.
     *   0:          None of the above
     *
     * The values are arranged numerically to simplify use.
     * Non-negative values mean that a node doesn't need to
     * signal. So, most code doesn't need to check for particular
     * values, just for sign.
     *
     * The field is initialized to 0 for normal sync nodes, and
     * CONDITION for condition nodes.  It is modified using CAS
     * (or when possible, unconditional volatile writes).
     */
    volatile int waitStatus;

    /**
     * Link to predecessor node that current node/thread relies on
     * for checking waitStatus. Assigned during enqueuing, and nulled
     * out (for sake of GC) only upon dequeuing.  Also, upon
     * cancellation of a predecessor, we short-circuit while
     * finding a non-cancelled one, which will always exist
     * because the head node is never cancelled: A node becomes
     * head only as a result of successful acquire. A
     * cancelled thread never succeeds in acquiring, and a thread only
     * cancels itself, not any other node.
     */
    volatile Node prev;

    /**
     * Link to the successor node that the current node/thread
     * unparks upon release. Assigned during enqueuing, adjusted
     * when bypassing cancelled predecessors, and nulled out (for
     * sake of GC) when dequeued.  The enq operation does not
     * assign next field of a predecessor until after attachment,
     * so seeing a null next field does not necessarily mean that
     * node is at end of queue. However, if a next field appears
     * to be null, we can scan prev's from the tail to
     * double-check.  The next field of cancelled nodes is set to
     * point to the node itself instead of null, to make life
     * easier for isOnSyncQueue.
     */
    volatile Node next;

    /**
     * The thread that enqueued this node.  Initialized on
     * construction and nulled out after use.
     */
    volatile Thread thread;

    /**
     * Link to next node waiting on condition, or the special
     * value SHARED.  Because condition queues are accessed only
     * when holding in exclusive mode, we just need a simple
     * linked queue to hold nodes while they are waiting on
     * conditions. They are then transferred to the queue to
     * re-acquire. And because conditions can only be exclusive,
     * we save a field by using special value to indicate shared
     * mode.
     */
    Node nextWaiter;

    /**
     * Returns true if node is waiting in shared mode.
     */
    final boolean isShared() {
        return nextWaiter == SHARED;
    }

    /**
     * Returns previous node, or throws NullPointerException if null.
     * Use when predecessor cannot be null.  The null check could
     * be elided, but is present to help the VM.
     *
     * @return the predecessor of this node
     */
    final Node predecessor() throws NullPointerException {
        Node p = prev;
        if (p == null)
            throw new NullPointerException();
        else
            return p;
    }

    Node() {    // Used to establish initial head or SHARED marker
    }

    Node(Thread thread, Node mode) {     // Used by addWaiter
        this.nextWaiter = mode;
        this.thread = thread;
    }

    Node(Thread thread, int waitStatus) { // Used by Condition
        this.waitStatus = waitStatus;
        this.thread = thread;
    }
}