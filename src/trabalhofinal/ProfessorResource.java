/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

/**
 *
 * @author x036716
 */
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResource {
    private List<Professor> professores;
    private long proximoId;
    public ProfessorResource() {
        proximoId = 1;
        professores = new ArrayList<>();
        professores.add(new Professor(proximoId++, "Marcos", 11111));
        professores.add(new Professor(proximoId++, "Pedro", 22222));
        professores.add(new Professor(proximoId++, "Joana", 12345));
    }

    @GET
    public List<Professor> read() {
        return professores;
    }
}    

