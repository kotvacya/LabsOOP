class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(0, 0);
        System.out.println(o.myMethod());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.set_a(i);
                o.set_b(j);
                System.out.print(o.myMethod());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

class MySecondClass {

    private int a;
    private int b;

    MySecondClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int get_a() {
        return a;
    }

    int get_b() {
        return b;
    }

    void set_a(int t) {
        a = t;
    }

    void set_b(int t) {
        b = t;
    }

    int myMethod() {
        return a + b;
    }
}