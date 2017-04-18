import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UniqueBSTsTest {

    @Test
    public void testBST1() {
        assertThat(UniqueBSTs.uniqueBSTs(1), is(1));
    }

    @Test
    public void testBST2() {
        assertThat(UniqueBSTs.uniqueBSTs(2), is(2));
    }

    @Test
    public void testBST3() {
        assertThat(UniqueBSTs.uniqueBSTs(3), is(5));
    }

    @Test
    public void testBST4() {
        assertThat(UniqueBSTs.uniqueBSTs(4), is(14));
    }

    @Test
    public void testBST5() {
        assertThat(UniqueBSTs.uniqueBSTs(5), is(42));
    }

    @Test
    public void testBST6() {
        assertThat(UniqueBSTs.uniqueBSTs(10), is(16796));
    }
}
