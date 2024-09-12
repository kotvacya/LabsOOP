package myfirstpackage;

public class MySecondClass {
	private int a;
	private int b;
	
	public MySecondClass(){
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