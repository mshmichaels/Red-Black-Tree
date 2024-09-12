/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Predecessor Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Predecessor  command finds and prints the predecessor of an input value in a
 * Red Black tree.
 */
public class Predecessor implements ICommand{

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println("Enter a value.");
    double value = user.nextDouble();
    if (rbTree.search(value) != rbTree.getSentinel()) {
      if (rbTree.search(value).getData() == rbTree.minimum(rbTree.getRoot())
          .getData()) {
        System.out.println("Value is minimum in the tree.");
      }
      System.out.println(
          "Value's predecessor: " + rbTree.predecessor(
              value).getData());
    } else {
      System.out.println("Value is not in the tree.");
    }
    System.out.println("Black height of the tree: " + rbTree.blackHeight());
  }
}
