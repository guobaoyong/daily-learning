package xh_algorithm.chapter06;
import	java.io.Serializable;
import java.util.HashMap;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-04 14:48
 * @description LRU算法实现思路
 * 最近最少使用
 */
public class Main2 {

    private NodeForMain2 head;
    private NodeForMain2 end;
    //缓存存储上限
    private int limit;

    private HashMap<String, NodeForMain2> hashMap;

    public Main2(int limit){
        this.limit = limit;
        hashMap = new HashMap<> ();
    }
    public String get(String key){
        NodeForMain2 nodeForMain2 = hashMap.get(key);
        if (nodeForMain2 == null){
            return null;
        }
        refreshNode(nodeForMain2);
        return nodeForMain2.value;
    }

    public void put(String key,String value){
        NodeForMain2 node = hashMap.get(key);
        if (node == null){
            //如果key不存在，则插入key-value
            if (hashMap.size() >= limit){
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new NodeForMain2(key,value);
            addNode(node);
            hashMap.put(key,node);
        }else {
            //如果key存在，则刷新key-value
            node.value = value;
            refreshNode(node);
        }
    }
    public void remove(String key){
        NodeForMain2 nodeForMain2 = hashMap.get(key);
        removeNode(nodeForMain2);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     * @param node
     */
    private void refreshNode(NodeForMain2 node){
        //如果访问的是尾节点，则无需移动节点
        if (node == end){
            return;
        }
        //移除节点
        removeNode(node);
        //重新插入节点
        addNode(node);
    }

    /**
     * 删除节点
     * @param node 要删除的节点
     * @return
     */
    private String removeNode(NodeForMain2 node){
        if (node == head && node == end){
            //移除唯一的节点
            head = null;
            end = null;
        }else if (node == end){
            //移除尾节点
            end = end.pre;
            end.next = null;
        }else if (node == head){
            //移除头节点
            head = head.next;
            head.pre = null;
        }else {
            //移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    private void addNode(NodeForMain2 node){
        if (end != null){
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null){
            head = node;
        }
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2(5);
        main2.put("001","用户1信息");
        main2.put("002","用户1信息");
        main2.put("003","用户1信息");
        main2.put("004","用户1信息");
        main2.put("005","用户1信息");
        main2.get("002");
        main2.put("004","用户2信息更新");
        main2.put("006","用户6信息");
        System.out.println(main2.get("001"));
        System.out.println(main2.get("006"));
    }
}

class NodeForMain2 {
    NodeForMain2(String key,String value){
        this.key = key;
        this.value = value;
    }
    public NodeForMain2 pre;
    public NodeForMain2 next;
    public String key;
    public String value;
}