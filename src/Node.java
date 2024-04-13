public class Node {
  private String nodeType;
  private Node topNode;
  private Node rightNode;
  private Node bottomNode;
  private Node leftNode;


  public Node(String nodeType, Node topNode, Node rightNode, Node bottomNode, Node leftNode) {
    this.nodeType = nodeType;
    this.topNode = topNode;
    this.rightNode = rightNode;
    this.bottomNode = bottomNode;
    this.leftNode = leftNode;
  }

  public String getNodeType() {
    return nodeType;
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
}
