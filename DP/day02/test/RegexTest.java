import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegexTest {
    @Test
    public void testRegex1() {
        assertThat(Regex.isMatch("aa", "a"), is(false));
    }

    @Test
    public void testRegex2() {
        assertThat(Regex.isMatch("aaa", "aa"), is(false));
    }

    @Test
    public void testRegex3() {
        assertThat(Regex.isMatch("aaa", "aa."), is(true));
    }

    @Test
    public void testRegex4() {
        assertThat(Regex.isMatch("aaa", "a*"), is(true));
    }

    @Test
    public void testRegex5() {
        assertThat(Regex.isMatch("aaa", ".*"), is(true));
    }

    @Test
    public void testRegex6() {
        assertThat(Regex.isMatch("aab", "c*a*b"), is(true));
    }

    @Test
    public void testRegex7() {
        assertThat(Regex.isMatch("aab", "c*a*b"), is(true));
    }

    @Test
    public void testRegex8() {
        assertThat(Regex.isMatch("aaa", "aaa"), is(true));
    }

    @Test
    public void testRegex9() {
        assertThat(Regex.isMatch("aaa", "a.a"), is(true));
    }

    @Test
    public void testRegex10() {
        assertThat(Regex.isMatch("b", "b.*c"), is(false));
    }

    @Test
    public void testRegex11() {
        assertThat(Regex.isMatch("aaa", "ab*ac*a"), is(true));
    }

    @Test
    public void testRegex12() {
        assertThat(Regex.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"), is(true));
    }

    @Test
    public void testRegex13() {
        assertThat(Regex.isMatch("bbba", "..a*"), is(false));
    }

    @Test
    public void testRegex14() {
        assertThat(Regex.isMatch("bbba", "..*a"), is(true));
    }

    @Test
    public void testRegex15() {
        assertThat(Regex.isMatch("bbggab", "b.*c*"), is(true));
    }

    @Test
    public void testRegex16() {
        assertThat(Regex.isMatch("bbggab", "b.*c"), is(false));
    }

    @Test
    public void testRegex17() {
        assertThat(Regex.isMatch("bbggabcc", "b.*c"), is(true));
    }
}
