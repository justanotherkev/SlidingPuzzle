import java.util.ArrayList;

public class NodeQueue {
  
  private ArrayList<Node> queue = new ArrayList<>();
  private int head;
  private int tail;

  public NodeQueue() {
    this.head = 0;
    this.tail = -1;
  }

  // public Node getHead() {
  //   return head;
  // }

  // public Node getTail() {
  //   return tail;
  // }

  public void enqueue(Node node) {
    queue.add(node);
  }

  public Node dequeue() {
    return queue.remove(0);
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public int size() {
    return queue.size();
  }

  public ArrayList<Node> getQueue() {
    return queue;
  }



}
