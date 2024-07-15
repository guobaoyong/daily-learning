package volatileTest;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 15:57
 * @Describe volatile测试类·4
 */
public class Main4 {

    int a = 1;
    boolean status = false;

    /**
     * 状态切换为true
     */
    public void changeStatus(){
        a = 2;//1
        status = true;//2
    }

    /**
     * 若状态为true，则running。
     */
    public void run(){
        if(status){//3
            int b = a+1;//4
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        Main4 main4 = new Main4();
        for (int i = 0;i<30000;i++){
            new Thread(){
                @Override
                public void run() {
                    /*System.out.println("-------"+ fi +"------");*/
                    main4.changeStatus();
                    main4.run();
                    System.out.println("--------------------------");
                }
            }.start();
        }
    }
}
