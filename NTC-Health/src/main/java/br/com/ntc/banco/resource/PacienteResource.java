package br.com.ntc.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.ntc.banco.exception.BadInfoException;
import br.com.ntc.banco.exception.IdNotFoundException;
import br.com.ntc.banco.service.PacienteService;
import br.com.ntc.model.Paciente;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/paciente")
public class PacienteResource {

    private PacienteService service;

    public PacienteResource() throws ClassNotFoundException, SQLException {
        service = new PacienteService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> lista() throws ClassNotFoundException, SQLException {
        return service.listarPacientes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response busca(@PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
        try {
            return Response.ok(service.pesquisarPorId(codigo)).build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/pesquisaPorNome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisaPorNome(@QueryParam("nome") String nome) {
        try {
            List<Paciente> pacientes = service.pesquisarPorNome(nome);
            return Response.ok(pacientes).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Paciente paciente, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
        try {
            service.cadastrarPaciente(paciente);
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(paciente.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (BadInfoException e) {
            e.printStackTrace();
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Paciente paciente, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
        try {
            paciente.setId(codigo);
            service.atualizarPaciente(paciente);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            service.removerPaciente(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
