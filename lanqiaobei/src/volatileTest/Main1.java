package volatileTest;


import hashMap.Main;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 15:20
 * @Describe volatile测试类·1
 */
public class Main1 {

    boolean status = false;

    /**
     * 状态切换为true
     */
    public void changeStatus(){
        status = true;
    }

    /**
     * 若状态为true，则running。
     */
    public void run(){
        if(status){
            System.out.println("running....");
        }
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1();
        for (int i = 0;i<50;i++){
            int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    System.out.println("-------"+ finalI +"------");
                    main1.changeStatus();
                    main1.run();
                    System.out.println("--------------------------");
                }
            }.start();
        }
    }

}
