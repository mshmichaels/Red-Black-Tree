/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * TestRBOps Command Class
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import RedBlack.Color;
import RedBlack.Node;
import RedBlack.Tree;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Red-Black specific operations on the tree.
 */
public class TestRBOps {


  Tree tree;

  Node sentinel;

  /**
   * Sets up by creating a tree and a sentinel value to work with.
   */
  @BeforeEach
  public void setUp() {
    this.tree = new Tree();
    this.sentinel = this.tree.getSentinel();
  }

  /**
   * Tests inserting to a red black tree from empty and confirming it is
   * balanced and has no violations after every insert.
   */
  @Test
  public void testInsert() {
    this.tree.insert(5);
    assertNotEquals(this.tree.getSentinel(), this.tree.search(5));
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(6);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(4);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(3);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(2);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(2.5);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(1);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(1.5);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(7);
    this.tree.insert(5.5);
    this.tree.insert(7);
    assertTrue(this.tree.noViolations());

    this.tree.insert(7);
    assertTrue(this.tree.isBalanced());
    this.tree.insert(7);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

  }

  /**
   * Test insert from book.
   */
  @Test
  public void testInsertFromBook() {
    Tree tree1 = new Tree();
    tree1.insert(11);
    tree1.insert(2);
    tree1.insert(14);
    tree1.insert(1);
    tree1.insert(7);
    tree1.insert(15);
    tree1.insert(5);
    tree1.insert(8);
    tree1.insert(4);
    assertTrue(tree1.isBalanced());
    assertTrue(tree1.noViolations());
    tree1.treePrinter(tree1.getRoot(), 0);

    Tree tree2 = new Tree();
    tree2.insert(11);
    tree2.insert(2);
    tree2.insert(14);
    tree2.insert(1);
    tree2.insert(13);
    tree2.insert(13.5);
    tree2.insert(13.3);
    tree2.insert(12);
    tree2.insert(11.5);
    tree2.treePrinter(tree2.getRoot(), 0);
    assertTrue(tree2.isBalanced());
    assertTrue(tree2.noViolations());
    tree2.treePrinter(tree2.getRoot(), 0);

  }

  /**
   * Test transplant.
   */
  @Test
  public void testTransplant() {
    this.tree.insert(7);
    this.tree.insert(13);
    this.tree.insert(42);
    this.tree.insert(-5);
    this.tree.insert(-4);
    this.tree.insert(-3);
    this.tree.insert(-2);
    this.tree.insert(-1);
    this.tree.insert(-6);
    this.tree.insert(15);
    this.tree.insert(16);
    this.tree.insert(5);
    this.tree.insert(4);
    this.tree.insert(3);

    this.tree.treePrinter(this.tree.getRoot(), 0);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

  }

  /**
   * Test delete.
   */
  @Test
  public void testDelete() {
    this.tree.insert(11);
    Node search = this.tree.search(11);
    this.tree.rbDelete(search);
    assertSame(this.tree.getRoot(), this.tree.getSentinel());

    this.tree.insert(11);
    this.tree.insert(10);
    search = this.tree.search(10);
    this.tree.rbDelete(search);
    assertEquals(this.tree.getRoot().getLeft(), this.tree.getSentinel());
    assertEquals(this.tree.getRoot().getRight(), this.tree.getSentinel());
    assertEquals(11, this.tree.getRoot().getData());

    this.tree.insert(10);
    search = this.tree.search(11);
    this.tree.rbDelete(search);
    assertEquals(this.tree.getRoot().getLeft(), this.tree.getSentinel());
    assertEquals(this.tree.getRoot().getRight(), this.tree.getSentinel());
    assertEquals(10, this.tree.getRoot().getData());

    this.tree.insert(11);
    this.tree.insert(9);
    System.out.println("--------------");
    this.tree.treePrinter(this.tree.getRoot(), 0);

    search = this.tree.search(9);
    this.tree.rbDelete(search);

    assertEquals(this.tree.getRoot().getLeft(), this.tree.getSentinel());
    assertEquals(10, this.tree.getRoot().getData());
    assertEquals(11, this.tree.getRoot().getRight().getData());

    this.tree.insert(8);
    this.tree.insert(7);
    this.tree.insert(14);
    this.tree.insert(15);
    this.tree.insert(12);
    this.tree.insert(1);
    this.tree.insert(3);

    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(15);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(15);
    assertEquals(search, this.tree.getSentinel());
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(7);
    this.tree.rbDelete(search);
    search = this.tree.search(7);
    assertEquals(search, this.tree.getSentinel());
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(7);
    this.tree.insert(13);
    this.tree.insert(42);
    this.tree.insert(-5);
    this.tree.insert(-4);
    this.tree.insert(-3);
    this.tree.insert(-2);
    this.tree.insert(-1);
    this.tree.insert(-6);
    this.tree.insert(15);
    this.tree.insert(16);
    this.tree.insert(5);
    this.tree.insert(4);

    search = this.tree.search(3);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(3);
    assertEquals(search, this.tree.getSentinel());

    search = this.tree.search(-5);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(-5);
    assertEquals(search, this.tree.getSentinel());

    search = this.tree.search(11);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(-4);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    this.tree.insert(-4);
    search = this.tree.search(4);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    search = this.tree.search(7);
    this.tree.rbDelete(search);
    assertTrue(this.tree.isBalanced());
    assertTrue(this.tree.noViolations());

    System.out.println("--------------");
    this.tree.treePrinter(this.tree.getRoot(), 0);


  }

  /**
   * Tests randomized insert and delete on a range of values to confirm all
   * cases are tried.
   */
  @Test
  public void testRandom() {
    Random rand = new Random();

    int size = 10;

    for (int i = 0; i < size; i++) {
      int next = rand.nextInt(10000);
      this.tree.insert(next);
      assertNotEquals(this.tree.getSentinel(), this.tree.search(next));
      assertTrue(this.tree.isBalanced());
      assertTrue(this.tree.noViolations());
    }

    ArrayList<Double> sorted = new ArrayList<>();
    this.tree.sort(this.tree.getRoot(), sorted);

    assertEquals(size, sorted.size());

    ArrayList<Integer> indices = new ArrayList<Integer>(sorted.size());
    for (int i = 0; i < sorted.size(); i++) {
      indices.add(i);
    }

    ArrayList<Integer> randomIndices = new ArrayList<>();
    Random randIndex = new Random();
    while (!indices.isEmpty()) {
      int index = randIndex.nextInt(indices.size());
      randomIndices.add(indices.remove(index));
    }

    for (int i = 0; i < sorted.size(); i++) {
      Double next = sorted.get(randomIndices.get(i));
      Node search = this.tree.search(next);
      this.tree.rbDelete(search);
      assertEquals(this.tree.getSentinel(), this.tree.search(next));
      assertTrue(this.tree.isBalanced());
      assertTrue(this.tree.noViolations());
    }
    

    ArrayList<Double> emptysort = new ArrayList<>();
    this.tree.sort(this.tree.getRoot(), emptysort);

    assertEquals(0, emptysort.size());

  }


}
