package org.example.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class BaseController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listarProdutos() {
        return "Raiz da API";
    }

}
