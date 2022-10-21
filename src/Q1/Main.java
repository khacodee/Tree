package Q1;

/**
 *
 * @author CaoKha
 */
public class Main {

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        //        3
        //      1   5
        //    0  2 4  9
        int[] a = {3, 1, 5, 0, 4, 2, 9};
        System.out.print("BST after insertion: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        tree.addArray(a);
        System.out.print("\nBreadth traversal: ");
        //3 1 5 0 2 9
        tree.breadth();

        System.out.print("PreOrder traversal: ");
        //3 1 0 2 5 9
        tree.preOrder(tree.root);

        System.out.print("\nInOrder traversal: ");
        //0 1 2 3 5 9
        tree.inOrder(tree.root);

        System.out.print("\nPostOrder traversal: ");
        //0 2 1 9 5 3

        tree.postOrder(tree.root);
        System.out.println("\n10.Count: " + tree.count());
        //0
        System.out.println("11.Minimum value of BST is: " + tree.min().getData());
        //9
        System.out.println("12.Maximum value of BST is: " + tree.max().getData());

        //28
        System.out.println("13.Sum: " + tree.sum());

        //48
        System.out.println("14.AVG: " + tree.avg());

        //3
        System.out.println("15.Height of a tree: " + tree.height());

        //17
        System.out.println("16.Sum of nodes on the longest path from root to leaf node: " + tree.sumOfLongRootToLeafPathUtil());
        //balanced
        if (tree.isBalanced()) {
            System.out.println("17.Tree is balanced");
        } else {
            System.out.println("17.Tree is not balanced");
        }
        //heap
        if (tree.isHeap()) {
            System.out.println("19.Given binary tree is a Heap");
        } else {
            System.out.println("19.Given binary tree is not a Heap");
        }

        System.out.println("18.This function serves little purpose: it always returns 0");
    }
}
