package dataStructure.day03;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-14 8:21
 * @Description 创建一棵树
 */
public class Node {
    //数据
    private int data;
    //左孩子
    private Node leftNode;
    //右孩子
    private Node rightNode;
    public Node(int data, Node leftNode, Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
    public Node getRightNode() {
        return rightNode;
    }
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
