package org.example.api;

import org.example.CamadaBO.LoginBO;
import org.example.exception.DataAccessException;
import org.example.Classes.Login;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    private final LoginBO loginBO;

    public LoginController() {
        this.loginBO = new LoginBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarLogin(Login login) {
        try {
            loginBO.inserirLogin(login);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar login: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{usuario}")
    public Response deletarLogin(@PathParam("usuario") String usuario) {
        try {
            loginBO.deletarLogin(usuario);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar login: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarSenha(@PathParam("usuario") String usuario, String novaSenha) {
        try {
            loginBO.alterarSenha(usuario, novaSenha);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar senha: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/validar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarLogin(Login login) {
        try {
            boolean valido = loginBO.validarLogin(login.getUsuario(), login.getSenha());
            if (valido) {
                return Response.status(Response.Status.OK).entity("Login válido").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Login inválido").build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao validar login: " + e.getMessage()).build();
        }
    }
}