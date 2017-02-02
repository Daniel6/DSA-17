package your_code;

import ADTs.StackADT;

import java.util.ListIterator;
import java.util.Stack;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        int opens = 0;
        int complete = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                opens++;
            } else if (c == ')' && opens > 0) {
                opens--;
                complete++;
                if (complete > max) {
                    max = complete;
                }
            } else {
                complete = 0;
            }
        }
        return max*2;
    }

    /*
    [ 312
    [
     */
    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        Stack<Integer> myStack = new Stack<>();
        while (!s.isEmpty()) { // O(n)
            myStack.push(s.pop());
        }
        while(!myStack.isEmpty()) { // (O(n)
            ListIterator<Integer> iterator = myStack.listIterator();
            Integer max = myStack.peek();

            while(iterator.hasNext()) { // O(n)
                Integer e = iterator.next();
                if (e.compareTo(max) >= 0) {
                    max = e;
                }
            }
            myStack.remove(max); // (O(n)
            s.push(max);
        }

        return s;
    }

}
