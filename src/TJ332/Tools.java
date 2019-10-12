package TJ332;

/**
 * 工具类，包括一些自定义静态函数
 */
public class Tools {

    /**
     * 返回一个min-max间的整数，包括min和max
     */
    public static int randint(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}