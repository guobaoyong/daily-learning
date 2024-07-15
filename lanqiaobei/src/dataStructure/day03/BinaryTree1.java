package dataStructure.day03;

import java.util.Stack;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-14 8:33
 * @Description 堆栈实现二叉树的先序中序后序遍历
 */
public class BinaryTree1 {

    /**
     * 初始化二叉树
     * @return
     */
    public Node init() {
        Node I = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, I);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        //返回根节点
        return A;
    }

    public void printNode(Node node){
        System.out.print(node.getData());
    }

    //先序遍历
    public void theFirstTraversal_Stack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        //将所有左孩子压栈
        while (node != null || stack.size() > 0) {
            //压栈之前先访问
            if (node != null) {
                printNode(node);
                stack.push(node);
                node = node.getLeftNode();
            } else {
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }

    //中序遍历
    public void theInOrderTraversal_Stack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);   //直接压栈
                node = node.getLeftNode();
            } else {
                node = stack.pop(); //出栈并访问
                printNode(node);
                node = node.getRightNode();
            }
        }
    }

    //后序遍历
    public void thePostOrderTraversal_Stack(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> output = new Stack<>();//构造一个中间栈来存储逆后序遍历的结果
        Node node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.getRightNode();
            } else {
                node = stack.pop();
                node = node.getLeftNode();
            }
        }
        while (output.size() > 0) {
            printNode(output.pop());
        }
    }

    //镜像对称(递归实现)
    public void mirrorRecursively(Node root){
        if(root == null){ //没有树直接返回
            return;
        }
        if(root.getLeftNode() == null && root.getRightNode() == null){ //如果左右结点为空，也直接返回
            return;
        }
        Node pTemp = root.getLeftNode(); //交换树的左右结点
        root.setLeftNode(root.getRightNode());
        root.setRightNode(pTemp);
        if(root.getLeftNode()!=null){ //如果左结点不为空，继续
            mirrorRecursively(root.getLeftNode());
        }
        if(root.getRightNode() != null){ //如果右结点不为空，继续
            mirrorRecursively(root.getRightNode());
        }
    }
    //镜像对称(循环栈实现)
    public void mirror2(Node root){
        if(root == null){ //没有树直接返回
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if (pop.getLeftNode() != null && pop.getRightNode() != null){
                Node pTemp = root.getLeftNode(); //交换树的左右结点
                root.setLeftNode(root.getRightNode());
                root.setRightNode(pTemp);
            }
            if(root.getLeftNode()!=null){ //如果左结点不为空，压入栈
                stack.push(root.getLeftNode());
            }
            if(root.getRightNode() != null){ //如果右结点不为空，压入栈
                stack.push(root.getRightNode());
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree1 tree = new BinaryTree1();
        Node root = tree.init();
        System.out.println("先序遍历");
        tree.theFirstTraversal_Stack(root);
        System.out.println("\n中序遍历");
        tree.theInOrderTraversal_Stack(root);
        System.out.println("\n后序遍历");
        tree.thePostOrderTraversal_Stack(root);
        tree.mirrorRecursively(root);
    }
}
