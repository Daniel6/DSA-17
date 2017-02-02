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

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        Stack<Integer> myStack = new Stack<>();
        while (!s.isEmpty()) { // O(n)
            Integer e = s.pop();
            while (!myStack.isEmpty() && myStack.peek() > e) { // O(n)
                s.push(myStack.pop());
            }
            myStack.push(e);
        }

        while (!myStack.isEmpty()) { // O(n)
            s.push(myStack.pop());
        }

        return s;
    }

}
