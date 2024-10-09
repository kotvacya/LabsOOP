package ru.ssau.tk.LR2.functions;


import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    private static final long serialVersionUID = 3257822708502394848L;

    @Override
    public void insert(double x, double y) {
        if (count == 0) addNode(x, y);
        else if (indexOfX(x) == -1) {
            Node new_node = new Node(x, y);
            if (x < leftBound()) {
                new_node.next = head;
                new_node.prev = head.prev;
                head.prev.next = new_node;
                head.prev = new_node;
                head = new_node;
            } else {
                Node fnx = floorNodeOfX(x);
                new_node.next = fnx.next;
                new_node.prev = fnx;
                fnx.next.prev = new_node;
                fnx.next = new_node;
            }
        } else {
            setY(indexOfX(x), y);
        }
        count++;
    }

    @Override
    public void remove(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) throw new IllegalArgumentException();

        if (count == 1) {
            count = 0;
            head = null;
            return;
        }

        if (index == 0) {
            head = head.next;
        }

        Node node = getNode(index);
        node.next.prev = node.prev;
        node.prev.next = node.next;

        count--;
    }

    static protected class Node {
        Node next = null, prev = null;
        double x, y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    protected int count = 0;
    private Node head = null;

    private void addNode(double x, double y) {
        Node new_node = new Node(x, y);
        if (head == null) {
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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException {
        if (xValues.length < 2) throw new IllegalArgumentException("Array length <2");
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Array length <2");

        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }

        for (int i = 0; i < count; i++) {
            double x = xFrom + (xTo - xFrom) / (count - 1) * i;
            addNode(x, source.apply(x));
        }
    }

    private Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    protected int floorIndexOfX(double x) throws IllegalArgumentException {
        if (x < leftBound()) throw new IllegalArgumentException();

        Node node = head;
        for (int i = 0; i < count; i++) {
            node = node.next;
            if (x < node.x) return i;
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        //if (count == 1) return head.y;
        double x0 = head.x;
        double x1 = head.next.x;
        double y0 = head.y;
        double y1 = head.next.y;
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        // if (count == 1) return head.y;
        double x0 = head.prev.prev.x;
        double x1 = head.prev.x;
        double y0 = head.prev.prev.y;
        double y1 = head.prev.y;
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) throws IllegalArgumentException {
        if (!(x >= getNode(floorIndex).x && x <= getNode(floorIndex + 1).x)) {
            throw new IllegalArgumentException();
        }
        Node node = getNode(floorIndex);
        return interpolate(x, node);
    }

    protected double interpolate(double x, Node node) {
        if (!(x >= node.x && x <= node.next.x)) {
            throw new IllegalArgumentException();
        }
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
    public double getX(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) throw new IllegalArgumentException();
        return getNode(index).x;
    }

    @Override
    public double getY(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) throw new IllegalArgumentException();
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) throw new IllegalArgumentException();
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node node = head;
        for (int i = 0; i < count; i++) {
            if (node.x == x) return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node node = head;
        for (int i = 0; i < count; i++) {
            if (node.y == y) return i;
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

    private Node floorNodeOfX(double x) {
        if (x < leftBound()) throw new IllegalArgumentException();
        if (x > rightBound()) return head.prev;
        Node node = head.next;
        for (int i = 1; i < count; i++) {
            if (x < node.x) return node.prev;
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

    @Override
    public Iterator<Point> iterator(){
        return new Iterator<Point>() {
            Node node = head;
            int remain = getCount();

            @Override
            public boolean hasNext() {
                return remain > 0;
            }

            @Override
            public Point next() {
                if(remain <= 0) throw new NoSuchElementException();
                remain--;
                Point res = new Point(node.x, node.y);
                node = node.next;
                return res;
            }
        };
    }


}
