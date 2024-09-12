/**
 * CS 5800 Summer 2024 Homework 8/9 Red Black Tree Miriam Michaels Tree Class
 */

package RedBlack;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class Tree represents a Red Black Tree.
 */
public class Tree {

  private Node root;
  private final Node sentinel;
  static final int COUNT = 10;

  /**
   * Instantiates a new Tree.
   *
   * @param data -- int, data of first node.
   */
  public Tree(int data) {
    this.sentinel = new Node(Color.BLACK);
    this.root = new Node(Color.BLACK, data, this.sentinel);
    this.root.setLeft(this.sentinel);
    this.root.setRight(this.sentinel);
  }

  /**
   * Instantiates a new Tree, overload constructor without a first node input.
   */
  public Tree() {
    this.sentinel = new Node(Color.BLACK);
    this.root = this.sentinel;
    this.root.setParent(this.sentinel);
    this.root.setLeft(this.sentinel);
    this.root.setRight(this.sentinel);

  }

  /**
   * Inserts a value into the Red Black tree. Maintains balance of the tree.
   *
   * @param data -- int, the new piece of data to insert into the tree.
   */
  public void insert(double data) {
    // instantiating new node
    Node newNode = new Node(Color.RED, data, this.sentinel);
    newNode.setRight(this.sentinel);
    newNode.setLeft(this.sentinel);

    Node prev = this.sentinel;
    Node current = this.root;

    // traversing down the tree to a leaf
    while (current != sentinel) {
      prev = current;
      if (data < current.getData()) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }
    newNode.setParent(prev); // new node is in the tree at a leaf

    if (prev == this.sentinel) { // tree was previously empty, adds as root
      this.root = newNode;
    } else {
      if (newNode.getData() < prev.getData()) {
        prev.setLeft(newNode);
      } else {
        prev.setRight(newNode);
      }
    }
    this.rbInsertFixup(newNode); // maintains Red Black Tree properties
  }

  /**
   * Runs at the end of the insert function to maintain the Red Black Tree 
   * properties.
   *
   * @param input -- Node where violation may occur
   */
  public void rbInsertFixup(Node input) {
    // while parent is red (two red nodes in a row on a branch is a violation)
    while (input.getParent().getColor() == Color.RED) {
      // if input's parent is a left child
      if (input.getParent() == input.getParent().getParent().getLeft()) {
        Node uncle = input.getParent().getParent().getRight();
        // case 1: uncle is red
        if (uncle.getColor() == Color.RED) {
          input.getParent().setColor(Color.BLACK);
          uncle.setColor(Color.BLACK);
          input.getParent().getParent().setColor(Color.RED);
          input = input.getParent().getParent();
        }
        // case 2: uncle is black
        else {
          // input is right child
          if (input == input.getParent().getRight()) { // if z is a right child
            input = input.getParent(); // z moves up, left rotate
            this.leftRotate(input);
          }
          // case 3: sets parent to black, rotates right
          input.getParent().setColor(Color.BLACK);
          input.getParent().getParent().setColor(Color.RED);
          this.rightRotate(input.getParent().getParent());
        }
      } else {
        Node uncle = input.getParent().getParent().getLeft();
        // case 1: input's parent is a right child, uncle is red
        if (uncle.getColor() == Color.RED) {
          input.getParent().setColor(Color.BLACK);
          uncle.setColor(Color.BLACK);
          input.getParent().getParent().setColor(Color.RED);
          input = input.getParent().getParent();
        } else {
          // case 2: uncle is black
          if (input == input.getParent().getLeft()) {
            input = input.getParent(); // input moves up, right rotate
            this.rightRotate(input);
          }

          //case 3
          input.getParent().setColor(Color.BLACK);
          input.getParent().getParent().setColor(Color.RED);
          this.leftRotate(input.getParent().getParent());
        }
      }

    }
    this.root.setColor(Color.BLACK);
  }

  /**
   * Rotates input node down and to the left.
   *
   * @param input -- Node, input that will be moved left and down
   */
  public void leftRotate(Node input) {
    Node rightChild = input.getRight();
    if (rightChild != sentinel) {
      // right child's left subtree becomes input's right subtree
      input.setRight(rightChild.getLeft());
      if (rightChild.getLeft() != this.sentinel) {
        // input becomes parent of subtree's root
        rightChild.getLeft().setParent(input);
      }
      // input's parent becomes right child's parent
      rightChild.setParent(input.getParent());
      if (input.getParent() == this.sentinel) { // if input was the root
        this.root = rightChild; // right child becomes the root
      } else {
        if (input == input.getParent().getLeft()) { // if input was left child
          input.getParent()
              .setLeft(rightChild); // right child becomes left child
        } else { // input is parent's right child
          input.getParent()
              .setRight(rightChild); // right child set appropriately
        }
      }
      rightChild.setLeft(input);
      input.setParent(rightChild);
    }
  }

