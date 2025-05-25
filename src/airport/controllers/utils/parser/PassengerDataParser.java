/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

/**
 *
 * @author saraibanez
 */


import airport.controllers.utils.parser.Parsers;
import airport.Models.Entities.Passenger;
import java.time.LocalDate;

public class PassengerDataParser {

    public static Passenger parse(
        String idText,
        String name,
        String lastname,
        String yearText,
        String monthText,
        String dayText,
        String phoneCodeText,
        String phoneText,
        String country
    ) throws Exception {
        long id = Parsers.LONG.parse(idText);
        int year = Parsers.INTEGER.parse(yearText);
        int month = Parsers.INTEGER.parse(monthText);
        int day = Parsers.INTEGER.parse(dayText);
        int phoneCode = Parsers.INTEGER.parse(phoneCodeText);
        long phone = Parsers.LONG.parse(phoneText);
        LocalDate birthDate = Parsers.localDate(month, day).parse(yearText);

        return new Passenger(id, name, lastname, birthDate, phoneCode, phone, country);
    }
}
