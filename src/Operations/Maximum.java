/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Maximum Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Maximum command finds and prints the maximum value of a Red Black tree.
 */
public class Maximum implements ICommand{

  @Override
  public void execute(Tree rbTree, Scanner user) {
    System.out.println(
        rbTree.maximum(rbTree.getRoot()).getData() + " is the "
            + "maximum of the tree.");
  }
}
