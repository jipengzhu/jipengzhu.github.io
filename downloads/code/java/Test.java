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
}
