package niuKeWrittenTest_2;

import java.util.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-25 19:25
 * @Describe 【牛牛取快递】
 * 牛牛的快递到了，他迫不及待地想去取快递，但是天气太热了，以至于牛牛不想在烈日下多走一步。他找来了你，请你帮他规划一下，他最少要走多少距离才能取回快递。
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 输入的第一行包括四个正整数，表示位置个数N(2<=N<=10000)，道路条数M(1<=M<=100000)，起点位置编号S(1<=S<=N)和快递位置编号T(1<=T<=N)。位置编号从1到N，道路是单向的。数据保证S≠T，保证至少存在一个方案可以从起点位置出发到达快递位置再返回起点位置。
 * 接下来M行，每行包含三个正整数，表示当前道路的起始位置的编号U(1<=U<=N)，当前道路通往的位置的编号V(1<=V<=N)和当前道路的距离D(1<=D<=1000)。
 * 输出描述:
 * 对于每个用例，在单独的一行中输出从起点出发抵达快递位置再返回起点的最短距离。
 *
 * 示例1
 * 输入
 * 3 3 1 3
 * 1 2 3
 * 2 3 3
 * 3 1 1
 * 输出
 * 7
 */
public class Main3 {

    public static class Item {
        int index;
        int weight;

        public Item(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int len = sc.nextInt();
            int times = sc.nextInt();
            int start = sc.nextInt();
            int end = sc.nextInt();
            List<List<Item>> lists = new ArrayList<List<Item>>();
            for (int i = 0; i <= len; i++) {
                lists.add(new ArrayList<Item>());
            }
            for (int i = 0; i < times; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int v = sc.nextInt();
                lists.get(s).add(new Item(e, v));
            }
            int res = helper(lists, len, start, end) + helper(lists, len, end, start);
            System.out.println(res);
        }
        sc.close();
    }

    public static int helper(List<List<Item>> lists, int len, int start, int end) {
        boolean[] visited = new boolean[len + 1];
        int[] dis = new int[len + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        PriorityQueue<Item> queue = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item o1, Item o2) {
                return o1.weight - o2.weight;
            }

        });
        queue.offer(new Item(start, 0));
        while (!queue.isEmpty()) {
            Item item = queue.poll();
            int index = item.index;
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            List<Item> list = lists.get(index);
            for (int j = 0; j < list.size(); j++) {
                Item temp = list.get(j);
                if (!visited[temp.index] && dis[index] + temp.weight < dis[temp.index]) {
                    dis[temp.index] = dis[index] + temp.weight;
                    queue.offer(new Item(temp.index, dis[index] + temp.weight));
                }
            }
        }
        return dis[end];
    }
}
