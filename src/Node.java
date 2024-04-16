public class Node {
  private char nodeType;
  private int row;
  private int column;

  public Node(char nodeType, int row, int column) {
    this.nodeType = nodeType;
    this.row = row;
    this.column = column;
  }

  public char getNodeType() {
    return nodeType;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
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