import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KnapsackTest {

    private static int[] parse(String[] s) {
        int[] I = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            I[i] = Integer.parseInt(s[i]);
        }
        return I;
    }

    private static void testKnapsack(File in, File out) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(in)));
        int[] vals = parse(br.readLine().split(", "));
        int[] wts = parse(br.readLine().split(", "));
        int K = Integer.parseInt(br.readLine());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(out)));
        int O = Integer.parseInt(br.readLine());
        System.out.println("Testing input file: " + in.getName() + " with output file: " + out.getName());
        assertThat(Knapsack.maxValue(K, wts, vals), is(O));
    }

    @Test
    public void testMaxValue() throws IOException {
        File folder = new File("testcases/knapsack/");
        for (final File in : folder.listFiles()) {
            if (in.isFile()) {
                if (in.getName().substring(in.getName().length()-3).equals(".in")) {
                    String sub = in.getAbsolutePath().substring(0, in.getAbsolutePath().length() - 3);
                    File out = new File(sub + ".out");
                    testKnapsack(in, out);
                }
            }
        }
    }
}
