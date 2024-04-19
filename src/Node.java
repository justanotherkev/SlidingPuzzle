public class Node {
  private char nodeType;
  private int row;
  private int column;
  private Node topNode = null;
  private Node rightNode = null;
  private Node bottomNode = null;
  private Node leftNode = null;

  public Node(char nodeType, int column, int row) {
    this.nodeType = nodeType;
    this.column = column;
    this.row = row;
  }

  // Getters
  public char getNodeType() {
    return nodeType;
  }
  public int getColumn() {
    return column;
  }
  public int getRow() {
    return row;
  }
  public Node getTopNode() {
    return topNode;
  }
  public Node getRightNode() {
    return rightNode;
  }
  public Node getBottomNode() {
    return bottomNode;
  }
  public Node getLeftNode() {
    return leftNode;
  }

  // Setters
  public void setTopNode(Node topNode) {
    this.topNode = topNode;
  }
  public void setRightNode(Node rightNode) {
    this.rightNode = rightNode;
  }
  public void setBottomNode(Node bottomNode) {
    this.bottomNode = bottomNode;
  }
  public void setLeftNode(Node leftNode) {
    this.leftNode = leftNode;
  }
}


// public class Node {
// private char nodeType;
// private Node topNode;
// private Node rightNode;
// private Node bottomNode;
// private Node leftNode;

// public Node(char nodeType, Node topNode, Node rightNode, Node bottomNode,
// Node leftNode) {
// this.nodeType = nodeType;
// this.topNode = topNode;
// this.rightNode = rightNode;
// this.bottomNode = bottomNode;
// this.leftNode = leftNode;
// }

// public char getNodeType() {
// return nodeType;
// }

// public Node getTopNode() {
// return topNode;
// }

// public Node getRightNode() {
// return rightNode;
// }

// public Node getBottomNode() {
// return bottomNode;
// }

// public Node getLeftNode() {
// return leftNode;
// }
// }