package xh_algorithm.chapter06;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-18 14:38
 * @description 位图算法
 */
public class Main1 {

    //每一个word是一个long类型元素，对应一个64位二进制数据
    private long[] words;
    //Bitmap的位数大小
    private int size;

    public Main1(int size) {
        this.size = size;
        this.words = new long[(getWordIndex(size -1) +1)];
    }

    /**
     * 判断Bitmap某一位的状态
     * @param bitIndex
     * @return
     */
    public boolean getBit(int bitIndex){
        if (bitIndex < 0 || bitIndex >= size -1){
            throw new IndexOutOfBoundsException("超出Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    /**
     * 把Bitmap某一位置设为true
     * @param bitIndex 位图为第bitIndex位
     */
    public void setBit(int bitIndex){
        if (bitIndex <0 || bitIndex >= size -1){
            throw new IndexOutOfBoundsException("超出Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    /**
     * 定位Bitmap某一位所对应的word
     * @param bitIndex
     * @return 位图为第bitIndex位
     */
    private int getWordIndex(int bitIndex){
        //右移6位，相当于除以64
        return bitIndex >> 6;
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1(128);
        main1.setBit(126);
        main1.setBit(75);
        System.out.println(main1.getBit(126));
        System.out.println(main1.getBit(78));
    }
}
