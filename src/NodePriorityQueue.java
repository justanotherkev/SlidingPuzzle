import java.util.Arrays;

public class NodePriorityQueue {

  private Node[] priorityQueue;
  private int size;

  public NodePriorityQueue(int capacity) {
    this.priorityQueue = new Node[capacity];
    this.size = 0;
  }

  public void enqueue(Node node) {
    if (!isFull()) {

      int insertionIndex = size;

      for (int index = size; index > 0; index--) {
        if (node.getCombinedWeight() > priorityQueue[index - 1].getCombinedWeight()) {
          break;
        }
        priorityQueue[index] = priorityQueue[index - 1];
        insertionIndex = index - 1;
      }

      priorityQueue[insertionIndex] = node;
      size++;
      // System.out.println("Added: " + node.getColumn() + "," + node.getRow() + "  -  " + "(" + node.getDistanceToStart()
      //     + "+" + node.getDistanceToEnd() + ")");
      // System.out.println("Queue size: " + size);

    } else {
      System.out.println("Queue is full");
    }
  }

  public Node dequeue() {
    if (!isEmpty()) {
      Node node = priorityQueue[0];

      for (int index = 0; index < size - 1; index++) {
        priorityQueue[index] = priorityQueue[index + 1];
      }

      priorityQueue[size - 1] = null;
      size--;
      // System.out.println(
      //     "\n__________\nRemoved: " + node.getColumn() + "," + node.getRow() + "  -  " + "(" + node.getDistanceToStart()
      //         + "+" + node.getDistanceToEnd() + ")");
      // System.out.println("Queue size: " + size);
      return node;
    } else {
      System.out.println("Queue is empty");
      return null;
    }
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == priorityQueue.length;
  }

  public void printPriorityQueue() {
    for (Node node : priorityQueue) {
      System.out.println("(" + node.getColumn() + ", " + node.getRow() + ")");
    }
  }

}