package pl.edu.pw.ee.services;

public interface HeapInterface <T extends Comparable <T>>{
	public void put(T element);
	public T pop();
	
}
