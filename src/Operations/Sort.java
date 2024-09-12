/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Sort Command Class
 */

package Operations;

import RedBlack.Tree;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sort command creates and prints a sorted list of all the values in a Red
 * Black Tree.
 */
public class Sort implements ICommand {

  @Override
  public void execute(Tree rbTree, Scanner user) {
    ArrayList<Double> sorted = new ArrayList<>();
    rbTree.sort(rbTree.getRoot(), sorted);
    System.out.println(sorted);
    System.out.println("Black height of the tree: " + rbTree.blackHeight());
  }
}
