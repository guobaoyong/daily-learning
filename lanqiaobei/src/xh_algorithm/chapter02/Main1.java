package xh_algorithm.chapter02;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-13 14:36
 * @description 数组的增加、插入、删除操作
 * 数组适用于读操作多，写操作少的场景
 */
public class Main1 {

    private int[] array;
    private int size;

    public Main1(int capacity){
        this.array = new int[capacity];
        size = 0;
    }

    /**
     * 数组插入元素
     * @param element 插入的元素
     * @param index 插入的位置
     */
    public void insert(int element,int index){
        //判断访问下标是否超范围
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        //如果实际元素达到数组上限，则对数组进行扩容
        if (size >= array.length){
            resize();
        }
        //从右往左循环，将元素逐个向右挪1位
        for (int i = size-1; i >= index; i--) {
            array[i+1] =array[i];
        }
        //腾出位置插入新元素
        array[index] = element;
        size++;
    }

    /**
     * 数组扩容
     */
    public void resize(){
        int[] arrayNew = new int[array.length*2];
        //从旧数组复制到新数组
        System.arraycopy(array,0,arrayNew,0,array.length);
        array = arrayNew;
    }

    /**
     * 数组删除元素
     * @param index 删除的位置
     * @return
     */
    public int delete(int index){
        //判断访问下标是否超范围
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        int deleteElementarray = array[index];
        //从左往右循环，将元素逐个向左挪1位
        for (int i = index; i <size-1 ; i++) {
            array[i] = array[i+1];
        }
        size--;
        return deleteElementarray;
    }

    /**
     * 输出数组
     */
    public void output(){
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]+"\t");
        }
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1(4);
        main1.insert(3,0);
        main1.insert(7,1);
        main1.insert(9,2);
        main1.insert(5,3);
        main1.insert(6,1);
        main1.delete(0);
        main1.output();
    }


}
