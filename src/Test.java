import java.util.*;

class Test {
  static class Node {
    int x, y;
    char nodeType;

    Node(int x, int y, char nodeType) {
      this.x = x;
      this.y = y;
      this.nodeType = nodeType;
    }
  }

  public static void main(String[] args) {
    char[][] grid = {
        { '.', '.', '.', '.', '.', '0', '.', '.', '.', 'S' },
        { '.', '.', '.', '.', '0', '.', '.', '.', '.', '.' },
        { '0', '.', '.', '.', '.', '.', '0', '.', '.', '0' },
        { '.', '.', '.', '0', '.', '.', '.', '.', '0', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '0', '.' },
        { '.', '0', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '0', '.', '.' },
        { '.', '0', '.', '0', '.', '.', '0', '.', '.', '0' },
        { '0', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '0', '0', '.', '.', 'F', '.', '.', '0', '.' }
    };

    Node start = new Node(0, 9, 'S'); // 'S'
    Node goal = new Node(9, 5, 'F'); // 'F'
    List<Node> path = bfs(grid, start, goal);

    System.out.println("Path:");
    for (Node node : path) {
      System.out.println("(" + node.x + ", " + node.y + ")");
    }
  }

  static List<Node> bfs(char[][] grid, Node start, Node end) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] visited = new boolean[m][n];
    Node[][] prev = new Node[m][n];
    Queue<Node> queue = new LinkedList<>();

    queue.offer(start);
    visited[start.x][start.y] = true;

    int count = 0;

    while (!queue.isEmpty()) {
      count++;
      System.out.print(count + ":\t");

      for (Node node : queue) {
        System.out.print("(" + (node.y + 1) + "," + (node.x + 1) + ") ");
      }

      System.out.println();

      // Makes sure that each iteration of the loop
      // processes the next node in the queue
      Node node = queue.poll(); // removes a node from the queue and uses it
      int x = node.x;
      int y = node.y;

      if (x == end.x && y == end.y) {
        List<Node> path = new ArrayList<>();
        for (Node at = end; at != null; at = prev[at.x][at.y]) {
          path.add(at);
        }
        Collections.reverse(path);
        return path;
      }

      for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
        int newX = x, newY = y;
        while (newX >= 0 && newX < m
            && newY >= 0 && newY < n
            && grid[newX][newY] != '0') {
          newX += dir[0];
          newY += dir[1];
        }

        // Adjust for the final step that led us outside the grid or into an obstacle
        newX -= dir[0];
        newY -= dir[1];

        if (!visited[newX][newY]) {
          queue.offer(new Node(newX, newY, '.'));
          visited[newX][newY] = true;
          prev[newX][newY] = node;
        }
      }
    }

    return null; // No valid path
  }
}
