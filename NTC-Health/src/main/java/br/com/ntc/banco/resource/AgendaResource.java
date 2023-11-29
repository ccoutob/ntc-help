package br.com.ntc.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.ntc.banco.exception.BadInfoException;
import br.com.ntc.banco.exception.IdNotFoundException;
import br.com.ntc.banco.service.AgendaService;
import br.com.ntc.model.AgendaAcompanhamento;
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

@Path("/agenda")
public class AgendaResource {

	private AgendaService service;
	
	public AgendaResource() throws ClassNotFoundException, SQLException {
		service = new AgendaService();
	}
	
	@GET
	@Path("/pesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AgendaAcompanhamento> pesquisar(@QueryParam("nome") String pesquisa) throws SQLException{
		return service.pesquisarPorMedico(pesquisa);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AgendaAcompanhamento> lista() throws ClassNotFoundException, SQLException {
		return service.listarAgendas();
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
	@Path("/pesquisaPorMedico/{nomeMedico}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pesquisaPorMedico(@PathParam("nomeMedico") String nomeMedico) throws BadInfoException {
	    try {
	        List<AgendaAcompanhamento> agendas = service.pesquisarPorMedico(nomeMedico);
	        return Response.ok(agendas).build();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    }
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(AgendaAcompanhamento agenda, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			service.cadastrarAgenda(agenda);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(agenda.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(AgendaAcompanhamento agenda, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			agenda.setId(codigo);
			service.atualizarAgenda(agenda);
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
			service.removerAgenda(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}

