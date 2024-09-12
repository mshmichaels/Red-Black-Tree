/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Print Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.Scanner;
/**
 * Print command prints out horizontally a Red Black Tree.
 */
public class Print implements ICommand {

  @Override
  public void execute(Tree rbTree, Scanner user) {
    rbTree.treePrinter(rbTree.getRoot(), 0);
    System.out.println("Black height of the tree: " + rbTree.blackHeight());
  }
}
