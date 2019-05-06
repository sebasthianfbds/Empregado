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
import trabalhofinal.model.Empregado;
import trabalhofinal.dao.EmpregadoDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/empregados")
@Produces(MediaType.APPLICATION_JSON)
public class EmpregadoResource {
    private EmpregadoDAO dao;
    
    public EmpregadoResource(EmpregadoDAO dao) {
        this.dao = dao;
    }
    
    private long proximoId;

    @GET
    public List<Empregado> read() {
        return dao.read();
    }
    
    @GET
    @Path("{id}")
    public Empregado readOne(@PathParam("id") int id) {
        return dao.readOne(id);
    }
    
    @POST
    public Empregado create(Empregado e) {
        return this.dao.create(e);
    }
    
    @PUT
    @Path("{id}")
    public Empregado update(@PathParam("id") int id, Empregado e) {
        return this.dao.update(id, e);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        if(!this.dao.delete(id)){ 
            throw new WebApplicationException("Empregado com id=" + id 
                                              + " não encontrado!", 404); 
        }
        return Response.ok().build();
    }
}    

