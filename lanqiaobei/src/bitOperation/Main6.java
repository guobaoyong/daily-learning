package bitOperation;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 16:40
 * @Describe 题6:0~1间浮点实数的二进制表示
 * 给定一个介于0和1之间的实数,(如0.625)类型为 double ,
 * 打印它的二进制表示(0.101因为小数点后的二进制分别表示0.5,0.25.0.125……)
 * 如果该数字无法精确地用32位以内的二进制表示
 * 则打印“ ERROR ”
 */
public class Main6 {
    public static void main(String[] args) {
        double num = 0.625;
        StringBuilder stringBuilder = new StringBuilder("0.");
        while (num > 0){
            //乘2
            double r = num*2;
            //判断整数部分
            if (r >= 1){
                stringBuilder.append("1");
                num = r -1;
            }else {
                stringBuilder.append("0");
                num = r;
            }
            //判断能否精确表示
            if (stringBuilder.length() > 34){
                System.out.println("ERROR");
                return;
            }
        }
        System.out.println(stringBuilder.toString());
    }
}