package com.zjh.demo.subject;

import java.util.*;

/**
 * @创建人 yangjianping
 * @创建时间 2021/4/2 17:51
 * @描述
 */
public class DuplicateRemoval {

    public static void main(String[] args) {
        String url = "http://aaw3esdasd?a=goond&b=cood&c=1&a=nidg&c=2&c=1&b=cood";
        System.out.println(getNewUrl(url));
    }

    /**
     * 去重后拼接返回新的url
     * 
     * @param url
     * @return newUrl
     */
    public static String getNewUrl(String url) {
        urlCheck(url);
        Map<String, Set<String>> paramMap = new TreeMap<>();
        String tPrefix = url.substring(0, url.lastIndexOf("?"));
        String paramList = url.substring(url.lastIndexOf("?") + 1);
        String[] param = paramList.split("&");
        for (String p : param) {
            String[] keyValue = p.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            if (paramMap.get(key) == null) {
                Set<String> set = new TreeSet<>();
                set.add(value);
                paramMap.put(key, set);
            } else {
                Set<String> currentSet = paramMap.get(key);
                currentSet.add(value);
                paramMap.put(key, currentSet);
            }
        }
        Iterator<Map.Entry<String, Set<String>>> iterator = paramMap.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> next = iterator.next();
            String key = next.getKey();
            Set<String> value = next.getValue();
            stringBuilder.append(key + "=");
            stringBuilder.append(buildValue(value));
            if (iterator.hasNext()) {
                stringBuilder.append("&");
            }

        }
        return tPrefix + "?" + stringBuilder.toString();
    }

    private static String buildValue(Set<String> value) {
        Iterator<String> iterator = value.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (iterator.hasNext()) {
                sb.append(next + "-");
            } else {
                sb.append(next);
            }
        }
        return sb.toString();
    }

    private static void urlCheck(String url) {
        // TODO url规则校验
    }
}
