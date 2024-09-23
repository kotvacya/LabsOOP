package ru.ssau.tk.LR2.functions;



public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

    static protected class Node {
        Node next = null, prev = null;
        double x, y;
        public Node(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    protected int count = 0;
    private Node head = null;

    private void addNode(double x, double y){
        Node new_node = new Node(x, y);
        if(head == null){
            head = new_node;
            head.prev = head.next = head;
        }

        Node last = head.prev;

        new_node.prev = last;
        new_node.next = head;
        last.next = new_node;
        head.prev = new_node;

        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        for(int i = 0; i < xValues.length; i++){
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if(xFrom > xTo){
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }

        for(int i = 0; i <= count; i++){
            double x = xFrom + (xTo-xFrom)/count*i;
            addNode(x, source.apply(x));
        }
    }

    private Node getNode(int index){
        Node node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node node = head;
        for(int i = 0; i < count; i++){
            node = node.next;
            if(x < node.x) return i;
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if(count == 1) return head.y;
        double x0 = head.x;
        double x1 = head.next.x;
        double y0 = head.y;
        double y1 = head.next.y;
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        if(count == 1) return head.y;
        double x0 = head.prev.prev.x;
        double x1 = head.prev.x;
        double y0 = head.prev.prev.y;
        double y1 = head.prev.y;
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node node = getNode(floorIndex);
        return interpolate(x, node);
    }

    protected double interpolate(double x, Node node) {
        double x0 = node.x;
        double y0 = node.y;
        double x1 = node.next.x;
        double y1 = node.next.y;
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node node = head;
        for(int i = 0; i < count; i++){
            if(node.x == x) return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node node = head;
        for(int i = 0; i < count; i++){
            if(node.y == y) return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    private Node floorNodeOfX(double x){
        Node node = head;
        for(int i = 0; i < count; i++){
            if(x < node.next.x) return node;
            node = node.next;
        }
        return head;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) == -1) {
            Node node = floorNodeOfX(x);
            return interpolate(x, node);
        }
        return getY(indexOfX(x));
    }
}
