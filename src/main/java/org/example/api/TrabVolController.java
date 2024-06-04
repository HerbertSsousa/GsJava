package org.example.api;

import org.example.CamadaBO.TrabVolBO;
import org.example.Classes.TrabVol;
import org.example.exception.DataAccessException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/trabalho_voluntario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrabVolController {

    private final TrabVolBO trabVolBO;

    public TrabVolController() {
        this.trabVolBO = new TrabVolBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirTrabVol(TrabVol trabVol) {
        try {
            trabVolBO.inserirTrabVol(trabVol);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir trabalho voluntário: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idEmpregado}")
    public Response deletarTrabVol(@PathParam("idEmpregado") String idEmpregado) {
        try {
            trabVolBO.deletarTrabVol(idEmpregado);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar trabalho voluntário: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarTrabVol(TrabVol trabVol) {
        try {
            trabVolBO.alterarTrabVol(trabVol);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar trabalho voluntário: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idEmpregado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarTrabVolPorId(@PathParam("idEmpregado") String idEmpregado) {
        try {
            TrabVol trabVol = trabVolBO.selecionarTrabVolPorId(idEmpregado);
            if (trabVol != null) {
                return Response.status(Response.Status.OK).entity(trabVol).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Trabalho voluntário não encontrado").build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar trabalho voluntário por ID: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosTrabVols() {
        try {
            List<TrabVol> trabVols = trabVolBO.listarTodosTrabVols();
            return Response.status(Response.Status.OK).entity(trabVols).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os trabalhos voluntários: " + e.getMessage()).build();
        }
    }
}