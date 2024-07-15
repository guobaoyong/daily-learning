package test;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-11 22:04
 * @Describe
 */
public class Main {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000 ; i++) {
            if (true){
                System.out.println(1);
            }else {
                System.out.println(2);
            }

            if (false){
                System.out.println(1);
            }else {
                System.out.println(2);
            }
        }
        long la = System.currentTimeMillis();
        System.out.println(la-l);
    }
}
