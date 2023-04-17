import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherDemo {

    public static void main(String[] args) {
        example1();
        example2();
        example3();
    }

    //start with e and end with d, greedy = longest sequence
    private static void example1() {
        System.out.println("----- 1");

        Pattern p = Pattern.compile("e.+d");
        Matcher m = p.matcher("extends cup end table");

        while (m.find()) {
            System.out.println("Match: " + m.group());
        }
    }

    //start with e and end with d, reluctant
    private static void example2() {
        System.out.println("----- 2");

        Pattern p = Pattern.compile("e.+?d");
        Matcher m = p.matcher("extends cup end table");

        while (m.find()) {
            System.out.println("Match: " + m.group());
        }
    }

    //replace
    private static void example3() {
        System.out.println("----- 3");

        String str = "Jon Jonathan Frank Ken Todd";
        Pattern p = Pattern.compile("Jon.*? ");
        Matcher m = p.matcher(str);

        System.out.println("Original sequence: " + str);
        str= m.replaceAll("Eric ");
        System.out.println("Modified sequence: " + str);
    }
}