  /**
   * Returns sentinel.
   *
   * @return Node -- the sentinel node of the Red Black tree
   */
  public Node getSentinel() {
    return this.sentinel;
  }

  /**
   * Rotates input node down and to the right.
   *
   * @param input -- Node, input that will be moved left and down
   */
  public void rightRotate(Node input) {
    Node leftChild = input.getLeft();

    if (leftChild != sentinel) {
      // left child's right subtree becomes input's left subtree
      input.setLeft(leftChild.getRight());
      // if leftChild's right subtree is non-empty, input becomes parent
      if (leftChild.getRight() != this.sentinel) {
        leftChild.getRight().setParent(input);
      }
      // input parent becomes left child's parent
      leftChild.setParent(input.getParent());
      // if input was the root, then child becomes the root
      if (input.getParent() == this.sentinel) {
        this.root = leftChild;
      } else {
        // if input was a right child, then leftChild becomes right child
        if (input == input.getParent().getRight()) {
          input.getParent().setRight(leftChild);
        }
        // if input was left child, make input become leftChild's right child
        else {
          input.getParent().setLeft(leftChild);
        }
      }
      leftChild.setRight(input);
      input.setParent(leftChild);
    }


  }

  /**
   * Finds and returns the minimum node by traversing down the left branch of
   * the tree.
   *
   * @param start Node -- where to start looking for the minimum
   * @return the minimum node
   */
  public Node minimum(Node start) {
    Node tracker = start;
    while (tracker.getLeft() != this.sentinel) {
      tracker = tracker.getLeft();
    }
    return tracker;
  }

  /**
   * Finds and returns the maximum node by traversing down the left branch of
   * the tree.
   *
   * @param start Node -- where to start looking for the maximum
   * @return the minimum node
   */
  public Node maximum(Node start) {
    Node tracker = start;
    while (tracker.getRight() != this.sentinel) {
      tracker = tracker.getRight();
    }
    return tracker;
  }

  /**
   * Returns the root of the tree.
   *
   * @return Node -- the root
   */
  public Node getRoot() {
    return this.root;
  }


  /**
   * Searches for a node and returns it. Traverses the tree left and right
   * using the Binary Tree property.
   *
   * @param data double -- the data to search for
   * @return Node -- the node if it is in the tree
   */
  public Node search(double data) {
    Node tracker = this.root;
    while (tracker != this.sentinel && tracker.getData() != data) {
      if (data < tracker.getData()) {
        tracker = tracker.getLeft();
      } else {
        tracker = tracker.getRight();
      }
    }
    return tracker;
  }


  /**
   * Finds the successor of the input node data by either: finding the
   * minimum of the node's right subtree, or by traversing up to find the
   * first ancestor of the input node that is a left child.
   *
   * @param data double -- the data to find successor of
   * @return Node -- the successor of the input data
   */
  public Node successor(double data) {
    Node start = this.search(data);
    // if the node has a right child: find min of subtree
    if (start.getRight() != this.sentinel) {
      return this.minimum(start.getRight());
    } else { // node has no right child: find first left child ancestor
      Node parent = start.getParent();
      while (parent != this.sentinel && start == parent.getRight()) {
        start = parent;
        parent = parent.getParent();
      }
      return parent;
    }
  }

  /**
   * Finds the predecessor of the input node data by either: finding the
   * maximum of the node's left subtree, or by traversing up to find the
   * first ancestor of the input node that is a right child.
   *
   * @param data double -- the data to find predecessor of
   * @return Node -- the successor of the input data
   */
  public Node predecessor(double data) {
    Node start = this.search(data);
    // if the node has a left child: find max of subtree
    if (start.getLeft() != this.sentinel) {
      return this.maximum(start.getLeft());
    } else { // node has no left child: find first right child ancestor
      Node parent = start.getParent();
      while (parent != this.sentinel && start == parent.getLeft()) {
        start = parent;
        parent = parent.getParent();
      }
      return parent;
    }
  }

