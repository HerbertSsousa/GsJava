package org.example.api;

import org.example.CamadaBO.PagamentoBO;
import org.example.Classes.Pagamento;
import org.example.exception.DataAccessException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoController {

    private final PagamentoBO pagamentoBO;

    public PagamentoController() {
        this.pagamentoBO = new PagamentoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirPagamento(Pagamento pagamento) {
        try {
            pagamentoBO.inserirPagamento(pagamento);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir pagamento: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    public Response deletarPagamento(@PathParam("cpf") String cpf) {
        try {
            pagamentoBO.deletarPagamento(cpf);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar pagamento: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarPagamento(Pagamento pagamento) {
        try {
            pagamentoBO.alterarPagamento(pagamento);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar pagamento: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarPagamentoPorCpf(@PathParam("cpf") String cpf) {
        try {
            Pagamento pagamento = pagamentoBO.selecionarPagamentoPorCpf(cpf);
            if (pagamento != null) {
                return Response.status(Response.Status.OK).entity(pagamento).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Pagamento não encontrado").build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar pagamento por CPF: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarTodosPagamentos() {
        try {
            List<Pagamento> pagamentos = pagamentoBO.selecionarTodosPagamentos();
            return Response.status(Response.Status.OK).entity(pagamentos).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar todos os pagamentos: " + e.getMessage()).build();
        }
    }
}