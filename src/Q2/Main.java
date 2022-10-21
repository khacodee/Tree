/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

/**
 *
 * @author CaoKha
 */
public class Main {

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        //              D         
        //           B     F
        //         A   C E   G
        String[] a = {"D", "B", "F", "A", "C", "E", "G"};
        System.out.print("Array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        tree.addArray(a);
        System.out.print("\nBreadth traversal: ");
        //D B F A C E G 
        tree.breadth();

        System.out.print("Preorder traversal: ");
        //D B A C F E G
        tree.preOrder(tree.root);

        System.out.print("\nInorder traversal: ");
        //A B C D E F G
        tree.inOrder(tree.root);

        System.out.print("\nPostorder traversal: ");
        //A C B E G F D
        tree.postOrder(tree.root);

        //7
        System.out.println("\nCount: " + tree.count());
        tree.deleteByMerging("B");
        System.out.print("Inorder after delete B:");
        tree.inOrder(tree.root);

        //A
        System.out.print("\nMin: " + tree.min().getData());
        //G
        System.out.print("\nMax: " + tree.max().getData());
        //3
        System.out.print("\nHeight: " + tree.height());
        if (tree.isBalanced()) {
            System.out.print("\n17.Tree is balanced");
        } else {
            System.out.print("\n17.Tree is not balanced");
        }
        if (tree.isHeap()) {
            System.out.print("\n19.Given binary tree is a Heap");
        } else {
            System.out.println("\n19.Given binary tree is not a Heap");
        }

    }
}
