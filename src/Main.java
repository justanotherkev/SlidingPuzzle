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

    System.out.println("Path:");
    for (Node node : path) {
      System.out.println("(" + node.getColumn() + ", " + node.getRow() + ")");
    }
  }

  static List<Node> bfs(ArrayList<ArrayList<Node>> grid, Node startNode, Node endNode) {
    int height = grid.size();
    int width = grid.get(0).size();

    boolean[][] visited = new boolean[height][width];
    Node[][] previous = new Node[height][width];
    Queue<Node> queue = new LinkedList<>();

    queue.offer(startNode);
    visited[startNode.getColumn()][startNode.getRow()] = true;

    while (!queue.isEmpty()) {
      Node node = queue.poll();
      int x = node.getColumn();
      int y = node.getRow();

      if (x == endNode.getColumn() && y == endNode.getRow()) {
        List<Node> path = new ArrayList<>();
        for (Node at = endNode; at != null; at = previous[at.getColumn()][at.getRow()]) {
          path.add(at);
        }
        Collections.reverse(path);
        return path;
      }

      for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
        int newX = x;
        int newY = y;

        while (newX >= 0 && newX < height && newY >= 0 && newY < width
            && grid.get(newX).get(newY).getNodeType() != '0') {
          newX += dir[0];
          newY += dir[1];
        }
        // Adjust for the final step that led us outside the grid or into an obstacle
        newX -= dir[0];
        newY -= dir[1];
        if (!visited[newX][newY]) {
          queue.offer(new Node('.', newX, newY));
          visited[newX][newY] = true;
          previous[newX][newY] = node;
        }
      }
    }

    return null; // No valid path
  }
}
