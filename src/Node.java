public class Node {
  private char nodeType;

  private int row;
  private int column;
  
  private int distanceToStart;
  private int distanceToEnd;
  private int combinedWeight;

  private boolean visitedStatus = false;

  private Node previousNode = null;

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

  public boolean isVisited() {
    return visitedStatus;
  }

  public Node getPreviousNode() {
    return this.previousNode;
  }

  public int getDistanceToStart() {
    return distanceToStart;
  }

  public int getDistanceToEnd() {
    return distanceToEnd;
  }

  public int getCombinedWeight() {
    return combinedWeight;
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

  public void setVisitedStatus(boolean visitedStatus) {
    this.visitedStatus = visitedStatus;
  }

  public void setPreviousNode(Node previousNode) {
    this.previousNode = previousNode;
  }

  public void setDistanceToStart(int distanceToStart) {
    this.distanceToStart = distanceToStart;
  }

  public void setDistanceToEnd(int distanceToEnd) {
    this.distanceToEnd = distanceToEnd;
  }

  public void setCombinedWeight(int combinedWeight) {
    this.combinedWeight = combinedWeight;
  }
}