/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

/**
 *
 * @author saraibanez
 */
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parsers {

    public static final ParseValue<Integer>INTEGER = Integer::parseInt;
    public static final ParseValue<Long> LONG = Long::parseLong;
    public static final ParseValue<Double> DOUBLE = Double::parseDouble;
    
    public static ParseValue<LocalDate> localDate(int month, int day){
    return (String year)-> {
    int y = Integer.parseInt(year);
    return LocalDate.of(y, month, day);
    };
    }
    
}
