import com.google.common.collect.Lists;

import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Collections.sort(values);
        balancedAdd(tree, values);
        return tree;
    }

    public static void balancedAdd(BinarySearchTree<Integer> tree, List<Integer> values) {
        if (values.size() == 0) {
            return;
        }

        tree.add(getMiddleValue(values));
        if (values.size() > 1) {
            List<Integer> left = values.subList(0, values.size() / 2);
            List<Integer> right = values.subList(values.size() / 2, values.size());
            balancedAdd(tree, left);
            balancedAdd(tree, right);
        }
    }

    public static int getMiddleValue(List<Integer> l) {
        return l.get(l.size() / 2);
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null ^ n2 == null) {
            return false;
        }
        if (n1.isLeaf() && n2.isLeaf() && n1 == n2) {
            return true;
        }

        Comparable n1_left = n1.hasLeftChild() ? n1.leftChild.key : null;
        Comparable n1_right = n1.hasRightChild() ? n1.rightChild.key : null;
        Comparable n2_left = n2.hasLeftChild() ? n2.leftChild.key : null;
        Comparable n2_right = n2.hasRightChild() ? n2.rightChild.key : null;

        if (n1_left == n2_left && n1_right == n2_right) {
            return isIsomorphic(n1.leftChild, n2.leftChild)
                && isIsomorphic(n1.rightChild, n2.rightChild);
        } else if (n1_left == n2_right && n1_right == n2_left) {
            return isIsomorphic(n1.leftChild, n2.rightChild)
                && isIsomorphic(n1.rightChild, n2.leftChild);
        }
        return false;
    }
}
