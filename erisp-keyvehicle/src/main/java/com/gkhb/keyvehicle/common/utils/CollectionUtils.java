package com.gkhb.keyvehicle.common.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 集合操作工具类
 * @author eddy
 *
 */
public class CollectionUtils {
	
	public static Map<String, List<?>> sortMapByKey(Map<String, List<?>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, List<?>> sortMap = new TreeMap<String, List<?>>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
	
}
