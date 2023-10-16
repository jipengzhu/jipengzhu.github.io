import java.util.List;

public class Test {

    public static void main(String[] args) {
        jsArrayLiteral2JavaArrayLiteral();
    }

    private static void jsArrayLiteral2JavaArrayLiteral() {
        String s = "[[1,2],[2,3],[3,4],[4,5]]";
        s = s.replace('[', '{');
        s = s.replace(']', '}');
        s = s.replace("{{", "{\n{");
        s = s.replace("},", "},\n");
        s = s.replace("}}", "}\n}");
        System.out.println(s);
    }

    public static class ArrayUtils {
        public static void printArray(int[] array) {
            System.out.println(java.util.Arrays.toString(array));
        }

        public static void printArray(int[] array, int len) {
            System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(array, len)));
        }

        public static void printArrays(int[][] arrays) {
            System.out.println(java.util.Arrays.deepToString(arrays));
        }
    }

    public static class ListUtils {
        public static int[] listToArray(List<Integer> list) {
            if (list == null || list.isEmpty()) {
                return new int[0];
            }

            int[] array = new int[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

            return array;
        }

        public static int[][] listsToArrays(List<List<Integer>> lists) {
            if (lists == null || lists.isEmpty()) {
                return new int[0][0];
            }

            int row = lists.size();
            int col = lists.get(0).size();
            int[][] arrays = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arrays[i][j] = lists.get(i).get(j);
                }
            }

            return arrays;
        }
    }

    int result;
    int expect;

    // String sp1 = "^\s{1,}print.*|System.out.println\((?!(java.util|expect|result))";
    // String sp2 = "ArrayUtils.print.*\((?!(expect|result))|System.out.println\((?!(java.util|expect|result))";
    // String sp3 = "public.*To[al].*\(";
    // String sc = "ArrayUtils.print|System.out.println\((?!java.util)";

    // String tp1 = "test(?!Case).*\d\("
    // String tc = "testCase\d\("
}
