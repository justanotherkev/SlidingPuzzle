public class Node {
  private char nodeType;

  private int row;
  private int column;

  private int distanceToStart = 0;
  private int distanceToPrevious = 0;

  private double distanceToEnd = 0.0;
  private double combinedWeight = 0.0;

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
    return this.nodeType;
  }

  public int getColumn() {
    return this.column;
  }

  public int getRow() {
    return this.row;
  }

  public Node getTopNode() {
    return this.topNode;
  }

  public Node getRightNode() {
    return this.rightNode;
  }

  public Node getBottomNode() {
    return this.bottomNode;
  }

  public Node getLeftNode() {
    return this.leftNode;
  }

  public boolean isVisited() {
    return this.visitedStatus;
  }

  public Node getPreviousNode() {
    return this.previousNode;
  }

  public int getDistanceToStart() {
    return this.distanceToStart;
  }

  public double getDistanceToEnd() {
    return this.distanceToEnd;
  }

  public double getCombinedWeight() {
    return this.combinedWeight;
  }

  public int getDistanceToPrevious() {
    return this.distanceToPrevious;
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

  public void setDistanceToEnd(double distanceToEnd) {
    this.distanceToEnd = distanceToEnd;
  }

  public void setCombinedWeight(double combinedWeight) {
    this.combinedWeight = combinedWeight;
  }

  public void setDistanceToPrevious(int distanceToPrevious) {
    this.distanceToPrevious = distanceToPrevious;
  }
}