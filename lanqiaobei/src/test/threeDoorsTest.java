package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-02 9:28
 * @description 三门问题
 * 这个数学问题来源于一个娱乐节目。节目中有一位参与者和一位主持人，在参与者的面前有三扇关闭的门，其中两扇门的后面是空的，剩下一扇门后是一辆法拉利跑车。
 * 主持人知道哪一扇门后面有跑车，但参与者不知道。此时让参与者人选一扇门，如果选择的是后面有跑车的那扇门，跑车就作为奖励送给参与者。
 * 下面是问题的重点，当参与者进行选择以后，暂时先不打开这扇门，接下来主持人把剩下两扇门当中的一扇打开，是空门。
 * 此时主持人给了参与者重新选择的机会：可以坚持刚才选择的门（在图中是2号门），也可以换另一扇没有打开的门（在图中是1号门）。
 * 如果你是游戏参与者，你怎样选择的获奖率更大？获奖率又是多少？
 */
public class threeDoorsTest {
    public static void main(String[] args) {
        //换门的获奖总次数
        int changeWinCount = 0;
        //不换门的获奖总次数
        int unChangeWinCount = 0;
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            List<Integer> doors = new ArrayList<>(Arrays.asList(0,1,2));
            int bonusDoor = random.nextInt(3);
            int selectedDoor = random.nextInt(3);
            //主持人打开一扇空门
            for (int j = 0; j < doors.size(); j++) {
                if (doors.get(j) != selectedDoor && doors.get(j) != bonusDoor){
                    doors.remove(j);
                    break;
                }
            }
            //获得换门的序号,此时集合中就剩两个元素
            int changedDoor = doors.get(0);
            if (changedDoor == selectedDoor){
                changedDoor = doors.get(1);
            }
            //如果不换门有奖，unChangeWinCount加1；如果换门有奖，changeWinCount加1
            if (selectedDoor == bonusDoor){
                unChangeWinCount++;
            }else if (changedDoor == bonusDoor){
                changeWinCount++;
            }
        }
        System.out.println("不换门获奖总次数："+unChangeWinCount+"，所占比例："+(float)unChangeWinCount/100000);
        System.out.println("换门获奖总次数："+changeWinCount+"，所占比例："+(float)changeWinCount/100000);
    }
}
