/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * TestBasicOps Command Class
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import RedBlack.Color;
import RedBlack.Node;
import RedBlack.Tree;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests basic operations of the Tree class.
 */
public class TestBasicOps {

  Tree tree;
  Node sentinel;
  Node two;
  Node three;
  Node four;
  Node fourPointFive;
  Node five;
  Node six;

  /**
   * Creates a tree and some nodes to work with.
   */
  @BeforeEach
  public void setUp() {
    this.tree = new Tree(5);
    this.sentinel = this.tree.getSentinel();
    this.five = this.tree.getRoot();
    five.setLeft(new Node(Color.RED, 3, five));
    this.three = five.getLeft();

    three.setLeft(new Node(Color.BLACK, 2, three));
    this.two = three.getLeft();
    two.setLeft(sentinel);
    two.setRight(sentinel);

    three.setRight(new Node(Color.BLACK, 4, three));
    this.four = three.getRight();
    four.setLeft(sentinel);

    four.setRight(new Node(Color.RED, 4.5, four));
    this.fourPointFive = four.getRight();
    fourPointFive.setLeft(sentinel);
    fourPointFive.setRight(sentinel);

    five.setRight(new Node(Color.BLACK, 6, five));
    this.six = five.getRight();
    six.setLeft(sentinel);
    six.setRight(sentinel);
  }

