package org.example.api;

import org.example.CamadaBO.Produto1BO;
import org.example.Classes.Produto1;
import org.example.exception.DataAccessException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produto1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Produto1Controller {

    private final Produto1BO produto1BO;

    public Produto1Controller() {
        this.produto1BO = new Produto1BO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirProduto(Produto1 produto) {
        try {
            produto1BO.inserirProduto(produto);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao inserir produto: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idProd}")
    public Response deletarProduto(@PathParam("idProd") String idProd) {
        try {
            produto1BO.deletarProduto(idProd);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar produto: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarProduto(Produto1 produto) {
        try {
            produto1BO.alterarProduto(produto);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar produto: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idProd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarProdutoPorId(@PathParam("idProd") String idProd) {
        try {
            Produto1 produto = produto1BO.selecionarProdutoPorId(idProd);
            if (produto != null) {
                return Response.status(Response.Status.OK).entity(produto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado").build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar produto por ID: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosProdutos() {
        try {
            List<Produto1> produtos = produto1BO.listarTodosProdutos();
            return Response.status(Response.Status.OK).entity(produtos).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os produtos: " + e.getMessage()).build();
        }
    }
}