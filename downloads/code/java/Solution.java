import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        int dotCount = 0;
        LinkedList<Character> stack = new LinkedList<>();
        char[] charArray = path.toCharArray();
        for (char c : charArray) {
            if (c == '.') {
                dotCount++;
            } else {
                if (dotCount > 0) {
                    if (dotCount == 1 && stack.peek() == '/' && c == '/') {
                        // do nothing
                    } else if (dotCount == 2 && stack.peek() == '/' && c == '/') {
                        if (stack.size() > 1) {
                            stack.pop();

                            while (stack.size() > 1 && stack.peek() != '/') {
                                stack.pop();
                            }
                        }
                    } else {
                        for (int i = 0; i < dotCount; i++) {
                            stack.push('.');
                        }
                    }

                    dotCount = 0;
                }

                if (c == '/') {
                    if (stack.isEmpty() || stack.peek() != c) {
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }
        }

        if (dotCount > 0) {
            if (dotCount == 1 && stack.peek() == '/') {
                // do nothing
            } else if (dotCount == 2 && stack.peek() == '/') {
                if (stack.size() > 1) {
                    stack.pop();

                    while (stack.size() > 1 && stack.peek() != '/') {
                        stack.pop();
                    }
                }
            } else {
                for (int i = 0; i < dotCount; i++) {
                    stack.push('.');
                }
            }

            dotCount = 0;
        }

        while (stack.size() > 1 && stack.peek() == '/') {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        testSimplifyPath1();
        testSimplifyPath2();
        testSimplifyPath3();
        testSimplifyPath4();
        testSimplifyPath5();
        testSimplifyPath6();
        testSimplifyPath7();
        testSimplifyPath8();
        testSimplifyPath9();
        testSimplifyPath10();
    }

    public static void testSimplifyPath1() {
        String s = "/home/";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath2() {
        String s = "/../";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath3() {
        String s = "/home//foo/";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath4() {
        String s = "/a/./b/../../c/";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath5() {
        String s = "/...";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath6() {
        String s = "/..";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath7() {
        String s = "/..hidden";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath8() {
        String s = "/.hidden";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath9() {
        String s = "/hello../world";
        System.out.println((new Solution().simplifyPath(s)));
    }

    public static void testSimplifyPath10() {
        String s = "/hello./world";
        System.out.println((new Solution().simplifyPath(s)));
    }
}