package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zwj
 * @date 2020/5/28
 */
public class TreeUtil {
    /**
     * 创建二叉搜索树
     */
    public static BinaryTreeNode<Integer> createTreeWithValues(int[] values) {
        BinaryTreeNode<Integer> root = null;
        for (int value: values) {
            //添加每一个节点
            root = addTreeNode(root, value);
        }
        return root;
    }
    /**
     * 在treeNode中添加值为value的节点
     */
    public static BinaryTreeNode<Integer> addTreeNode(BinaryTreeNode<Integer> treeNode, int value) {
        if (treeNode == null) {
            //创建节点
            treeNode = new BinaryTreeNode<>();
            treeNode.value = value;
        } else {
            //对比左右节点
            if (value <= treeNode.value) {
                treeNode.leftNode = addTreeNode(treeNode.leftNode, value);
            } else {
                treeNode.rightNode = addTreeNode(treeNode.rightNode, value);
            }
        }
        return treeNode;
    }


    public static void inOrderTraverseTree(BinaryTreeNode<Integer> rootNode) {
        if (rootNode != null) {
            //左中右，中根遍历
            inOrderTraverseTree(rootNode.leftNode);
            //中左右，先根遍历
            System.out.println(" " + rootNode.value + " ");
            //左右中，后根遍历
            inOrderTraverseTree(rootNode.rightNode);
        }
    }

    /**
     * 二叉树查找
     */
    public static BinaryTreeNode<Integer> search(BinaryTreeNode<Integer> rootNode, int value) {
        if (rootNode != null) {
            if (rootNode.value == value){
                return rootNode;
            }
            if (value > rootNode.value) {
                return search(rootNode.rightNode, value);
            } else {
                return search(rootNode.leftNode, value);
            }
        }
        return null;
    }

    /**
     * 寻找value节点的父节点
     */
    public static BinaryTreeNode<Integer> searchParent(BinaryTreeNode<Integer> rootNode, int value) {
        //如果当前节点为null，或者当前节点为根节点。返回null
        if (rootNode == null || rootNode.value == value) {
            return null;
        } else {
            //当前节点的左儿子或者右儿子等于value，则返回当前节点。
            if (rootNode.leftNode != null && value == rootNode.leftNode.value ||
                    rootNode.rightNode != null && value == rootNode.rightNode.value) {
                return rootNode;
            }
            //判断需要寻找的节点的位置，
            if (value > rootNode.value && rootNode.rightNode != null) {
                return searchParent(rootNode.rightNode, value);
            } else {
                return searchParent(rootNode.leftNode, value);
            }
        }
    }

    /**
     * 删除rootNode为根节点的二叉树中值为value的节点
     */
    public static BinaryTreeNode<Integer> delete(BinaryTreeNode<Integer> rootNode, int value) {
        //判断是否删除的节点为根节点
        if (rootNode != null && rootNode.value == value) {
            rootNode = null;
            return rootNode;
        }
        //找到删除的节点的父节点
        BinaryTreeNode<Integer> parentNode = searchParent(rootNode, value);
        //找不到父节点，表示该二叉树没有对应的节点
        if (parentNode == null) {
            return rootNode;
        }
        BinaryTreeNode<Integer> deleteNode = search(rootNode, value);
        //找不到该节点
        if (deleteNode == null) {
            return rootNode;
        }
        //需要删除的节点，为叶子节点
        if (deleteNode.leftNode == null && deleteNode.rightNode == null) {
            deleteNode = null;
            if (parentNode.leftNode != null && value == (Integer)parentNode.leftNode.value) {
                parentNode.leftNode = null;
            } else {
                parentNode.rightNode = null;
            }
        }
        //需要删除的节点，只有左子树，左子树继承该删除的位置
        else if (deleteNode.rightNode == null) {
            if (parentNode.leftNode != null && value == (Integer)parentNode.leftNode.value) {
                parentNode.leftNode = deleteNode.leftNode;
            } else {
                parentNode.rightNode = deleteNode.leftNode;
            }
        }
        //需要删除的节点，只有右子树，右子树继承该删除的位置
        else if (deleteNode.leftNode == null) {
            if (parentNode.leftNode != null && value == (Integer)parentNode.leftNode.value) {
                parentNode.leftNode = deleteNode.rightNode;
            } else {
                parentNode.rightNode = deleteNode.rightNode;
            }
        }
        //要删除的节点既有左海子，又有右孩子。需要选择一个设施的节点继承，我们选择左子树中的最右节点
        else {
            BinaryTreeNode<Integer> tmpDeleteNode = deleteNode;
            BinaryTreeNode<Integer> selectNode = tmpDeleteNode.leftNode;
            if (selectNode.rightNode == null) {
                selectNode.rightNode = deleteNode.rightNode;
            } else {
                //找到deleteNode的左子树中的最右节点，即最大节点
                while (selectNode.rightNode != null) {
                    tmpDeleteNode = selectNode;
                    selectNode = selectNode.rightNode;
                }
                //将选出的继承节点的左子树赋值给父节点的右子树
                tmpDeleteNode.rightNode = selectNode.leftNode;
                //继承节点继承需要删除的左右子树
                selectNode.leftNode = deleteNode.leftNode;
                selectNode.rightNode = deleteNode.rightNode;
            }
            //将选出的继承节点进行继承（删除对应节点）
            if (parentNode.leftNode != null && value == (Integer)parentNode.leftNode.value) {
                parentNode.leftNode = selectNode;
            } else {
                parentNode.rightNode = selectNode;
            }
        }
        return rootNode;
    }

