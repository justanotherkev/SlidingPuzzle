import java.util.ArrayList;

public class NodeQueue {
  
  private ArrayList<Node> queue = new ArrayList<>();
  private Node head;
  private Node tail;

  public NodeQueue(Node head, Node tail) {
    this.head = head;
    this.tail = tail;
  }

  public Node getHead() {
    return head;
  }

  public Node getTail() {
    return tail;
  }

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