  /**
   * Creates and returns a sorted list based on the data in the tree by
   * recursively traversing the list in order.
   *
   * @param root   Node, to continue traversal
   * @param result List -- the result is stored here
   */
  public void sort(Node root, ArrayList<Double> result) {
    if (root != this.sentinel) {
      sort(root.getLeft(), result);
      result.add(root.getData());
      sort(root.getRight(), result);
    }
  }

  /**
   * Takes in a node to start from and a node to transplant in. Adds the new
   * transplant in at the start node.
   *
   * @param start        Node -- where the transplant will go
   * @param transplantIn Node -- to transplant in
   */
  public void rbTransplant(Node start, Node transplantIn) {
    // start is the root
    if (start.getParent() == this.sentinel) {
      this.root = transplantIn;
    } else {
      // if first is a left child
      if (start == start.getParent().getLeft()) {
        start.getParent().setLeft(transplantIn);
      } else { // if first is a right child
        start.getParent().setRight(transplantIn);
      }
    }
    // setting the new transplant's parent correctly
    transplantIn.setParent(start.getParent());
  }

  /**
   * Deletes a node from the tree and maintains the balance of the tree by
   * correcting violations in the Red Black properties.
   *
   * @param z Node -- input to delete
   */
  public void rbDelete(Node z) {
    Node x;
    Color yOriginalColor = z.getColor();

    // z has no left child
    if (z.getLeft() == this.sentinel) {
      x = z.getRight();
      rbTransplant(z, z.getRight()); // replace z with right child
    } else { // z has left child
      if (z.getRight() == this.sentinel) { // z has no right child
        x = z.getLeft();
        rbTransplant(z, z.getLeft()); // replace z with left child

      } else { // z has both left and right children
        Node y = this.minimum(z.getRight()); // y is z's successor
        yOriginalColor = y.getColor();
        x = y.getRight();
        // if y is farther down the tree than being z's immediate child
        if (y != z.getRight()) {
          rbTransplant(y, y.getRight()); // replace y with its right child
          y.setRight(z.getRight());
          y.getRight().setParent(y);
        } else {
          x.setParent(y);
        }
        this.rbTransplant(z, y);
        y.setLeft(z.getLeft());
        y.getLeft().setParent(y);
        y.setColor(z.getColor());

      }
    }
    if (yOriginalColor == Color.BLACK) {
      rbDeleteFixup(x);
    }
  }