    /**
     * 二叉树的深度
     * 从根节点到叶子节点依次经过的节点形成树的一条路径，最长路径的长度为树的深度。
     */
    public static int depthOfTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        if (root.leftNode == null && root.rightNode == null) {
            return 1;
        }
        int leftDepth = depthOfTree(root.leftNode);
        int rightDepth = depthOfTree(root.rightNode);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 二叉树的宽度
     * 各层节点数的最大值
     * @param root
     * @return
     */
    public static int widthOfTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        int maxWith = 1;
        int currentWidth = 0;
        Queue<BinaryTreeNode<Integer>> list = new LinkedList<>();
        list.add(root);

        while (list.size()>0){
            currentWidth = list.size();
            for (int i = 0; i < currentWidth; i++) {
                BinaryTreeNode<Integer> node = list.poll();
                if (node.leftNode!=null){
                    list.offer(node.leftNode);
                }
                if (node.rightNode!=null){
                    list.offer(node.rightNode);
                }
            }
            maxWith = Math.max(maxWith,currentWidth);
        }
        return maxWith;
    }

    /**
     * 二叉树某层中的节点数
     */
    public static int numberOfNodesOnLevel(BinaryTreeNode<Integer> rootNode, int level){
        if (rootNode==null || level<1){
            return 0;
        }
        //level为根节点，节点数为1
        if (level==1){
            return 1;
        }
        int left = numberOfNodesOnLevel(rootNode.leftNode, level - 1);
        int right = numberOfNodesOnLevel(rootNode.rightNode,level-1);
        return left + right;
    }

    /**
     * 二叉树的叶子节点数
     * @param rootNode
     * @return
     */
    public static int numberOfLeafsInTree(BinaryTreeNode<Integer> rootNode){
        if (rootNode==null){
            return 0;
        }
        if (rootNode.leftNode==null&&rootNode.rightNode==null){
            return 1;
        }
        return numberOfLeafsInTree(rootNode.leftNode)+numberOfLeafsInTree(rootNode.rightNode);
    }

    /**
     * 二叉树的最大距离（直径）
     * 二叉树中任意两个节点都有且仅有一条路径，这个路径的长度叫这两个节点的距离。二叉树中所有节点之间的距离的最大值就是二叉树的直径
     * @param rootNode 根节点
     * @return 直径
     */
    public static int maxDistanceOfTree(BinaryTreeNode<Integer> rootNode) {
        if (rootNode==null){
            return 0;
        }
        //节点在左右子树
        int leftAndRight = depthOfTree(rootNode.leftNode) + depthOfTree(rootNode.rightNode);
        //节点只存在左子树
        int left = maxDistanceOfTree(rootNode.leftNode);
        //节点只在右子树
        int right = maxDistanceOfTree(rootNode.rightNode);
        return Math.max(leftAndRight,Math.max(left,right));
    }

    /**
     * 二叉树的最大距离（直径）
     * @param rootNode 根节点
     * @return 直径
     */
    public static int maxDistanceOfTree2(BinaryTreeNode<Integer> rootNode){
        if (rootNode==null){
            return 0;
        }
        return getTreeNodeProperty(rootNode).distance;
    }

    private static TreeNodeProperty getTreeNodeProperty(BinaryTreeNode<Integer> node){
        if (node==null){
            return new TreeNodeProperty();
        }
        TreeNodeProperty leftNodeProperty = getTreeNodeProperty(node.leftNode);
        TreeNodeProperty rightNodeProperty = getTreeNodeProperty(node.rightNode);
        TreeNodeProperty p = new TreeNodeProperty();
        p.depth = Math.max(leftNodeProperty.depth,rightNodeProperty.depth) + 1;
        p.distance = Math.max(Math.max(leftNodeProperty.distance,rightNodeProperty.distance),leftNodeProperty.depth + rightNodeProperty.depth);
        return p;
    }

    /**
     * 二叉树中某个节点到根节点的路径
     * @param rootNode 根节点
     * @param treeNode 节点
     * @return 路径队列
     */
    public static Stack<BinaryTreeNode<Integer>> pathOfTreeNode(BinaryTreeNode<Integer> rootNode, int treeNode) {
        Stack<BinaryTreeNode<Integer>> pathList = new Stack<>();
        findRootPath(rootNode,treeNode,pathList);
        return pathList;
    }

    private static boolean findRootPath(BinaryTreeNode<Integer> rootNode, int treeNode, Stack<BinaryTreeNode<Integer>> pathList){
        if (rootNode==null){
            return false;
        }
        if (rootNode.value == treeNode){
            pathList.add(rootNode);
            return true;
        }
        //路过的压栈
        pathList.add(rootNode);
        //找左子树
        boolean find = findRootPath(rootNode.leftNode, treeNode, pathList);
        //找不到找右子树
        if (!find){
            find = findRootPath(rootNode.rightNode, treeNode, pathList);
        }
        //还找不到就出栈
        if (!find){
            pathList.pop();
        }
        return find;
    }
}
