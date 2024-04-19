import java.util.*;

class Main {
  // static class Node {
  // int x, y;

  // Node(int x, int y) {
  // this.x = x;
  // this.y = y;
  // }
  // }

  public static void main(String[] args) {

    Graph graph = new Graph();
    graph.loadMaze("test_maze.txt");

    Node startNode = graph.getStartNode();
    Node endNode = graph.getEndNode();
    int[] gridDimensions = graph.getDimensions();

    List<Node> path = bfs(graph.getNodesArray(), startNode, endNode);

    if (path == null) {
      System.out.println("No path found");
    } else {
      System.out.println("Path:");
      for (Node node : path) {
        System.out.println("(" + node.getColumn() + ", " + node.getRow() + ")");
      }
    }
  }

  static List<Node> bfs(ArrayList<ArrayList<Node>> grid, Node start, Node goal) {
    int m = grid.size();
    int n = grid.get(0).size();

    boolean[][] visited = new boolean[m][n];
    Node[][] prev = new Node[m][n];
    Queue<Node> queue = new LinkedList<>();

    queue.offer(start);
    visited[start.getRow()][start.getColumn()] = true;

    while (!queue.isEmpty()) {
      // queue.toArray().toString();
      Node node = queue.poll();

      int x = node.getColumn(); 
      int y = node.getRow();

      if (x == goal.getColumn() && y == goal.getRow()) {
        List<Node> path = new ArrayList<>();
        for (Node at = goal; at != null; at = prev[at.getRow()][at.getColumn()]) {
          path.add(at);
        }
        Collections.reverse(path);
        return path;
      }

      for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
        int newX = x;
        int newY = y;

        // Move in the direction until hitting an obstacle
        // Prevents IndexOutOfBounds error
        while (newX >= 0 && newX < m
            && newY >= 0 && newY < n
            && grid.get(newY).get(newX).getNodeType() != '0') {
          newY += dir[0];
          newX += dir[1];
        }
        // Adjust for the final step that led us outside the grid or into an obstacle
        newY -= dir[0];
        newX -= dir[1];
        if (!visited[newY][newX]) {
          queue.offer(new Node('.', newX, newY));
          visited[newY][newX] = true;
          prev[newY][newX] = node;
        }
      }
    }
    return null; // No valid path
  }

  // static List<Node> bfs(ArrayList<ArrayList<Node>> grid, Node startNode, Node
  // endNode) {
  // int height = grid.size();
  // int width = grid.get(0).size();

  // boolean[][] visited = new boolean[height][width];
  // Node[][] previous = new Node[height][width];
  // Queue<Node> queue = new LinkedList<>();

  // queue.offer(startNode);
  // visited[startNode.getColumn()][startNode.getRow()] = true;

  // while (!queue.isEmpty()) {

  // // dequeues the first node in the queue and uses it
  // Node node = queue.poll();
  // int column = node.getColumn();
  // int row = node.getRow();

  // // Checks whether the current node is the end node
  // // If so, then find the shortest path
  // if (column == endNode.getColumn() && row == endNode.getRow()) {
  // List<Node> path = new ArrayList<>();

  // for (Node pathNode = endNode; pathNode != null; pathNode =
  // previous[pathNode.getColumn()][pathNode.getRow()]) {
  // path.add(pathNode);
  // }

  // Collections.reverse(path);
  // return path;
  // }

  // for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
  // int newColumn = column;
  // int newRow = row;

  // while (newColumn >= 0
  // && newColumn < height
  // && newRow >= 0
  // && newRow < width
  // && grid.get(newColumn).get(newRow).getNodeType() != '0') {
  // newColumn += dir[0];
  // newRow += dir[1];
  // }

  // // Adjust for the final step that led us outside the grid or into an obstacle
  // newColumn -= dir[0];
  // newRow -= dir[1];
  // if (!visited[newColumn][newRow]) {
  // queue.offer(new Node('.', newColumn, newRow));
  // visited[newColumn][newRow] = true;
  // previous[newColumn][newRow] = node;
  // }
  // }
  // }

  // return null; // No valid path
  // }
}
