package exception;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-01 10:32
 * @Describe 测试用例
 * 有return的情况下try catch finally的执行顺序
 *
 */
public class Main1 {

    /**
     * 例1、try{} -->   catch(){} --> finally{} -->  return;
     */
    static Integer f1(){
        try {
            System.out.println("情况1try");
        }catch (Exception e){
            System.out.println("情况1catch");
        }finally {
            System.out.println("情况1finally");
        }
        return 0;
    }

    /**
     * try{ return; } --> catch(){}  --> finally{}  --> return;
     * @return
     */
    static Integer f2(){
        try {
            System.out.println("情况2try");
            return 1;
        }catch (Exception e){
            System.out.println("情况2catch");
        }finally {
            System.out.println("情况2finally");
        }
        return 0;
    }

    /**
     * try{ } --> catch(){return;}  --> finally{} --> return;
     * @return
     */
    static Integer f3(){
        try {
            System.out.println("情况3try");
            int a = 1/0;
        }catch (Exception e){
            System.out.println("情况3catch");
            return 1;
        }finally {
            System.out.println("情况3finally");
        }
        return 0;
    }

    /**
     * try{ return; } --> catch(){} --> finally{return;}
     * @return
     */
    static Integer f4(){
        try {
            System.out.println("情况4try");
            return 1;
        }catch (Exception e){
            System.out.println("情况4catch");
        }finally {
            System.out.println("情况4finally");
            return 2;
        }
    }

    /**
     * try{} --> catch(){return;} --> finally{return;}
     * @return
     */
    static Integer f5(){
        try {
            System.out.println("情况5try");
            int a = 1/0;
        }catch (Exception e){
            System.out.println("情况5catch");
            return 1;
        }finally {
            System.out.println("情况5finally");
            return 2;
        }
    }

    /**
     * try{ return;} --> catch(){return;} --> finally{return;}
     * @return
     */
    static Integer f6(){
        try {
            System.out.println("情况6try");
            int a = 1 / 0;
            return 1;
        }catch (Exception e){
            System.out.println("情况6catch");
            return 2;
        }finally {
            System.out.println("情况6finally");
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println(f1());
        System.out.println("----------------------------");
        System.out.println(f2());
        System.out.println("----------------------------");
        System.out.println(f3());
        System.out.println("----------------------------");
        System.out.println(f4());
        System.out.println("----------------------------");
        System.out.println(f5());
        System.out.println("----------------------------");
        System.out.println(f6());
        System.out.println("----------------------------");
    }

}
