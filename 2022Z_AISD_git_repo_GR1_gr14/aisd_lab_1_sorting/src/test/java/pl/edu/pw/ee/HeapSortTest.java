package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;
public class HeapSortTest {

	 private final int EPS = 0;
	    Sorting sorter;

	    @Before
	    public void beforeEach() {
	        sorter = new HeapSort();
	    }
	    
	    @Test
	    public void gdyTablicaPosortowanaMalejaco() {
	        //given
	        double[] nums = {5, 4, 3, 2, 1};

	        //when
	        sorter.sort(nums);

	        //then
	        double[] expected = {1, 2, 3, 4, 5};
	        Assert.assertArrayEquals(expected, nums, EPS);
	    }
}
