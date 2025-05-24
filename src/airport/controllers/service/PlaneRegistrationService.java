/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Plane;
import airport.Models.Storage.Storage;
import airport.controllers.utils.PlaneValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class PlaneRegistrationService {
    private final ArrayList<Plane> planes;
    private final Storage storage;

    public PlaneRegistrationService(ArrayList<Plane> planes, Storage storage) {
        this.planes = planes;
        this.storage = storage;
    }

    public Response register(Plane plane) {
        PlaneValidator planeValidator = new PlaneValidator();
        ValidationResult planeValidation = planeValidator.validate(plane);

        if (!planeValidation.isValid()) {
            return new Response(planeValidation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        if (planes.stream().anyMatch(pl -> pl.getId().equals(plane.getId()))) {
            return new Response("El ID del avión ya está registrado.", Status.BAD_REQUEST);
        }

        planes.add(plane);
        storage.savePlanes(planes);
        return new Response("Avión registrado correctamente.", Status.CREATED, plane);
    }
}
