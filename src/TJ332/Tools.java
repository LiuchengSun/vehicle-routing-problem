package TJ332;

/**
 * �����࣬����һЩ�Զ��徲̬����
 */
public class Tools {

    /**
     * ����һ��min-max�������������min��max
     */
    public static int randint(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}