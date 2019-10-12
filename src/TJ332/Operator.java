package TJ332;

import java.util.*;

/**
 * 算子类，包括一些通用的VRP操作算子
 * 包括单路径的操作intra-，和两路径的inter-
 * 参考 https://bib.irb.hr/datoteka/433524.Vehnicle_Routing_Problem.pdf 第19-21页
 * 所有方法返回一个新的route,不改变原有route顺序，便于比较
 * <p>
 * 注：未来可能加入更复杂的算子，如三路径交换等；或者看看能不能将ALNS的destroy，repair方法也加在里面
 */

public class Operator {

    /**
     * 单路径操作: relocate
     * example: [0,1,2,3,4,5,0] 将3插入到第1位（注:最前面为第0位） -> [0,3,1,2,4,5,0]
     */
    public static <T> List<T> intraRelocate(List<T> route) {

        int len = route.size();
        int firstCustomerPos = 1; // 第一个顾客点在序列中的位置
        int lastCustomerPos = len - 2; // 最后一个顾客点在序列中点位置

        int chosenPos = Tools.randint(firstCustomerPos, lastCustomerPos); // 被挑选relocate的点的位置
        int relocatePos = chosenPos; // relocate的位置
        while (chosenPos == relocatePos) {
            relocatePos = Tools.randint(firstCustomerPos, lastCustomerPos);
        }

        // 构建新route
        List<T> routeCopy = new ArrayList<>();
        routeCopy.addAll(route);
        routeCopy.add(relocatePos, routeCopy.get(chosenPos));
        if (chosenPos < relocatePos) {
            routeCopy.remove(chosenPos); // 往后插
        } else {
            routeCopy.remove(chosenPos + 1); // 往前插
        }

        return routeCopy;
    }

    /**
     * 单路径操作: 2-opt
     * example: [0,1,2,3,4,5,0] convert between 1 and 4 -> [0,4,3,2,1,5,0]
     */
    public static <T> List<T> intra2opt(List<T> route) {

        int len = route.size();
        int firstCustomerPos = 1; // 第一个顾客点在序列中的位置
        int lastCustomerPos = len - 2; // 最后一个顾客点在序列中点位置

        // 产生翻转起始点start和end
        int start = 0;
        int end = 0;
        while (start == end) {
            start = Tools.randint(firstCustomerPos, lastCustomerPos - 1);
            end = Tools.randint(start + 1, lastCustomerPos);
        }

        // 构建新route
        List<T> routeCopy = new ArrayList<>();
        routeCopy.addAll(route.subList(0, start));
        for (int i = end; i >= start; i--) {
            routeCopy.add(route.get(i));
        }
        routeCopy.addAll(route.subList(end + 1, len));

        return routeCopy;
    }


}
