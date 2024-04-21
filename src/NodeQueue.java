public class NodeQueue {

  private Node[] queue;
  private int head;
  private int tail;
  private int capacity;
  private int size;

  public NodeQueue(int capacity) {
    this.queue = new Node[capacity];
    this.head = 0;
    this.tail = 0;
    this.capacity = capacity;
    this.size = 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  public boolean isEmpty() {
    return size == 0;
  }


  public void enqueue(Node node) {
    if (!isFull()) {
      queue[tail] = node;
      tail = (tail + 1) % capacity;
      size++;
    } else {
      System.out.println("Queue is full");
    }
  }

  public Node dequeue() {
    if (!isEmpty()) {
      Node node = queue[head];
      head = (head + 1) % capacity;
      size--;
      return node;
    } else {
      System.out.println("Queue is empty");
      return null;
    }
  }

  public int getSize() {
    return size;
  }
}
