class MyFirstClass {
	public static void main(String[] s) {
		MySecondClass o = new MySecondClass();
		System.out.println(o.Operation());
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				o.SetA(i);
				o.SetB(j);
				System.out.print(o.Operation());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}

class MySecondClass {
	private int a;
	private int b;
	
	MySecondClass(){
		a = 0;
		b = 0;
	}
	
	public int GetA(){
		return a;
	}
	public void SetA(int new_val){
		a = new_val;
	}
	public int GetB(){
		return b;
	}
	public void SetB(int new_val){
		b = new_val;
	}
	public int Operation(){
		return a + b;
	}
	
	
}
