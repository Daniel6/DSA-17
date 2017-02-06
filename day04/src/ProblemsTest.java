/**
 *
 */

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.Test;

public class ProblemsTest {

    /**
     * Test method for {@link Problems#getCountMap(int[])}.
     */
    @Test
    public void testCountMap() {
        int[] in = new int[]{1, 1, 3, 4, 1, 4, 5};
        Map m = Problems.getCountMap(in);
        assertThat(m.get(1), is(3));
        assertThat(m.get(3), is(1));
        assertThat(m.get(4), is(2));
        assertThat(m.get(5), is(1));

        in = new int[]{3, 4, 1, 4, 4};
        m = Problems.getCountMap(in);
        assertThat(m.get(3), is(1));
        assertThat(m.get(4), is(3));
        assertThat(m.get(1), is(1));

        in = new int[]{1, 2, 3, 4};
        m = Problems.getCountMap(in);
        assertThat(m.get(1), is(1));
        assertThat(m.get(2), is(1));
        assertThat(m.get(3), is(1));
        assertThat(m.get(4), is(1));

        in = new int[]{2, 3, 2, 3, 2, 3, 2};
        m = Problems.getCountMap(in);
        assertThat(m.get(2), is(4));
        assertThat(m.get(3), is(3));

    }


    /**
     * Test method for {@link Problems#removeKDigits(int[], int)}.
     */
    @Test
    public void testRemoveKDigits() {
        int[] in = new int[]{1, 4, 3, 2, 2, 1, 9};
        Object[] out = Problems.removeKDigits(in, 3).toArray();
        assertThat(out, is(new Integer[]{1, 2, 1, 9}));

        in = new int[]{1, 0, 2, 0, 0};
        out = Problems.removeKDigits(in, 1).toArray();
        assertThat(out, is(new Integer[]{0, 2, 0, 0}));

        in = new int[]{8, 5, 9, 3, 4, 3};
        out = Problems.removeKDigits(in, 2).toArray();
        assertThat(out, is(new Integer[]{5, 3, 4, 3}));

        in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        out = Problems.removeKDigits(in, 5).toArray();
        assertThat(out, is(new Integer[]{1, 2, 3, 4}));

        in = new int[]{1, 2, 3, 1, 5, 6, 7, 2, 9};
        out = Problems.removeKDigits(in, 4).toArray();
        assertThat(out, is(new Integer[]{1, 1, 5, 2, 9}));

        in = new int[]{4, 0, 9, 1, 0, 1, 9, 4, 5, 9, 9, 6, 8, 2, 7, 2, 0, 8, 7, 2, 6, 4, 5, 1, 6, 5, 4, 8, 5, 5, 4, 9,
                8, 6, 2, 4, 4, 6, 4, 1, 3, 8, 1, 3, 1, 5, 4, 3, 4, 4};
        out = Problems.removeKDigits(in, 25).toArray();
        assertThat(out, is(new Integer[]{0, 0, 0, 1, 4, 4, 9, 8, 6, 2, 4, 4, 6, 4, 1, 3, 8, 1, 3, 1, 5, 4, 3, 4, 4}));
    }

    private Node<Integer> arrToNodes(int[] arr) {
        Node<Integer> dummyHead = new Node<>(0);
        Node<Integer> current = dummyHead;
        for (int i : arr) {
            Node<Integer> n = new Node<>(i);
            current.next = n;
            current = current.next;
        }

        return dummyHead.next;
    }

    /**
     * Test method for {@link Problems#sumLists(Node, Node)}
     */
    @Test
    public void testSumLists() {
        // Case 1: README example
        int[] a1 = {7, 4, 4, 2};
        int[] a2 = {5, 2, 9};
        int case1 = 7971;

        Node<Integer> n1 = arrToNodes(a1);
        Node<Integer> n2 = arrToNodes(a2);
        assertThat(Problems.sumLists(n1, n2), is(case1));

        // Case 2: overflow example
        int[] a3 = {9, 9, 9};
        int[] a4 = {9, 9, 9};
        int case2 = 1998;

        Node<Integer> n3 = arrToNodes(a3);
        Node<Integer> n4 = arrToNodes(a4);
        assertThat(Problems.sumLists(n3, n4), is(case2));

        // Case 3: different lengths
        int[] a5 = {1, 4};
        int[] a6 = {7, 0, 0, 0, 0, 0, 7};
        int case3 = 7000021;

        Node<Integer> n5 = arrToNodes(a5);
        Node<Integer> n6 = arrToNodes(a6);
        assertThat(Problems.sumLists(n5, n6), is(case3));

        // Case 4: zeros
        int[] a7 = {0};
        int[] a8 = {0};
        int case4 = 0;

        Node<Integer> n7 = arrToNodes(a7);
        Node<Integer> n8 = arrToNodes(a8);
        assertThat(Problems.sumLists(n7, n8), is(case4));

        // Case 5: random numbers
        int[] a9 = {7, 6, 6, 5, 1};
        int[] a10 = {5, 0, 7, 2, 1, 0};
        int case5 = 583861;

        Node<Integer> n9 = arrToNodes(a9);
        Node<Integer> n10 = arrToNodes(a10);
        assertThat(Problems.sumLists(n9, n10), is(case5));
    }

}
