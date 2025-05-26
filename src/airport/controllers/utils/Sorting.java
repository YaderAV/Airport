/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author yader
 */
public class Sorting {
    public static <T> List<T> sortList(List<T> list, Comparator<T> comparator){
        List<T> sorted = new ArrayList<>(list);
        sorted.sort(comparator);
        return sorted;
    }
    
    public static <K, V extends Comparable<V>> Map<K,V> sortMapByVaule(Map<K,V> map){
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e1,
                LinkedHashMap::new
                ));
    }
}
