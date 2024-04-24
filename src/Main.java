class Main {

  public static void main(String[] args) {

    Grid graph = new Grid();
    graph.loadMaze("test_maze.txt");
    // graph.breadthFirstSearch();
    graph.aStarSearch();

  }
}