package myfirstpackage;

public class MySecondClass {

    private int a;
    private int b;

    public MySecondClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int get_a() {
        return a;
    }

    public int get_b() {
        return b;
    }

    public void set_a(int t) {
        a = t;
    }

    public void set_b(int t) {
        b = t;
    }

    public int myMethod() {
        return a + b;
    }
}