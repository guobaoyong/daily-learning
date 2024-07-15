package bitOperation;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-15 8:37
 * @Describe 题7·出现K次与出现1次
 * 数组中只有一个数出现了1次，其他的数都出现K次，
 * 请输出出现1次的数。
 */
public class Main7 {
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,2,3,4,4,4,5,5,5};
        int len = arr.length;
        char[][] kRadix = new char[len][];
        int k = 3;

        int maxlen = 0;
        //转成K进制字符数组
        for (int i = 0; i < len; i++) {
            //求每个数字的3进制字符串并翻转，然后转为字符数组
            kRadix[i] = new StringBuilder(Integer.toString(arr[i],k)).reverse().toString().toCharArray();
            if (kRadix[i].length > maxlen){
                maxlen = kRadix[i].length;
            }
        }

        int[] resArr = new int[maxlen];
        for (int i = 0; i < len; i++) {
            //不进位加法
            for (int j = 0;j<maxlen;j++){
                if (j >= kRadix[i].length)
                    resArr[j] += 0;
                else
                    resArr[j] += (kRadix[i][j] - '0');
            }
        }

        int res = 0;
        //将K进制数还原为10进制数
        for (int i = 0; i < maxlen; i++) {
            res += (resArr[i] % k) * (int) Math.pow(k,i);
        }
        System.out.println(res);
    }
}
