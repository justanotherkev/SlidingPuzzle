class Main {

  public static void main(String[] args) {

    Graph graph = new Graph();
    graph.loadMaze("test_maze.txt");
    graph.breadthFirstSearch();

  }
}