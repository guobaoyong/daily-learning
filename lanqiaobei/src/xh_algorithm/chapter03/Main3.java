package xh_algorithm.chapter03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static xh_algorithm.chapter03.Main1.createBinaryTree;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-17 11:04
 * @description 二叉树的层序遍历
 */
public class Main3 {

    /**
     * 二叉树的层序遍历
     * @param root 二叉树根节点
     */
    static void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.leftChild != null){
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null){
                queue.offer(node.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{
                3,2,9,null,null,10,null,null,8,null,4
        }));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("层序遍历");
        levelOrderTraversal(treeNode);
    }
}
