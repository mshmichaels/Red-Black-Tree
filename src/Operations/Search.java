/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Search Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Sort command searches for an input value in a Red Black Tree and prints
 * whether that value is in the tree.
 */
public class Search implements ICommand {

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println("Enter a value to search for.");
    double value = user.nextDouble();
    if (rbTree.search(value) != rbTree.getSentinel()) {
      System.out.println("Value is in the tree.");
    } else {
      System.out.println("Value is not in the tree.");
    }
    System.out.println("Black height of the tree: " + rbTree.blackHeight());

  }
}
