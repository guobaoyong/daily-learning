package test;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-27 16:05
 * @description
 */
public class BubbleSoerOpt1 {
    public static void main(String[] args) {
        int[] list = {5,4,3,1,2};
        int temp = 0; // ����һ����ʱ�ռ�, ��Ž������м�ֵ
        // Ҫ�����Ĵ���
        for (int i = 0; i < list.length-1; i++) {
            int flag = 1; //����һ����־λ
            //���εıȽ������������Ĵ�С������һ�κ󣬰������е�iС�������ڵ�i��λ����
            for (int j = 0; j < list.length-1-i; j++) {
                // �Ƚ����ڵ�Ԫ�أ����ǰ�����С�ں������������
                if (list[j] < list[j+1]) {
                    temp = list[j+1];
                    list[j+1] = list[j];
                    list[j] = temp;
                    flag = 0;  //������������־λ��0
                }
            }
            System.out.format("�� %d �����ս����", i+1);
            for(int count:list) {
                System.out.print(count);
            }
            System.out.println("");
            if (flag == 1) {//���û�н�����Ԫ�أ����Ѿ�����
                return;
            }

        }
    }
}
