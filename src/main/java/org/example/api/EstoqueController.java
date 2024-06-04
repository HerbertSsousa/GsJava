package org.example.api;

import org.example.CamadaBO.EstoqueBO;
import org.example.exception.DataAccessException;
import org.example.Classes.Estoque;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/estoque")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueController {
    private EstoqueBO estoqueBO;

    public EstoqueController() {
        this.estoqueBO = new EstoqueBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarEstoque(Estoque estoque) {
        try {
            estoqueBO.inserirEstoque(estoque);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar estoque: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idProd}")
    public Response deletarEstoque(@PathParam("idProd") String idProd) {
        try {
            estoqueBO.deletarEstoque(idProd);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar estoque: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarEstoque(Estoque estoque) {
        try {
            estoqueBO.alterarEstoque(estoque);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar estoque: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosEstoques() {
        try {
            List<Estoque> estoques = estoqueBO.listarTodosEstoques();
            return Response.status(Response.Status.OK).entity(estoques).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os estoques: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idProd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarEstoquePorId(@PathParam("idProd") String idProd) {
        try {
            Estoque estoque = estoqueBO.selecionarEstoquePorId(idProd);
            return Response.status(Response.Status.OK).entity(estoque).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar estoque por ID: " + e.getMessage()).build();
        }
    }
}