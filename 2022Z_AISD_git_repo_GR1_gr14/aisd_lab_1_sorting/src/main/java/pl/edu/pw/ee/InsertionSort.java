package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
    	if (nums == null) {
            throw new IllegalArgumentException("Nums cannot be null");
        } else {
        	insertionSort(nums);
        }
    }

	private void insertionSort(double[] nums) {
		
		int n=nums.length;
		
		for (int i=1; i<n;i++) {
			
			double putElement=nums[i];
			int j=0;
			
			for (j=i; j>0 && nums[j-1]>putElement; j--) {
				nums[j]=nums[j-1];
			}
			nums[j]=putElement;
		}
		
	}

}
