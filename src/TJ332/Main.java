package TJ332;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        // 测试
        List<Integer> route = new ArrayList<>();
        for (int i = 0; i < 11 ; i++) route.add(i);
        route.add(0);

        System.out.println(Operator.intraRelocate(route));
        System.out.println(Operator.intra2opt(route));

    }
}
