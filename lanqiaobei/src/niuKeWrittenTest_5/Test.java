package niuKeWrittenTest_5;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-15 19:04
 * @description
 */
public class Test {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= (100-i)/2; j++) {
                for (int k = 1; k <= (100-i-2*j)/5; k++) {
                    if ((i+j*2+k*5) == 100){
                        System.out.println("1分："+i+"\t 2分："+j+"\t 5分："+k);
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
