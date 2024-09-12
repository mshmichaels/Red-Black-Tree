/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Minimum Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Minimum command finds and prints the minimum value of a Red Black tree.
 */
public class Minimum implements ICommand{

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println(
        rbTree.minimum(rbTree.getRoot()).getData() + " is the "
            + "minimum of the tree.");
    System.out.println("Black height of the tree: " + rbTree.blackHeight());
  }
}
