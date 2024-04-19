import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Graph {
  private ArrayList<ArrayList<Node>> nodesArray = new ArrayList<>();
  private int[] gridDimensions = { 0, 0 }; // {row, collumn}
  private Node startNode;
  private Node endNode;

  /**
   * Loads a maze from a file and constructs nodes for each character in the maze.
   *
   * @param fileName the name of the file containing the maze
   * @return a nested integer array containing the dimensions of the loaded maze
   *         {row, column}, the start coordinates and the end coordinates
   */
  public void loadMaze(String fileName) {
    String line = "";

    try {
      FileReader file = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(file);

      while ((line = reader.readLine()) != null) {
        // Contains all the nodes in a single row
        ArrayList<Node> nodeRow = new ArrayList<>();

        // Set the number of columns
        gridDimensions[1] = line.length();

        for (int i = 0; i < line.length(); i++) {
          Node node = new Node(line.toCharArray()[i], i, gridDimensions[0]);
          nodeRow.add(node);
          if (node.getNodeType() == 'S') {
            startNode = node;
          } else if (node.getNodeType() == 'F') {
            endNode = node;
          }
        }
        nodesArray.add(nodeRow);

        // Increase row count
        gridDimensions[0]++;
      }

      reader.close();
      System.out.println("\nMaze loaded successfully");
      printMaze();
      printMazeCoordinates();

      this.setNeighbours();

    } catch (Exception exception) {
      System.out.println("Failed to load maze");
      System.out.println(exception.getMessage());
    }
  }

  public void setNeighbours() {
    for (ArrayList<Node> nodeRow : nodesArray) {
      for (Node node : nodeRow) {
        if (node.getNodeType() != '0' && node.getNodeType() != 'F') {
          node.setTopNode(this.getTopNeighbour(node));
          node.setRightNode(this.getRightNeighbour(node));
          node.setBottomNode(this.getBottomNeighbour(node));
          node.setLeftNode(this.getLeftNeighbour(node));
        }
      }
    }
  }

  public Node getRightNeighbour(Node currentNode) {
    int rightMoves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();

    // Move right until hitting a rock or wall
    while ((currentColumn + rightMoves) < gridDimensions[1]
        && nodesArray.get(currentRow).get(currentColumn + rightMoves).getNodeType() != '0') {

      // Check if the node is an end node
      if (nodesArray.get(currentRow).get(currentColumn + rightMoves).getNodeType() == 'F') {
        return nodesArray.get(currentRow).get(currentColumn + rightMoves);
      }
      rightMoves++;
    }

    if (rightMoves == 1) {
      return null;
    } else {
      return nodesArray.get(currentRow).get(currentColumn + rightMoves - 1);
    }
  }

  public Node getLeftNeighbour(Node currentNode) {
    int leftMoves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();

    // Move left until hitting a rock or wall
    while ((currentColumn - leftMoves) >= 0
        && nodesArray.get(currentRow).get(currentColumn - leftMoves).getNodeType() != '0') {

      // Check if the node is an end node
      if (nodesArray.get(currentRow).get(currentColumn - leftMoves).getNodeType() == 'F') {
        return nodesArray.get(currentRow).get(currentColumn - leftMoves);
      }
      leftMoves++;
    }
    if (leftMoves == 1) {
      return null;
    } else {
      return nodesArray.get(currentRow).get(currentColumn - leftMoves + 1);
    }
  }

  public Node getTopNeighbour(Node currentNode) {
    int topMoves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();

    // Move upwards until hitting a rock or wall
    while ((currentRow - topMoves) >= 0
        && nodesArray.get(currentRow - topMoves).get(currentColumn).getNodeType() != '0') {
      // Check if the node is an end node
      if (nodesArray.get(currentRow - topMoves).get(currentColumn).getNodeType() == 'F') {
        return nodesArray.get(currentRow - topMoves).get(currentColumn);
      }
      topMoves++;

    }
    if (topMoves == 1) {
      return null;
    } else {
      return nodesArray.get(currentRow - topMoves + 1).get(currentColumn);
    }
  }

  public Node getBottomNeighbour(Node currentNode) {
    int bottomMoves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();

    // Move downwards until hitting a rock or wall
    while ((currentRow + bottomMoves) < gridDimensions[0]
        && nodesArray.get(currentRow + bottomMoves).get(currentColumn).getNodeType() != '0') {

      // Check if the node is an end node
      if (nodesArray.get(currentRow + bottomMoves).get(currentColumn).getNodeType() == 'F') {
        return nodesArray.get(currentRow + bottomMoves).get(currentColumn);
      }
      bottomMoves++;
    }
    if (bottomMoves == 1) {
      return null;
    } else {
      return nodesArray.get(currentRow + bottomMoves - 1).get(currentColumn);
    }
  }

  /**
   * Prints the maze represented by the nodesArray.
   *
   */
  public void printMaze() {
    for (ArrayList<Node> nodeRow : nodesArray) {
      for (Node node : nodeRow) {
        System.out.print(node.getNodeType() + "  ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public void printMazeCoordinates() {
    for (ArrayList<Node> nodeRow : nodesArray) {
      for (Node node : nodeRow) {
        System.out.print("(" + node.getColumn() + ", " + node.getRow() + ") ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public Node getStartNode() {
    return startNode;
  }

  public Node getEndNode() {
    return endNode;
  }

  // public Node getRightNode(Node currentNode) {

  // if (currentNode.getColumn() < nodesArray.get(currentNode.getRow()).size() -
  // 1) {
  // return nodesArray.get(currentNode.getRow()).get(currentNode.getColumn() + 1);
  // }
  // return null;
  // }

  public ArrayList<ArrayList<Node>> getNodesArray() {
    return nodesArray;
  }

  public int[] getDimensions() {
    return gridDimensions;
  }
}
