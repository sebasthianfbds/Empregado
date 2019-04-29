/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.resource;

/**
 *
 * @author x036716
 */
import trabalhofinal.model.Professor;
import trabalhofinal.dao.ProfessorDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResource {
    private ProfessorDAO dao;
    
    public ProfessorResource(ProfessorDAO dao) {
        this.dao = dao;
    }
    
    private long proximoId;

    @GET
    public List<Professor> read() {
        return dao.read();
    }
    
    @GET
    @Path("{id}")
    public Professor readOne(@PathParam("id") int id) {
        return dao.readOne(id);
    }
    
    @POST
    public Professor create(Professor p) {
        return this.dao.create(p);
    }
    
    @PUT
    @Path("{id}")
    public Professor update(@PathParam("id") int id, Professor p) {
        return this.dao.update(id, p);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        if(!this.dao.delete(id)){ 
            throw new WebApplicationException("Professor com id=" + id 
                                              + " não encontrado!", 404); 
        }
        return Response.ok().build();
    }
}    

