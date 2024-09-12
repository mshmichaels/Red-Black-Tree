/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Insert Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Insert command adds a value to a Red Black Tree and prints a message to
 * the user.
 */
public class Insert implements ICommand {

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println("Enter a value to insert.");
    double value = user.nextDouble();
    rbTree.insert(value);
    System.out.println(value + " has been inserted. Current black-height "
        + "of tree: " + rbTree.blackHeight());
  }
}
