package xh_algorithm.chapter03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import static xh_algorithm.chapter03.Main1.createBinaryTree;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-17 10:01
 * @description 二叉树的遍历
 * 非递归实现
 */
public class Main2 {

    /**
     * 二叉树非递归前序遍历
     * @param root 二叉树根节点
     */
    static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            //迭代访问左孩子
            while (treeNode != null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if (!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }

        }
    }

    /**
     * 二叉树非递归中序遍历
     * @param root 二叉树根节点
     */
    static void inOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            //迭代访问左孩子
            if (treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }else {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 二叉树非递归后序遍历
     * @param root 二叉树根节点
     * 两个栈实现
     */
    static void postOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            stack2.push(pop);
            if (pop.leftChild != null){
                stack.push(pop.leftChild);
            }
            if (pop.rightChild != null){
                stack.push(pop.rightChild);
            }
        }
        while (!stack2.isEmpty()){
            TreeNode pop = stack2.pop();
            System.out.println(pop.data);
        }
    }

    /**
     * 二叉树非递归后序遍历
     * @param root 二叉树根节点
     * 一个栈实现
     */
    static void postOrderTraveralWithStack2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        //当前访问的结点
        TreeNode curNode;
        //上次访问的结点
        TreeNode lastVisitNode;
        curNode = root;
        lastVisitNode = null;

        //把currentNode移到左子树的最下边
        while(curNode != null){
            stack.push(curNode);
            curNode = curNode.leftChild;
        }
        while(!stack.empty()){
            curNode = stack.pop();  //弹出栈顶元素
            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if(curNode.rightChild!= null && curNode.rightChild !=lastVisitNode){
                //根节点再次入栈
                stack.push(curNode);
                //进入右子树，且可肯定右子树一定不为空
                curNode = curNode.rightChild;
                while(curNode!= null){
                    //再走到右子树的最左边
                    stack.push(curNode);
                    curNode = curNode.leftChild;
                }
            }else{
                //访问
                System.out.println(curNode.data);
                //修改最近被访问的节点
                lastVisitNode = curNode;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{
                3,2,9,null,null,10,null,null,8,null,4
        }));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveralWithStack(treeNode);
        System.out.println("中序遍历");
        inOrderTraveralWithStack(treeNode);
        System.out.println("后序遍历");
        postOrderTraveralWithStack(treeNode);
        System.out.println("后序遍历2");
        postOrderTraveralWithStack2(treeNode);
    }


}
