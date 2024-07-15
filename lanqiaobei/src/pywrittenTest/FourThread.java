package pywrittenTest;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-29 19:56
 * @Description
 */
public class FourThread {

    static int sum11 = 0;
    static int sum12 = 0;
    static int sum13 = 0;
    static int sum14 = 0;
    static int sum = 0;


    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            public void run() {
                int sum1=0;
                for(int i=1;i<=25;i++){
                    sum1+=i;
                }
                sum11 += sum1;
            }
        }.start();

        new Thread(){
            public void run() {

                int sum2 = 0;
                for (int i = 26; i <= 50; i++) {
                    sum2 += i;
                }
                sum12 += sum2;
            }
        }.start();

        new Thread(){
            public void run() {

                int sum3 = 0;
                for (int i = 51; i <= 75; i++) {
                    sum3 += i;
                }
                sum13 += sum3;
            }
        }.start();

        new Thread(){
            public void run() {
                int sum4 = 0;
                for (int i = 76; i <= 100; i++) {
                    sum4 += i ;
                }
                sum14 = sum4;
            }
        }.start();


        Thread.sleep(100);

        sum = sum11 + sum12 + sum13 + sum14;
        System.out.println( "sum: " + sum);
    }
}