  /**
   * Maintains the Red Black properties by fixing violations after delete
   * has been run.
   *
   * @param x Node -- input where violations are occurring
   */
  public void rbDeleteFixup(Node x) {
    while (x != this.root && x.getColor() == Color.BLACK) {
      // x is a left child
      if (x == x.getParent().getLeft()) {
        Node w = x.getParent().getRight(); // w is x's sibling
        if (w.getColor() == Color.RED) { // x's sibling is red
          w.setColor(Color.BLACK);
          x.getParent().setColor(Color.RED);
          this.leftRotate(x.getParent());
          w = x.getParent().getRight();
        }
        // AFTER rotation, if w's children are black
        if (w.getLeft().getColor() == Color.BLACK
            && w.getRight().getColor() == Color.BLACK) {
          w.setColor(Color.RED);
          x = x.getParent();
        } else { // AFTER rotation, if w's children are not black
          if (w.getRight().getColor() == Color.BLACK) {
            w.getLeft().setColor(Color.BLACK);
            w.setColor(Color.RED);
            this.rightRotate(w);
            w = x.getParent().getRight();
          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(Color.BLACK);
          w.getRight().setColor(Color.BLACK);
          this.leftRotate(x.getParent());
          x = this.root;
        }
      } else { // x is a right child
        Node w = x.getParent().getLeft();
        if (w.getColor() == Color.RED) {
          w.setColor(Color.BLACK);
          x.getParent().setColor(Color.RED);
          rightRotate(x.getParent());
          w = x.getParent().getLeft();
        }
        if (w.getRight().getColor() == Color.BLACK
            && w.getLeft().getColor() == Color.BLACK) {
          w.setColor(Color.RED);
          x = x.getParent();
        } else {
          if (w.getLeft().getColor() == Color.BLACK) {
            w.getRight().setColor(Color.BLACK);
            w.setColor(Color.RED);
            this.leftRotate(w);
            w = x.getParent().getLeft();

          }
          w.setColor(x.getParent().getColor());
          x.getParent().setColor(Color.BLACK);
          w.getLeft().setColor(Color.BLACK);
          this.rightRotate(x.getParent());
          x = this.root;

        }
      }
    }
    x.setColor(Color.BLACK);
  }

  /**
   * Checks whether the tree is balanced by doing a walk of every branch and
   * confirming that the black heights of every branch are equal.
   *
   * @return boolean -- whether the tree is balanced
   */
  public boolean isBalanced() {
    ArrayList<Integer> blackHeight = new ArrayList<>();

    // creates list of black heights from all leaves
    this.inorderWalk(root, blackHeight);
    // checks if everything in the list is the same
    return new HashSet<Integer>(blackHeight).size() <= 1;
  }

  /**
   * Finds the black height of the tree by doing a walk of every branch and
   * returns the first height in the list.
   *
   * @return the int
   */
  public int blackHeight() {
    ArrayList<Integer> height = new ArrayList<>();

    // creates list of black heights from all leaves
    this.inorderWalk(root, height);
    // checks if everything in the list is the same
    return height.getFirst();
  }

  /**
   * Traverses each branch in order and adds the result of the black height
   * of each branch to the integer list.
   *
   * @param root -- Node, to start traversing the tree
   * @param result -- list of integers, the black heights of every branch
   */
  private void inorderWalk(Node root, ArrayList<Integer> result) {
    if (root != null && root.getLeft() == this.sentinel
        && root.getRight() == this.sentinel) {
      result.add(walkToRoot(root));
    } else {
      assert root != null;
      if (root.getLeft() != this.sentinel) {
        inorderWalk(root.getLeft(), result);
      }
      if (root.getRight() != this.sentinel) {
        inorderWalk(root.getRight(), result);
      }
    }
  }

  /**
   * Walks from a leaf to the root of the tree and returns the number of black
   * nodes in the branch.
   *
   * @param node -- Node, starting leaf of walk
   * @return int -- Black height of the tree
   */
  public int walkToRoot(Node node) {
    int result = 0;
    while (node.getParent() != this.sentinel) {
      if (node.getColor() == Color.BLACK) {
        result++;
      }
      node = node.getParent();
    }
    return result;
  }

  /**
   * Confirms that the tree does not have violations by walking every branch
   * and checking that there are not two red nodes in a row.
   *
   * @return boolean -- whether the tree has violations
   */
  public boolean noViolations() {
    ArrayList<Integer> violations = new ArrayList<>();
    this.inorderWalkForRed(root, violations);
    int result = 0;
    for (Integer violation : violations) {
      result = violation + result;
    }
    return result == 0;

  }

  /**
   * Traverses each branch in order and looks for two red nodes in a row. If
   * there are two red nodes in a row, adds 1 to the return list.
   *
   * @param root -- Node, to start traversing the tree
   * @param result -- list of integers, the number of violations in the tree
   */
  private void inorderWalkForRed(Node root, ArrayList<Integer> result) {
    if (root != null && root.getLeft() == this.sentinel
        && root.getRight() == this.sentinel) {
      result.add(walkToRootForRed(root));
    } else {
      assert root != null;
      if (root.getLeft() != this.sentinel) {
        inorderWalkForRed(root.getLeft(), result);
      }
      if (root.getRight() != this.sentinel) {
        inorderWalkForRed(root.getRight(), result);
      }
    }
  }

  /**
   * Walks from a leaf to the root of the tree and returns 1 if there are 2
   * red nodes in a row
   *
   * @param node -- Node, starting leaf of walk
   * @return int -- 1 if 2 red nodes in a row, 0 if not
   */
  private int walkToRootForRed(Node node) {
    while (node.getParent() != this.sentinel) {
      if (node.getColor() == Color.RED
          && node.getParent().getColor() == Color.RED) {
        return 1;
      }
      node = node.getParent();
    }
    return 0;
  }


  /**
   * Prints the tree horizontally, so it can be visually viewed. Recursively
   * traverses the tree in order, adding spaces appropriately.
   *
   * @param root  -- Node to start walk from
   * @param space int -- number of spaces to start with from the left side
   */
  public void treePrinter(Node root, int space) {
    if (root == sentinel) {
      return;
    }
    space += COUNT;
    treePrinter(root.getRight(), space);

    System.out.print("\n");
    for (int i = COUNT; i < space; i++) {
      System.out.print(" ");
    }
    System.out.print(root.getData() + " " + root.getColor() + "\n");

    treePrinter(root.getLeft(), space);

  }


}
