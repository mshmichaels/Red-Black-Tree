package RedBlack;

public class Node {

  private Color color;
  private double data;
  private Node left = null;
  private Node right = null;
  private Node parent;

  public Node(Color color, double data, Node parent) {
    this.color = color;
    this.data = data;
    this.parent = parent;
  }

  public Node(Color color) {
    this.color = color;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public Node getParent() {
    return this.parent;
  }

  public double getData() {
    return this.data;
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color newColor) {
    this.color = newColor;
  }

  public void setParent(Node newParent) {
    this.parent = newParent;
  }

  public void setLeft(Node newLeft) {
    this.left = newLeft;
  }

  public void setRight(Node newRight) {
    this.right = newRight;
  }

}
