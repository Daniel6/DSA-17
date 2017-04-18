import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LCSTest {
    @Test
    public void testLCS1() {
        assertThat(LongestCommonSubsequence.longestCommonSubsequence("katie", "hater"), is(3));
    }

    @Test
    public void testLCS2() {
        assertThat(LongestCommonSubsequence.longestCommonSubsequence("hieroglyphology", "michaelangelo"), is(5));
    }

    @Test
    public void testLCS3() {
        assertThat(LongestCommonSubsequence.longestCommonSubsequence("822746576", "802294393"), is(4));
    }

    @Test
    public void testLCS4() {
        assertThat(LongestCommonSubsequence.longestCommonSubsequence("2035098756706740437408037396949194623134934787291398849050295457953374965820342463412248725428168866", "5359895045467841055572249860671751729903240505412224738102366766463089285662600016200835448181504939"), is(45));
    }

}
