import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Grid {
  private ArrayList<ArrayList<Node>> nodesArray = new ArrayList<>();
  private int[] gridDimensions = { 0, 0 }; // {row, collumn}
  private Node startNode;
  private Node endNode;

  public void breadthFirstSearch() {
    NodeQueue queue = new NodeQueue(gridDimensions[0] * gridDimensions[1]);
    queue.enqueue(startNode);

    boolean isEndNodeReached = false;

    int count = 2;
    while (!queue.isEmpty() && !isEndNodeReached) {
      for (int i = 0; i < queue.getSize(); i++) {
        Node node = queue.dequeue();
        node.setVisitedStatus(true);

        Node[] neighbours = {
            node.getTopNode(),
            node.getRightNode(),
            node.getBottomNode(),
            node.getLeftNode()
        };

        for (Node neighbour : neighbours) {
          if (neighbour != null && !neighbour.isVisited()) {
            neighbour.setVisitedStatus(true);
            neighbour.setPreviousNode(node);
            if (neighbour.getNodeType() == 'F') {
              isEndNodeReached = true;
              break;
            }
            queue.enqueue(neighbour);
          }
        }
      }
      count++;
    }

    if (endNode.getPreviousNode() != null) {
      // printPath(endNode, count);
      System.out.println(count + ". Done!\n");
    } else {
      System.out.println("No path found\n");
    }
  }

  public void aStarSearch() {
    NodePriorityQueue queue = new NodePriorityQueue(gridDimensions[0] * gridDimensions[1]);
    queue.enqueue(startNode);

    boolean isEndNodeReached = false;

    while (!queue.isEmpty() && !isEndNodeReached) {
      for (int i = 0; i < queue.getSize(); i++) {
        Node node = queue.dequeue();

        node.setVisitedStatus(true);

        Node[] neighbours = {
            getTopNeighbour(node),
            getRightNeighbour(node),
            getBottomNeighbour(node),
            getLeftNeighbour(node)
        };

        for (Node neighbour : neighbours) {
          if (neighbour != null && !neighbour.isVisited()) {
            // Find the distance from start to neighbour
            neighbour.setPreviousNode(node);
            int distanceToStart = neighbour.getDistanceToPrevious() + neighbour.getPreviousNode().getDistanceToStart();
            neighbour.setDistanceToStart(distanceToStart);

            // System.out
            // .println("(" + node.getColumn() + ", " + node.getRow() + ") - dist(s)=" +
            // node.getDistanceToStart());
            // distanceToStart = neighbour.getDistanceToPrevious() +
            // node.getDistanceToStart();

            // if (distanceToStart < neighbour.getDistanceToStart()) {
            // neighbour.setDistanceToStart(distanceToStart);
            // neighbour.setPreviousNode(node);
            // }

            // Find the distance from neighour to end
            int xDistance = endNode.getColumn() - neighbour.getColumn();
            int yDistance = endNode.getRow() - neighbour.getRow();
            double distanceToEnd = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

            neighbour.setDistanceToEnd(distanceToEnd);
            neighbour.setCombinedWeight(distanceToStart + distanceToEnd);
            neighbour.setVisitedStatus(true);

            queue.enqueue(neighbour);

            if (neighbour.getNodeType() == 'F') {
              isEndNodeReached = true;
            }
          }
        }
      }
    }
    if (endNode.getPreviousNode() != null) {
      printPath(endNode);
      // int count = printPathRecursive(endNode);
      // count++;
      // System.out.println(count + ". Done!\n");
    } else {
      System.out.println("No path found\n");
    }
  }

  public void printPath(Node node) {
    ArrayList<Node> path = new ArrayList<>();
    while (node.getPreviousNode() != null) {
      path.add(node);
      node = node.getPreviousNode();
    }
    path.add(node);
    for (int i = path.size() - 1; i >= 0; i--) {
      System.out.println("(" + (path.get(i).getColumn() + 1) + "," + (path.get(i).getRow() + 1) + ")");
    }
  }

  public int printPathRecursive(Node node) {
    int count = 999;
    if (node.getPreviousNode() != null) {
      count = printPathRecursive(node.getPreviousNode());
      count++;
    }
    if (node.getPreviousNode() == null) {
      count = 1;
      System.out.print(count + ". Start at");
    } else {
      if (node.getPreviousNode().getColumn() < node.getColumn()) {
        System.out.print(count + ". Move right to");
      } else if (node.getPreviousNode().getColumn() > node.getColumn()) {
        System.out.print(count + ". Move left to");
      }

      if (node.getPreviousNode().getRow() < node.getRow()) {
        System.out.print(count + ". Move down to");
      } else if (node.getPreviousNode().getRow() > node.getRow()) {
        System.out.print(count + ". Move up to");
      }
    }

    System.out.println(" (" + (node.getColumn() + 1) + "," + (node.getRow() + 1) + ")");
    return count;
  }

  public Node getTopNeighbour(Node currentNode) {
    int moves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();
    Node neighbour;

    // Move until hitting a rock or wall
    while ((currentRow - moves) >= 0
        && nodesArray.get(currentRow - moves).get(currentColumn).getNodeType() != '0') {

      neighbour = nodesArray.get(currentRow - moves).get(currentColumn);

      // Check if the node is an end node
      if (neighbour.getNodeType() == 'F') {
        neighbour.setDistanceToPrevious(moves);
        return neighbour;
      }
      moves++;
    }

    if (moves == 1) {
      return null;
    } else {
      // The previous valid node is now the neighbour
      neighbour = nodesArray.get(currentRow - (moves - 1)).get(currentColumn);
      neighbour.setDistanceToPrevious(moves - 1);
      return neighbour;
    }
  }

  public Node getRightNeighbour(Node currentNode) {
    int moves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();
    Node neighbour;

    // Move until hitting a rock or wall
    while ((currentColumn + moves) < gridDimensions[1]
        && nodesArray.get(currentRow).get(currentColumn + moves).getNodeType() != '0') {

      neighbour = nodesArray.get(currentRow).get(currentColumn + moves);

      // Check if the node is an end node
      if (neighbour.getNodeType() == 'F') {
        neighbour.setDistanceToPrevious(moves);
        return neighbour;
      }
      moves++;
    }

    if (moves == 1) {
      return null;
    } else {
      // The previous valid node is now the neighbour
      neighbour = nodesArray.get(currentRow).get(currentColumn + (moves - 1));
      neighbour.setDistanceToPrevious(moves - 1);
      return nodesArray.get(currentRow).get(currentColumn + (moves - 1));
    }
  }

  public Node getBottomNeighbour(Node currentNode) {
    int moves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();
    Node neighbour;

    // Move until hitting a rock or wall
    while ((currentRow + moves) < gridDimensions[0]
        && nodesArray.get(currentRow + moves).get(currentColumn).getNodeType() != '0') {

      neighbour = nodesArray.get(currentRow + moves).get(currentColumn);

      // Check if the node is an end node
      if (neighbour.getNodeType() == 'F') {
        neighbour.setDistanceToPrevious(moves);
        return neighbour;
      }
      moves++;
    }

    if (moves == 1) {
      return null;
    } else {
      // The previous valid node is now the neighbour
      neighbour = nodesArray.get(currentRow + (moves - 1)).get(currentColumn);
      neighbour.setDistanceToPrevious(moves - 1);
      return neighbour;
    }
  }

  public Node getLeftNeighbour(Node currentNode) {
    int moves = 1;
    int currentRow = currentNode.getRow();
    int currentColumn = currentNode.getColumn();
    Node neighbour;

    // Move until hitting a rock or wall
    while ((currentColumn - moves) >= 0
        && nodesArray.get(currentRow).get(currentColumn - moves).getNodeType() != '0') {

      neighbour = nodesArray.get(currentRow).get(currentColumn - moves);

      // Check if the node is an end node
      if (neighbour.getNodeType() == 'F') {
        neighbour.setDistanceToPrevious(moves);
        return neighbour;
      }
      moves++;
    }

    if (moves == 1) {
      return null;
    } else {
      // The previous valid node is now the neighbour
      neighbour = nodesArray.get(currentRow).get(currentColumn - (moves - 1));
      neighbour.setDistanceToPrevious(moves - 1);
      return nodesArray.get(currentRow).get(currentColumn - (moves - 1));
    }
  }

  public void loadMaze(String fileName) {
    String line = "";

    try {
      FileReader file = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(file);

      while ((line = reader.readLine()) != null) {
        // Contains all the nodes from a single row
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
    if (nodesArray.size() > 250) {
      System.out.println("Maze is too large to display");
    } else {
      for (ArrayList<Node> nodeRow : nodesArray) {
        for (Node node : nodeRow) {
          System.out.print(node.getNodeType() + "  ");
        }
        System.out.println();
      }
      System.out.println();
    }
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

  public ArrayList<ArrayList<Node>> getNodesArray() {
    return nodesArray;
  }

  public int[] getDimensions() {
    return gridDimensions;
  }
}
