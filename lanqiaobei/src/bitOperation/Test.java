package bitOperation;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-15 9:46
 * @Describe 位运算实现加减乘除操作
 */
public class Test {
    //两种方式：
    //1、递归形式实现
    int add(int a ,int b){
        if (b == 0)
            return a;
        else{
            //进位值
            int carry = (a & b) << 1;
            a = a ^b;
            return add(a,carry);
        }
    }

    //非递归形式实现
    int add2(int a ,int b){
        //进位值
        int carry;
        while (b != 0){
            carry = (a & b) << 1;
            a = a ^b;
            b = carry;
        }
        return a;
    }

    //减法实现 a+(-b)=a+~b+1
    int subtraction(int a ,int b){
        b = ~b+1;
        return this.add(a,b);
    }

    //乘法实现
    //a 被乘数，b 乘数
    int multiplication(int a,int b){
        int i = 0;
        int res = 0;
        //乘数不为0
        while (b != 0){
            //处理当前位
            //当前位是1
            if ((b & 1) == 1){
                res += (a << i);
                b = b >> 1;
                //记录当前是第几位
                i++;
            }else {
                //当前位是0
                b = b >> 1;
                i++;
            }
        }
        return res;
    }

    //除法实现
    int division(int a,int b){
        int res;
        if(a<b){
            return 0;
        }else{
            res=division(subtraction(a, b), b)+1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Test().add(100,8));
        System.out.println(new Test().subtraction(100,8));
        System.out.println(new Test().multiplication(-3,3));
        System.out.println(new Test().division(100,3));
    }
}
