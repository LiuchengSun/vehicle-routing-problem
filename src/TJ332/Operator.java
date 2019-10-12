package TJ332;

import java.util.*;

/**
 * �����࣬����һЩͨ�õ�VRP��������
 * ������·���Ĳ���intra-������·����inter-
 * �ο� https://bib.irb.hr/datoteka/433524.Vehnicle_Routing_Problem.pdf ��19-21ҳ
 * ���з�������һ���µ�route,���ı�ԭ��route˳�򣬱��ڱȽ�
 * <p>
 * ע��δ�����ܼ�������ӵ����ӣ�����·�������ȣ����߿����ܲ��ܽ�ALNS��destroy��repair����Ҳ��������
 */

public class Operator {

    /**
     * ��·������: relocate
     * example: [0,1,2,3,4,5,0] ��3���뵽��1λ��ע:��ǰ��Ϊ��0λ�� -> [0,3,1,2,4,5,0]
     */
    public static <T> List<T> intraRelocate(List<T> route) {

        int len = route.size();
        int firstCustomerPos = 1; // ��һ���˿͵��������е�λ��
        int lastCustomerPos = len - 2; // ���һ���˿͵��������е�λ��

        int chosenPos = Tools.randint(firstCustomerPos, lastCustomerPos); // ����ѡrelocate�ĵ��λ��
        int relocatePos = chosenPos; // relocate��λ��
        while (chosenPos == relocatePos) {
            relocatePos = Tools.randint(firstCustomerPos, lastCustomerPos);
        }

        // ������route
        List<T> routeCopy = new ArrayList<>();
        routeCopy.addAll(route);
        routeCopy.add(relocatePos, routeCopy.get(chosenPos));
        if (chosenPos < relocatePos) {
            routeCopy.remove(chosenPos); // �����
        } else {
            routeCopy.remove(chosenPos + 1); // ��ǰ��
        }

        return routeCopy;
    }

    /**
     * ��·������: 2-opt
     * example: [0,1,2,3,4,5,0] convert between 1 and 4 -> [0,4,3,2,1,5,0]
     */
    public static <T> List<T> intra2opt(List<T> route) {

        int len = route.size();
        int firstCustomerPos = 1; // ��һ���˿͵��������е�λ��
        int lastCustomerPos = len - 2; // ���һ���˿͵��������е�λ��

        // ������ת��ʼ��start��end
        int start = 0;
        int end = 0;
        while (start == end) {
            start = Tools.randint(firstCustomerPos, lastCustomerPos - 1);
            end = Tools.randint(start + 1, lastCustomerPos);
        }

        // ������route
        List<T> routeCopy = new ArrayList<>();
        routeCopy.addAll(route.subList(0, start));
        for (int i = end; i >= start; i--) {
            routeCopy.add(route.get(i));
        }
        routeCopy.addAll(route.subList(end + 1, len));

        return routeCopy;
    }


}
