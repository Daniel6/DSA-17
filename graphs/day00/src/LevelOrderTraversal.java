import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        List<Integer> trav = Lists.newArrayList();

        Queue<TreeNode<Integer>> lookAtThese = Queues.newArrayDeque();
        lookAtThese.add(n);
        while (lookAtThese.size() > 0) {
            TreeNode<Integer> currNode = lookAtThese.poll();
            trav.add(currNode.key);
            if (currNode.hasLeftChild()) {
                lookAtThese.add(currNode.leftChild);
            }
            if (currNode.hasRightChild()) {
                lookAtThese.add(currNode.rightChild);
            }
        }

        return trav;
    }
}
