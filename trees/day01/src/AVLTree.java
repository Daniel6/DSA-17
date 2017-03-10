import sun.reflect.generics.tree.Tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n,key);
        if (n != null) {
            return balance(n);
        }
        return null;
    }
    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T>  n, T key) {
        n = super.insert(n,key);
        if (n != null) {
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n){
        n = super.deleteMin(n);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) {
            return -1;
        } else {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return n.height;
        }
    }

    public int height() {
        return Math.max(height(root),0);
    }

    // Restores the AVL tree property of the subtree.
    /*  Pseudocode implementation

        if (n is right heavy by 2 or more) {
            if (n.right is left heavy by 1) {
                n.right = rotate_right(n.right);
            }
            n = rotate_left(n);
        }

        if (n is left heavy by 2 or more) {
            if (n.left is right heavy by 1) {
                n.left = rotate_left(n.left);
            }
            n = rotate_right(n);
        }
        return n;

     */
    TreeNode<T> balance(TreeNode<T> n) {
        int balanceFactor = balanceFactor(n);
        if (balanceFactor > 1) { // very right heavy
            if (balanceFactor(n.rightChild) == -1) { // right child is slightly left heavy
                rotateRight(n.rightChild);
            }
            rotateLeft(n);
        }

        if (balanceFactor < -1) { // very left heavy
            if (balanceFactor(n.leftChild) == 1) { // left child is slightly right heavy
                rotateLeft(n.leftChild);
            }
            rotateRight(n);
        }

        // Recalculate heights
        height(n);

        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     *
     * A positive balance factors means that the right child is higher, while a negative factors indicates a taller left child.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     * Does an in-place rotation, meaning that the head of the tree before the rotation is still the head of the tree after the rotation.
     * This saves us from having to re-do pointers leading into the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> oldLeftChild = n.leftChild;

        // Swap keys
        T temp = n.key;
        n.key = oldLeftChild.key;
        oldLeftChild.key = temp;

        n.leftChild = oldLeftChild.leftChild;
        oldLeftChild.leftChild = oldLeftChild.rightChild;
        oldLeftChild.rightChild = n.rightChild;
        n.rightChild = oldLeftChild;

        return n;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     * Does an in-place rotation like rotateRight()
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> oldRightChild = n.rightChild;

        // Swap keys
        T temp = n.key;
        n.key = oldRightChild.key;
        oldRightChild.key = temp;

        n.rightChild = oldRightChild.rightChild;
        oldRightChild.rightChild = oldRightChild.leftChild;
        oldRightChild.leftChild = n.leftChild;
        n.leftChild = oldRightChild;

        return n;
    }
}
