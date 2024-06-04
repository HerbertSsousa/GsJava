package org.example.api;

import org.example.CamadaBO.CadastroBO;
import org.example.Classes.Cadastro;
import org.example.Classes.Endereco;
import org.example.exception.DataAccessException;
import org.example.integration.ViaCEPService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroController {
    private CadastroBO cadastroBO;

    public CadastroController() {
        this.cadastroBO = new CadastroBO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarCadastro(Cadastro cadastro) {
        try {
            cadastroBO.inserirCadastro(cadastro);
            return Response.status(Response.Status.CREATED).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar cadastro: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    public Response deletarCadastro(@PathParam("cpf") String cpf) {
        try {
            cadastroBO.deletarCadastro(cpf);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar cadastro: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarCadastro(Cadastro cadastro) {
        try {
            cadastroBO.alterarCadastro(cadastro);
            return Response.status(Response.Status.OK).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar cadastro: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosCadastros() {
        try {
            List<Cadastro> cadastros = cadastroBO.listarTodosCadastros();
            return Response.status(Response.Status.OK).entity(cadastros).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os cadastros: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarCadastroPorCpf(@PathParam("cpf") String cpf) {
        try {
            Cadastro cadastro = cadastroBO.selecionarCadastroPorCpf(cpf);
            return Response.status(Response.Status.OK).entity(cadastro).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao selecionar cadastro por CPF: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/cep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEnderecoPorCep(@PathParam("cep") String cep) {
        try {
            Endereco endereco = ViaCEPService.buscarEnderecoPorCEP(cep);
            return Response.status(Response.Status.OK).entity(endereco).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar endereço: " + e.getMessage()).build();
        }
    }
}
