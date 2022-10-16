package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort  implements Sorting{
	static double[] kopiec;
    private int size;
    private int maxsize;
	
		public void sort(double[] kopiec)
		{
/*
	        int n = kopiec.length;
	        
	        int i = (n - 2) / 2;
	        while (i >= 0) {
	            heapify(kopiec, i--, n);
	        }
	        
	        while (n > 0)
	        {
	        	kopiec[n - 1] = pop(kopiec, n);
	            n--;
	        }*/
	    }

	    private static int leftChild(int i) {
	        return (2*i + 1);
	    }
	 
	    private static int rightChild(int i) {
	        return (2*i + 2);
	    }
	    
	    private int parent(int i) {
            return i / 2;
        }

	 
	    private static void swap(int i, int j)
	    {
	        double temp = kopiec[i];
	        kopiec[i] = kopiec[j];
	        kopiec[j] = temp;
	    }
	 

	    private void downHeapify(int index) {
            if (index >= (size / 2) && index <= size)
                return;

            if (kopiec[index] < kopiec[leftChild(index)] ||
            		kopiec[index] < kopiec[rightChild(index)]) {

                if (kopiec[leftChild(index)] > kopiec[rightChild(index)]) {
                    swap(index, leftChild(index));
                    downHeapify(leftChild(index));
                } else {
                    swap(index, rightChild(index));
                    downHeapify(rightChild(index));
                }
            }
        }
	    
	    private void heapifyUp(int index) {
            double temp = kopiec[index];
            while(index>0 && temp > kopiec[parent(index)]){
            	kopiec[index] = kopiec[parent(index)];
            	index = parent(index);
            }
            kopiec[index] = temp;
        }
	 

	    
}
