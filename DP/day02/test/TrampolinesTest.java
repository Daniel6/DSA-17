import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TrampolinesTest {

    private static int[] parse(String[] s) {
        int[] I = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            I[i] = Integer.parseInt(s[i]);
        }
        return I;
    }

    private static void testTrampoline(File in, File out) throws IOException {
        System.out.println("Testing input file: " + in.getName() + " with output file: " + out.getName());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(in)));
        int[] nums = parse(br.readLine().split(", "));
        br = new BufferedReader(new InputStreamReader(new FileInputStream(out)));
        int O = Integer.parseInt(br.readLine());
        assertThat(Trampolines.trampoline(nums), is(O));
    }

    @Test
    public void testTrampoline() throws IOException {
        File folder = new File("testcases/trampoline/");
        for (final File in : folder.listFiles()) {
            if (in.isFile()) {
                if (in.getName().substring(in.getName().length()-3).equals(".in")) {
                    String sub = in.getAbsolutePath().substring(0, in.getAbsolutePath().length() - 3);
                    File out = new File(sub + ".out");
                    testTrampoline(in, out);
                }
            }
        }
    }
}
