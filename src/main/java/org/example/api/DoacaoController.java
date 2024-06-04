package org.example.api;

import org.example.CamadaBO.DoacaoBO;
import org.example.exception.DataAccessException;
import org.example.Classes.Doacao;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/doacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoacaoController {
    private DoacaoBO doacaoBO;

    public DoacaoController() {
        this.doacaoBO = new DoacaoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarDoacao(Doacao doacao) {
        try {
            doacaoBO.inserirDoacao(doacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar doação: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idTrans}")
    public Response deletarDoacao(@PathParam("idTrans") String idTrans) {
        try {
            doacaoBO.deletarDoacao(idTrans);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar doação: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarDoacao(Doacao doacao) {
        try {
            doacaoBO.alterarDoacao(doacao);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar doação: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodasDoacoes() {
        try {
            List<Doacao> doacoes = doacaoBO.listarTodasDoacoes();
            return Response.status(Response.Status.OK).entity(doacoes).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todas as doações: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idTrans}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarDoacaoPorId(@PathParam("idTrans") String idTrans) {
        try {
            Doacao doacao = doacaoBO.selecionarDoacaoPorId(idTrans);
            return Response.status(Response.Status.OK).entity(doacao).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar doação por ID: " + e.getMessage()).build();
        }
    }
}