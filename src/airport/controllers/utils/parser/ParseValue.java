/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

/**
 *
 * @author saraibanez
 */
@FunctionalInterface
public interface ParseValue<T> {
    T parse(String value) throws Exception;
}