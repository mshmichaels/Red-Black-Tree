/**
 * CS 5800
 * Summer 2024
 * Homework 8/9 Red Black Tree
 * Miriam Michaels
 * Driver Class
 */

package RedBlack;

import Operations.CommandList;
import Operations.ICommand;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;

/**
 * Driver class runs the Red Black tree test code. It reads in a file called
 * "readfile.csv" that contains comma separated values. These are all added
 * to an instance of the Tree class as Nodes. Then, the user is prompted to
 * run various methods on the Red Black tree.
 */
public class Driver {

  /**
   * Main function runs the code.
   *
   * @param args the input arguments
   * @throws FileNotFoundException the file not found exception
   */
  public static void main(String[] args) throws FileNotFoundException {
    Tree rbTree = new Tree();
    Scanner scanner = new Scanner(new File("readfile.csv"));
    scanner.useDelimiter(",");
    while (scanner.hasNext()) {
      double number = scanner.nextDouble();
      rbTree.insert(number);
    }
    scanner.close();

    Scanner user = new Scanner(System.in);
    System.out.println("Choose from the following commands (q to quit):");
    System.out.println("Search: s\nDelete: d\nInsert: i\nMinimum: "
        + "min\nMaximum: max\nSuccessor: su\nPredecessor: pr\nSort: so\nPrint"
        + " Tree: pt");

    String command = user.next();
    Map<String, ICommand> commandMap = new CommandList().getCommands();

    while (!command.contains("q") && !command.contains("Q")) {
      if (commandMap.containsKey(command.toLowerCase())) {
        commandMap.get(command.toLowerCase()).execute(rbTree, user);
      }
      System.out.println("\n-----------------------------------");
      System.out.println("Search: s\nDelete: d\nInsert: i\nMinimum: "
          + "min\nMaximum: max\nSuccessor: su\nPredecessor: pr\nSort: so\nPrint"
          + " Tree: pt");
      command = user.next();

    }
    user.close();
    System.out.println("Thanks for playing!");


  }

}
