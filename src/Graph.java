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
  public loadMaze(String fileName) {
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
          Node node = new Node(line.toCharArray()[i], gridDimensions[0], i);
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

    } catch (Exception exception) {
      System.out.println("Failed to load maze");
      System.out.println(exception.getMessage());
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

  public Node getRightNode(Node currentNode, ) {

    if (currentNode.getColumn() < nodesArray.get(currentNode.getRow()).size() - 1) {
      return nodesArray.get(currentNode.getRow()).get(currentNode.getColumn() + 1);
    }
    return null;
  }

  public ArrayList<ArrayList<Node>> getNodesArray() {
    return nodesArray;
  }

  public int[] getDimensions() {
    return gridDimensions;
  }
}
