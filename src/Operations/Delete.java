/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Delete Command Class
 */

package Operations;

import RedBlack.Node;
import RedBlack.Tree;
import java.util.Scanner;

/**
 * Delete command deletes an element from a Red Black Tree and prints a
 * notification to the user.
 */
public class Delete implements ICommand{


  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println("Enter a value to delete.");
    double value = user.nextDouble();
    Node search = rbTree.search(value);
    if (rbTree.search(value) != rbTree.getSentinel()) {
      rbTree.rbDelete(search);
      System.out.println("Value was deleted from the tree. Current "
          + "black-height of tree: " + rbTree.blackHeight());
    } else {
      System.out.println("Value is not in the tree.");
    }
  }
}
