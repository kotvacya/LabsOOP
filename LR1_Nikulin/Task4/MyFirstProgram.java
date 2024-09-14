import myfirstpackage.*;

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