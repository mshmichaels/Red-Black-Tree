/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Successor Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Successor command finds and prints the successor of an input value in a
 * Red Black tree.
 */
public class Successor implements ICommand {

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println("Enter a value.");
    double value = user.nextDouble();
    if (rbTree.search(value) != rbTree.getSentinel()) {
      if (rbTree.search(value).getData() == rbTree.maximum(rbTree.getRoot())
          .getData()) {
        System.out.println("Value is maximum in the tree.");
      } else {
        System.out.println(
            "Value's successor: " + rbTree.successor(
                value).getData());
      }
    } else {
      System.out.println("Value is not in the tree.");
    }
    System.out.println("Black height of the tree: " + rbTree.blackHeight());
  }
}
