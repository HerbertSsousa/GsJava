package org.example.api;

import org.example.CamadaBO.HistoricoCompraBO;
import org.example.exception.DataAccessException;
import org.example.Classes.HistoricoCompra;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/historico-compra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoCompraController {
    private final HistoricoCompraBO historicoCompraBO;

    public HistoricoCompraController() {
        this.historicoCompraBO = new HistoricoCompraBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarHistoricoCompra(HistoricoCompra historicoCompra) {
        try {
            historicoCompraBO.inserirHistoricoCompra(historicoCompra);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar histórico de compra: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idHistorico}")
    public Response deletarHistoricoCompra(@PathParam("idHistorico") String idHistorico) {
        try {
            historicoCompraBO.deletarHistoricoCompra(idHistorico);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar histórico de compra: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarHistoricoCompra(HistoricoCompra historicoCompra) {
        try {
            historicoCompraBO.alterarHistoricoCompra(historicoCompra);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar histórico de compra: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosHistoricosCompra() {
        try {
            List<HistoricoCompra> historicoCompras = historicoCompraBO.selecionarTodosHistoricosCompra();
            return Response.status(Response.Status.OK).entity(historicoCompras).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os históricos de compra: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idHistorico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarHistoricoCompraPorId(@PathParam("idHistorico") String idHistorico) {
        try {
            HistoricoCompra historicoCompra = historicoCompraBO.selecionarHistoricoCompraPorId(idHistorico);
            return Response.status(Response.Status.OK).entity(historicoCompra).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar histórico de compra por ID: " + e.getMessage()).build();
        }
    }
}