package tree;

/**
 * @author zwj
 * @date 2020/5/28
 */
public class TreeTest {

    public static void main(String[] args) {
//        int[] array = new int[]{8,3,19,1,6,14,4,7};
//        BinaryTreeNode<Integer> treeWithValues = TreeUtil.createTreeWithValues(array);
//        TreeUtil.inOrderTraverseTree(treeWithValues);
//
//        TreeUtil.search(treeWithValues,4);


        int[] array2 = new int[]{8,3,19,1,6,14,4,7,20,11,15,10,13,12,16};
        BinaryTreeNode<Integer> tree = TreeUtil.createTreeWithValues(array2);
//        BinaryTreeNode<Integer> delete = TreeUtil.delete(tree, 14);
//        int i = TreeUtil.depthOfTree(tree);
//        System.out.println("树的深度>>>>"+i);
        System.out.println("树的宽度>>>>"+TreeUtil.widthOfTree(tree));
        System.out.println("二叉树3层的节点数>>>"+TreeUtil.numberOfNodesOnLevel(tree,3));
        System.out.println("叶子节点数>>>"+TreeUtil.numberOfLeafsInTree(tree));
    }
}
