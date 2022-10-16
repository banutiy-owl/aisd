package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface{
	double[] kopiec;
	int maxLength;
	int length;
	
	public Heap(int size) {
        this.maxLength = size;
        this.length = 0;
        kopiec = new double[this.maxLength + 1];
        kopiec[0] = Double.MAX_VALUE;
    }

	@Override
	public void put(Comparable element) {
		kopiec[++length] = (double) element;

		int current = length;
      //  heapifyUp(current);
		
	}

	@Override
	public Comparable pop() {
		 double max = kopiec[1];
		 kopiec[1] = kopiec[length--];
        // downHeapify(1);
         return max;
	}



}
