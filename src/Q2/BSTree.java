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
public class BSTree {

    Node root;
    private int nodeCount = 0;

    public BSTree() {
//        size = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        } else {
            this.root = null;
//            this.size = 0;
            System.out.println("Clear successfully!");
            return;
        }
    }

    public Node search(String elem) {
        return search(root, elem);
    }

    //Private
    private Node search(Node node, String key) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(key)) {
            return node;
        } else if (key.compareToIgnoreCase(node.getData()) <= 0) {
            return search(node.getLeft(), key);
        } else {
            return search(node.getRight(), key);
        }
    }

    public void insert(String elem) {
        if (root == null) {
            root = new Node(elem, null, null);
            this.nodeCount++;
            //sum += root.getData();
        }
        // f is the father of p
        Node p = root, f = null;
        while (p != null) {
            if (p.getData().equals(elem)) {
                return;
            }

            if (elem.compareToIgnoreCase(p.getData()) <= 0) {
                f = p;
                p = p.getLeft();
            } else {
                f = p;
                p = p.getRight();
            }
        }

        if (elem.compareToIgnoreCase(f.getData()) <= 0) {
            f.setLeft(new Node(elem, null, null));
            this.nodeCount++;
            //sum += f.getData();
        } else {
            f.setRight(new Node(elem, null, null));
            this.nodeCount++;
        }
    }

    void breadth() {
        Node node = root;
        if (root == null) {
            return;
        }
        int height = height(node);
        for (int i = 0; i < height; i++) {
            print_level(root, i);
            if (i == height - 1) {
                System.out.println();
            }
        }
    }

    private void print_level(Node node, int level_no) {
        if (node == null) {
            return;
        }
        if (level_no == 0) {
            System.out.print(node.getData() + " ");
        } else {
            print_level(node.getLeft(), (level_no - 1));
            print_level(node.getRight(), (level_no - 1));
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }

    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        print(p);
        preOrder(p.getLeft());
        preOrder(p.getRight());
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.getLeft());
        print(p);
        inOrder(p.getRight());
    }

//PostOrder:    
    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.getLeft());
        postOrder(p.getRight());
        print(p);
    }

    int count() {
        return size();
    }

    public void deleteByMerging(String x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }
        //find f is father of p
        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.getData().compareToIgnoreCase(p.getData()) > 0) {
                q = q.getLeft();
            } else {
                q = q.getRight();
            }
        }
        //1- p has no child
        if (p.getLeft() == null && p.getRight() == null) {
            if (f == null) {
                root = null;
            } else if (f.getLeft() == p) {
                f.setLeft(null);
            } else {
                f.setRight(null);
            }
        } //2- p has the left child only
        else if (p.getLeft() != null && p.getRight() == null) {
            if (f == null) {
                root = p.getLeft();
            } else if (f.getLeft() == p) {
                f.setLeft(p.getLeft());
            } else {
                f.setRight(p.getLeft());
            }
        } //3- p has the right child only
        else if (p.getLeft() == null && p.getRight() != null) {
            if (f == null) {
                root = p.getRight();
            } else if (f.getLeft() == p) {
                f.setLeft(p.getRight());
            } else {
                f.setRight(p.getRight());
            }
        } //4- p has both left and right children
        else if (p.getLeft() != null && p.getRight() != null) {
            //find q is the largest node on the left child of p (q is the right 
            //child with the largest key of the left child of p)
            q = p.getLeft();
            Node t = null;
            while (q.getRight() != null) {
                t = q;
                q = q.getRight();
            }
            //merge the right child of p into q
            Node rp = p.getRight();
            q.setRight(rp);
            if (f == null) {
                root = p.getLeft();
            } else if (f.getLeft() == p) {
                f.setLeft(p.getLeft());
            } else {
                f.setRight(p.getLeft());
            }
        }
    }

    Node min() {
        return minValue(root);
    }

    private Node minValue(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    Node max() {
        return maxValue(root);
    }

    private Node maxValue(Node node) {
        Node current = node;
        /* loop down to find the rightmost leaf */
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    int height() {
        return height(root);
    }

    private boolean isBalanced(Node node) {
        int lh;
        /* for height of left subtree */

        int rh;
        /* for height of right subtree */

 /* If tree is empty then return true */
        if (node == null) {
            return true;
        }

        /* Get the height of left and right sub trees */
        lh = height(node.getLeft());
        rh = height(node.getRight());

        if (Math.abs(lh - rh) <= 1 && isBalanced(node.getLeft())
                && isBalanced(node.getRight())) {
            return true;
        }

        /* If we reach here then tree is not height-balanced
         */
        return false;
    }

    boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isHeapUtil(Node root) {
        //  Base case : single
        // node satisfies property
        if (root.getLeft() == null && root.getRight() == null) {
            return true;
        }

        //  node will be in second last level
        if (root.getRight() == null) {
            //  check heap property at Node
            //  No recursive call ,
            //  because no need to check last level
            return root.getData().compareToIgnoreCase(root.getLeft().getData()) >= 0;
        } else {
            //  Check heap property at Node and
            //  Recursive check heap property at left and
            //  right subtree
            if (root.getData().compareToIgnoreCase(root.getLeft().getData()) >= 0
                    && root.getData().compareToIgnoreCase(root.getRight().getData()) >= 0) {
                return isHeapUtil(root.getLeft())
                        && isHeapUtil(root.getRight());
            } else {
                return false;
            }
        }
    }
    
    private boolean isCompleteUtil(Node root, int index,
            int number_nodes) {
        // An empty tree is complete
        if (root == null) {
            return true;
        }

        // If index assigned to current
        //  node is more than number of
        //  nodes in tree,  then tree is
        // not complete
        if (index >= number_nodes) {
            return false;
        }

        // Recur for left and right subtrees
        return isCompleteUtil(root.getLeft(), 2 * index + 1,
                number_nodes)
                && isCompleteUtil(root.getRight(), 2 * index + 2,
                        number_nodes);
    }
    
    boolean isHeap() {
        if (root == null) {
            return true;
        }

        // These two are used
        // in isCompleteUtil()
        int node_count = count();

        if (isCompleteUtil(root, 0, node_count) == true
                && isHeapUtil(root) == true) {
            return true;
        }
        return false;
    }

    void print(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(" " + node.getData());
    }

    void addArray(String[] a) {
        for (String a1 : a) {
            insert(a1);
        }
    }
}
