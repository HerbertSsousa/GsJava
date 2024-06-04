package org.example.api;

import org.example.CamadaBO.CarrinhoBO;
import org.example.exception.DataAccessException;
import org.example.Classes.Carrinho;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoController {
    private CarrinhoBO carrinhoBO;

    public CarrinhoController() {
        this.carrinhoBO = new CarrinhoBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarCarrinho(Carrinho carrinho) {
        try {
            carrinhoBO.inserirCarrinho(carrinho);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar carrinho: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idCarrinho}")
    public Response deletarCarrinho(@PathParam("idCarrinho") String idCarrinho) {
        try {
            carrinhoBO.deletarCarrinho(idCarrinho);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar carrinho: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarCarrinho(Carrinho carrinho) {
        try {
            carrinhoBO.alterarCarrinho(carrinho);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar carrinho: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosCarrinhos() {
        try {
            List<Carrinho> carrinhos = carrinhoBO.listarTodosCarrinhos();
            return Response.status(Response.Status.OK).entity(carrinhos).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os carrinhos: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idCarrinho}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarCarrinhoPorId(@PathParam("idCarrinho") String idCarrinho) {
        try {
            Carrinho carrinho = carrinhoBO.selecionarCarrinhoPorId(idCarrinho);
            return Response.status(Response.Status.OK).entity(carrinho).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar carrinho por ID: " + e.getMessage()).build();
        }
    }
}