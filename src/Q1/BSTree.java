package Q1;

/**
 *
 * @author CaoKha
 */
public class BSTree {

    private int nodeCount = 0;
    private int sum = 0, sumLeft , sumRight;
    Node root;
    private int maxLen;
    private int maxSum;

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

    public Node search(int elem) {
        return search(root, elem);
    }

    //Private
    private Node search(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (node.getData() == key) {
            return node;
        } else if (key < node.getData()) {
            return search(node.getLeft(), key);
        } else {
            return search(node.getRight(), key);
        }
    }

    public void insert(int elem) {
        if (root == null) {
            root = new Node(elem, null, null);
            this.nodeCount++;
            sum += root.getData();
        }
        // f is the father of p
        Node p = root, f = null;
        while (p != null) {
            if (p.getData() == elem) {
                return;
            }

            if (elem < p.getData()) {
                f = p;
                p = p.getLeft();
            } else {
                f = p;
                p = p.getRight();
            }
        }

        if (elem < f.getData()) {
            f.setLeft(new Node(elem, null, null));
            this.nodeCount++;
            sum += f.getData();
        } else {
            f.setRight(new Node(elem, null, null));
            this.nodeCount++;
            sum += f.getData();
        }
    }

///Bredth()
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

//PreOrder:
    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        print(p);
        preOrder(p.getLeft());
        preOrder(p.getRight());
    }
    

//InOrder:    
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
//Count():

    int count() {
        return size();
    }

//delete():
    public void deleteByMerging(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }
        //find f is father of p
        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.getData() > p.getData()) {
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
//Node min():

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

//Node min():
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

    

//int sum:
    float sum() {
        return addBT(root);
    }
    float addBT(Node root) {
        if (root == null) {
            return 0;
        }
        return (root.getData()+ addBT(root.getLeft())
                + addBT(root.getRight()));
    }

    float avg() {
        return (float)((sum() / nodeCount));
    }

    int height() {
        return height(root);
    }

    private void sumOfLongRootToLeafPath(Node root, int sum,
            int len) {
        // if true, then we have traversed a
        // root to leaf path
        if (root == null) {
            // update maximum length and maximum sum
            // according to the given conditions
            if (maxLen < len) {
                maxLen = len;
                maxSum = sum;
            } else if (maxLen == len && maxSum < sum) {
                maxSum = sum;
            }
            return;
        }

        // recur for left subtree
        sumOfLongRootToLeafPath(root.getLeft(), sum + root.getData(),
                len + 1);

        sumOfLongRootToLeafPath(root.getRight(), sum + root.getData(),
                len + 1);

    }

    //16  the longest path from root to leaf node
    int sumOfLongRootToLeafPathUtil() {
        // if tree is NULL, then sum is 0
        if (root == null) {
            return 0;
        }

        maxSum = Integer.MIN_VALUE;
        maxLen = 0;

        // finding the maximum sum 'maxSum' for the
        // maximum length root to leaf path
        sumOfLongRootToLeafPath(root, 0, 0);

        // required maximum sum
        return maxSum;
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
    // isbalanced

    boolean isBalanced() {
        return isBalanced(root);
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

    // This Function checks
    // the heap property in the tree.
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
            return root.getData() >= root.getLeft().getData();
        } else {
            //  Check heap property at Node and
            //  Recursive check heap property at left and
            //  right subtree
            if (root.getData() >= root.getLeft().getData()
                    && root.getData() >= root.getRight().getData()) {
                return isHeapUtil(root.getLeft())
                        && isHeapUtil(root.getRight());
            } else {
                return false;
            }
        }
    }

    //  Function to check binary
    // tree is a Heap or Not.
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

    void addArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

}