  /**
   * Tests the right and left rotate functions.
   */
  @Test
  public void testRotate() {
    Node five = this.tree.getRoot();
    five.setLeft(new Node(Color.RED, 3, five));
    Node three = five.getLeft();

    three.setLeft(new Node(Color.RED, 2, three));
    Node two = three.getLeft();
    two.setLeft(sentinel);
    two.setRight(sentinel);

    three.setRight(new Node(Color.BLACK, 4, three));
    Node four = three.getRight();
    four.setLeft(sentinel);

    four.setRight(new Node(Color.RED, 4.5, four));
    Node fourPointFive = four.getRight();
    fourPointFive.setLeft(sentinel);
    fourPointFive.setRight(sentinel);

    five.setRight(new Node(Color.BLACK, 6, five));
    Node six = five.getRight();
    six.setLeft(sentinel);
    six.setRight(sentinel);

    assertFalse(this.tree.isBalanced());
    assertFalse(this.tree.noViolations());
    two.setColor(Color.BLACK);


    assertEquals(3, five.getLeft().getData());
    assertEquals(6, five.getRight().getData());
    assertEquals(2, three.getLeft().getData());
    assertEquals(4, three.getRight().getData());
    assertEquals(4.5, four.getRight().getData());

    this.tree.rightRotate(five);
    assertFalse(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    assertEquals(2, three.getLeft().getData());
    assertEquals(5, three.getRight().getData());
    assertEquals(4, five.getLeft().getData());
    assertEquals(6, five.getRight().getData());
    assertEquals(4.5, four.getRight().getData());

    this.tree.leftRotate(three);
    assertTrue(this.tree.isBalanced());
    assertEquals(3, five.getLeft().getData());
    assertEquals(6, five.getRight().getData());
    assertEquals(2, three.getLeft().getData());
    assertEquals(4, three.getRight().getData());
    assertEquals(4.5, four.getRight().getData());

    this.tree.rightRotate(three);
    assertEquals(two, five.getLeft());
    assertEquals(three, two.getRight());
    assertEquals(four, three.getRight());

    this.tree.leftRotate(two);
    assertEquals(3, five.getLeft().getData());
    assertEquals(6, five.getRight().getData());
    assertEquals(2, three.getLeft().getData());
    assertEquals(4, three.getRight().getData());
    assertEquals(4.5, four.getRight().getData());

    six.setLeft(new Node(Color.BLACK, 5.5, six));
    Node fivePointFive = six.getLeft();
    fivePointFive.setLeft(sentinel);
    fivePointFive.setRight(sentinel);

    this.tree.rightRotate(six);
    assertEquals(5.5, five.getRight().getData());
    assertEquals(6, fivePointFive.getRight().getData());
    assertEquals(5, fivePointFive.getParent().getData());
    assertEquals(5.5, six.getParent().getData());

    this.tree.leftRotate(fivePointFive);
    assertEquals(5, six.getParent().getData());
    assertEquals(5.5, six.getLeft().getData());
    assertEquals(6, fivePointFive.getParent().getData());


  }

  /**
   * Test basic queries: minimum, maximum, predecessor, successor.
   */
  @Test
  public void testBasicQueries() {
    Node five = this.tree.getRoot();
    five.setLeft(new Node(Color.RED, 3, five));
    Node three = five.getLeft();

    three.setLeft(new Node(Color.BLACK, 2, three));
    Node two = three.getLeft();
    two.setLeft(sentinel);
    two.setRight(sentinel);

    three.setRight(new Node(Color.BLACK, 4, three));
    Node four = three.getRight();
    four.setLeft(sentinel);

    four.setRight(new Node(Color.RED, 4.5, four));
    Node fourPointFive = four.getRight();
    fourPointFive.setLeft(sentinel);
    fourPointFive.setRight(sentinel);

    five.setRight(new Node(Color.BLACK, 6, five));
    Node six = five.getRight();
    six.setLeft(sentinel);
    six.setRight(sentinel);

    assertEquals(2, tree.minimum(five).getData());
    assertEquals(2, tree.minimum(three).getData());
    assertEquals(4, tree.minimum(four).getData());

    assertEquals(6, tree.maximum(five).getData());
    assertEquals(4.5, tree.maximum(three).getData());
    assertEquals(6, tree.maximum(six).getData());

    assertEquals(3, tree.successor(2).getData());
    assertEquals(4, tree.successor(3).getData());
    assertEquals(5, tree.successor(4.5).getData());

    six.setLeft(new Node(Color.BLACK, 5.5, six));
    Node fivePointFive = six.getLeft();
    fivePointFive.setLeft(sentinel);
    fivePointFive.setRight(sentinel);

    assertEquals(2, tree.predecessor(3).getData());
    assertEquals(4.5, tree.predecessor(5).getData());
    assertEquals(5.5, tree.predecessor(6).getData());
    assertEquals(5, tree.predecessor(5.5).getData());

    assertEquals(fourPointFive, tree.search(4.5));
    assertEquals(six, tree.search(6));
    assertEquals(five, tree.search(5));
  }

  /**
   * Tests the sort function.
   */
  @Test
  public void testSort() {
    ArrayList<Double> result = new ArrayList<>();
    tree.sort(five, result);
    assertEquals(6, result.size());
    for (int i = 0; i < result.size() - 1; i++) {
      assertTrue(result.get(i) < result.get(i + 1));
    }

    ArrayList<Double> resultRight = new ArrayList<>();
    tree.rightRotate(five);
    tree.sort(three, resultRight);
    for (int i = 0; i < result.size(); i ++) {
      assertEquals(result.get(i), resultRight.get(i));
    }

  }

  /**
   * Tests the walk to root function.
   */
  @Test
  public void testWalkToRoot() {
    assertEquals(1, tree.walkToRoot(two));
    assertEquals(1, tree.walkToRoot(fourPointFive));
    assertEquals(1, tree.walkToRoot(six));

    tree.rightRotate(five);
    assertEquals(1, tree.walkToRoot(two));
    assertEquals(2, tree.walkToRoot(fourPointFive));
    assertEquals(2, tree.walkToRoot(six));

  }

  /**
   * Tests the isBalanced function.
   */
  @Test
  public void testIsBalanced() {
    assertTrue(tree.isBalanced());
    tree.rightRotate(five);
    assertFalse(tree.isBalanced());

    three.setColor(Color.BLACK);
    five.setColor(Color.RED);
    assertTrue(tree.isBalanced());

    two.setLeft(new Node(Color.BLACK, 1, two));
    Node one = two.getLeft();
    one.setLeft(sentinel);
    one.setRight(sentinel);

    assertFalse(tree.isBalanced());
    one.setColor(Color.RED);
    assertTrue(tree.isBalanced());
  }


}
