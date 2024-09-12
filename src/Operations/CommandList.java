/**
 * CS 5800
 * Summer 2024 Homework
 * 8/9 Red Black Tree
 * Miriam Michaels
 * Command List Class
 */

package Operations;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command list maintains a list of available commands a user may
 * perform on a Red Black Tree.
 */
public class CommandList {

  private final Map<String, ICommand> commandMap;


  /**
   * Instantiates a new Command list.
   */
  public CommandList() {
    this.commandMap = new HashMap<>();
    this.commandMap.put("d", new Delete());
    this.commandMap.put("i", new Insert());
    this.commandMap.put("min", new Minimum());
    this.commandMap.put("max", new Maximum());
    this.commandMap.put("su", new Successor());
    this.commandMap.put("so", new Sort());
    this.commandMap.put("pr", new Predecessor());
    this.commandMap.put("pt", new Print());
    this.commandMap.put("s", new Search());
  }

  /**
   * Add command.
   *
   * @param name    the name
   * @param command the command
   */
  public void addCommand(String name, ICommand command) {
    this.commandMap.put(name, command);
  }

  /**
   * Returns commands.
   *
   * @return the commands
   */
  public Map<String, ICommand> getCommands() {
    return this.commandMap;
  }
}
