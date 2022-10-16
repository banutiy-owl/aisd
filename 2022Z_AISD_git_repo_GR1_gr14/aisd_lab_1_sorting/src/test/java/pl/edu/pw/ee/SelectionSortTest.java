package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public class SelectionSortTest {

    private final int EPS = 0;
    Sorting sorter;

    @Before
    public void beforeEach() {
        sorter = new SelectionSort();
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
