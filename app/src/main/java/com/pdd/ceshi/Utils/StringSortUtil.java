package com.pdd.ceshi.Utils;

public class StringSortUtil {


    /**
     * 对字符串数组进行排序
     *
     * @param keys     String[] 需要排序的字符串数组
     * @return
     */
    public static String[] getUrlParam(String[] keys) {

        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - i - 1; j++) {
                String pre = keys[j];
                String next = keys[j + 1];
                if (isMoreThan(pre, next)) {
                    String temp = pre;
                    keys[j] = next;
                    keys[j + 1] = temp;
                }
            }
        }
        return keys;
    }

    /**
     * 比较两个字符串的大小，按字母的ASCII码比较
     *
     * @param pre
     * @param next
     * @return
     */
    private static boolean isMoreThan(String pre, String next) {
        if (null == pre || null == next || "".equals(pre) || "".equals(next)) {

            return false;
        }

        char[] c_pre = pre.toCharArray();
        char[] c_next = next.toCharArray();

        int minSize = Math.min(c_pre.length, c_next.length);

        for (int i = 0; i < minSize; i++) {
            if ((int) c_pre[i] > (int) c_next[i]) {
                return true;
            } else if ((int) c_pre[i] < (int) c_next[i]) {
                return false;
            }
        }
        if (c_pre.length > c_next.length) {
            return true;
        }

        return false;
    }

}
