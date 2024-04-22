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
      for (int index = 0; index < size; index++) {
        if (node.getCombinedWeight() <= priorityQueue[index].getCombinedWeight()) {

          // Shift values to make space for new node
          for (int i = size; i > index; i--) {
            priorityQueue[i] = priorityQueue[i - 1];
          }

          priorityQueue[index] = node;
          break;
        }
      }
      size++;
    } else {
      System.out.println("Queue is full");
    }
  }

  public Node dequeue() {
    if (!isEmpty()) {
      Node root = priorityQueue[0];

      for (int i = 0; i < size - 1; i++) {
        priorityQueue[i] = priorityQueue[i + 1];
      }
      priorityQueue[size - 1] = null;
      size--;
      return root;
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

  // private void siftUp(int index) {
  // Node node = priorityQueue[index];
  // while (index > 0 && priorityQueue[parent(index)].priority > node.priority) {
  // priorityQueue[index] = priorityQueue[parent(index)];
  // index = parent(index);
  // }
  // priorityQueue[index] = node;
  // }

  // private void siftDown(int index) {
  // Node node = priorityQueue[index];
  // int minChild;
  // while (leftChild(index) < size) {
  // minChild = leftChild(index);
  // if (rightChild(index) < size
  // && priorityQueue[rightChild(index)].priority <
  // priorityQueue[leftChild(index)].priority) {
  // minChild = rightChild(index);
  // }
  // if (priorityQueue[minChild].priority >= node.priority) {
  // break;
  // }
  // priorityQueue[index] = priorityQueue[minChild];
  // index = minChild;
  // }
  // priorityQueue[index] = node;
  // }

  // private int parent(int index) {
  // return (index - 1) / 2;
  // }

  // private int leftChild(int index) {
  // return (index * 2) + 1;
  // }

  // private int rightChild(int index) {
  // return (index * 2) + 2;
  // }

}