/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

/**
 *
 * @author edangulo
 */
public class Response {
    
    private String message;
    private Object data;
    private int status;
    private Object object;
   

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    
    public Response(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
    public Object getData() {
        return data;
    }
    
}
