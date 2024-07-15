package xh_algorithm.chapter03;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-15 10:28
 * @description 二叉树的遍历
 * 递归实现
 */
public class Main1 {

    /**
     * 创建二叉树
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()){
            return null;
        }
        Integer date = inputList.removeFirst();
        if (date != null){
            node = new TreeNode(date);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 二叉树的前序遍历
     * @param node 二叉树节点
     */
    static void preOrderTraveral(TreeNode node){
        if (node == null){
            return;
        }
        //根左右
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 二叉树的中序遍历
     * @param node 二叉树节点
     */
    static void inOrderTreveral(TreeNode node){
        if (node == null){
            return;
        }
        //左根右
        inOrderTreveral(node.leftChild);
        System.out.println(node.data);
        inOrderTreveral(node.rightChild);
    }

    /**
     * 二叉树的后序遍历
     * @param node 二叉树节点
     */
    static void postOrderTreveral(TreeNode node){
        if (node == null){
            return;
        }
        //左右根
        postOrderTreveral(node.leftChild);
        postOrderTreveral(node.rightChild);
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{
            3,2,9,null,null,10,null,null,8,null,4
        }));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveral(treeNode);
        System.out.println("中序遍历");
        inOrderTreveral(treeNode);
        System.out.println("后序遍历");
        postOrderTreveral(treeNode);
    }
}

/**
 * 二叉树节点
 */
class TreeNode{
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
    }
}
