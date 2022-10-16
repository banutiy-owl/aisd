package pl.edu.pw.ee;



import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSortTest {
    public static final double EPS = 0;
    
    Sorting sorter;

    @Before
    public void beforeEach() {
        sorter = new InsertionSort();
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void gdyTablicaJestNull() {
        //given
        double[] nums = null;

        //when
        sorter.sort(nums);

        //then
        assert false;
    }

    @Test
    public void gdyTablicaJestPusta() {
        //given
        double[] nums = {};

        //when
        sorter.sort(nums);
        //then
        
        int expectedSize = 0;
        Assert.assertEquals(expectedSize, nums.length);
    }

    @Test
    public void gdyTablicaMaJedenElement() {
        //given
        double[] nums = {1};

        //when
        sorter.sort(nums);

        //then
        int expectedSize = 1;
        Assert.assertEquals(expectedSize, nums.length);
    }

    @Test
    public void gdyTablicaPosortowanaRosnaco() {
        //given
        double[] nums = {1, 2, 3, 4, 5};

        //when
        sorter.sort(nums);

        //then

        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }
    
    

    @Test
    public void gdyTablicaMaDuzoWygenerowanychLiczb() {
        //given
    	double[] nums = new double[10000];
    	int i=0;
    	while (i<1000) {
    		Random random = new Random();
    		double num = random.nextDouble()+1;
    		nums[i]=num;
    		i++;
    	}
    	
    	double[] temp=nums;
    	
    	//when
        sorter.sort(nums);

        //then
        double[] expected = temp ;
        Arrays.sort(expected);
        Assert.assertArrayEquals(expected, nums, EPS);
    }
    
}
