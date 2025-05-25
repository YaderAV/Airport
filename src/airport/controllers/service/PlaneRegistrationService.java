/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Plane;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.validators.PlaneValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.validators.ValidationResult;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author saraibanez
 */
public class PlaneRegistrationService {
    private final Map<String,Plane> planes;
    private final JSONStorage storage;

    public PlaneRegistrationService(Map<String,Plane> planes, JSONStorage storage) {
        this.planes = planes;
        this.storage = storage;
    }

    public Response register(Plane plane) throws IOException {
        PlaneValidator planeValidator = new PlaneValidator();
        ValidationResult planeValidation = planeValidator.validate(plane);

        if (!planeValidation.isValid()) {
            return new Response(planeValidation.getCombinedMessage(), Status.BAD_REQUEST);
        }


        planes.put(plane.getId(), plane);
        storage.getPlaneLoader().savePlanes(planes);
        return new Response("Avión registrado correctamente.", Status.CREATED, plane);
    }
}
