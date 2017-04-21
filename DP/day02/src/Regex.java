import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

public class Regex {

    public static boolean isMatch(String s, String p) {
        MyFSM fsm = new MyFSM(p);
        return fsm.matchString(s);
    }

    private static class MyFSM {
        private FSMNode start;

        public MyFSM(String regex) {
            start = new FSMNode();

            int i = 0;
            FSMNode currNode = start;
            FSMNode prevNode = null;

            char c;
            char prev = '\0';
            while (i < regex.length()) {
                c = regex.charAt(i);
                if (c == '*') {
                    // 0 or more of previous char
                    currNode = prevNode;
                    currNode.setCircularRedirect(String.valueOf(prev));
                } else {
                    // Char is a letter in the alphabet or a period
                    FSMNode newNode = new FSMNode();
                    currNode.setRedirect(String.valueOf(c), newNode);
                    prevNode = currNode;
                    currNode = newNode;
                }
                prev = c;
                i++;
            }

            currNode.setAsSuccessState();
        }

        public boolean matchString(String sample) {
            FSMNode curr = start;
            for (int i = 0; i < sample.length(); i++) {
                String s = String.valueOf(sample.charAt(i));

                curr = curr.get(s);
                if (curr == null) return false;
            }

            return curr.isSuccess();
        }
    }

    private static class FSMNode {
        private HashMap<String, FSMNode> redirects;
        private boolean isSuccess;

        public FSMNode() {
            redirects = Maps.newHashMap();
            isSuccess = false;
        }

        public FSMNode get(String key) {
            System.out.println(key + " in " + redirects);
            if (redirects.containsKey(key)) return redirects.get(key);
            if (redirects.containsKey(".")) return redirects.get(".");
            return null;
        }

        public void setCircularRedirect(String key) {
            redirects.put(key, this);
        }

        public void setRedirect(String key, FSMNode node) {
            redirects.put(key, node);
        }

        public void setAsSuccessState() {
            isSuccess = true;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }
}
