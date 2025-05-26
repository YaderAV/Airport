/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author yader
 */
public class ValidatorDates {

    public static boolean isValidBirthDate(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthDate, today);
        return !birthDate.isAfter(today) && years <= 150;
    }

    public static boolean isFlightInFuture(LocalDateTime departureDate) {
        LocalDateTime now = LocalDateTime.now();
        return !departureDate.isBefore(now);
    }

    public static boolean isFlightDateWithinOneYear(LocalDateTime departureDate) {
        LocalDateTime now = LocalDateTime.now();
        return !departureDate.isAfter(now.plusYears(1));
    }

    public static boolean isArrivalAfterDeparture(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        return !arrivalDate.isBefore(departureDate);
    }

    public static boolean isFlightDurationValid(int hoursArrival, int minutesArrival, int hoursScale, int minutesScale) {
        int totalMinutes = (hoursArrival + hoursScale) * 60 + minutesArrival + minutesScale;
        return totalMinutes <= 20 * 60; // 20 hours
    }

    // Validación combinada general para vuelo
    public static boolean validateFlightConstraints(LocalDateTime departureDate, LocalDateTime arrivalDate, 
                                                    int hoursArrival, int minutesArrival, 
                                                    int hoursScale, int minutesScale) {
        return isFlightInFuture(departureDate)
            && isFlightDateWithinOneYear(departureDate)
            && isArrivalAfterDeparture(departureDate, arrivalDate)
            && isFlightDurationValid(hoursArrival, minutesArrival, hoursScale, minutesScale);
    }

    // Extra: prevenir fechas absurdas por error humano (tipo año 9999 o 1800)
    public static boolean isReasonableDate(LocalDateTime date) {
        int year = date.getYear();
        return year >= 1900 && year <= 2100;
    }


}
