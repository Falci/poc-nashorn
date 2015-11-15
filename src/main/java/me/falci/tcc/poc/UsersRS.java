package me.falci.tcc.poc;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import me.falci.tcc.poc.exception.InvalidFormDataException;
import me.falci.tcc.poc.exception.InvalidJsValidatorException;
import me.falci.tcc.poc.forms.UserForm;
import me.falci.tcc.poc.services.UserService;

@Path("/users")
public class UsersRS {
	
	private final UserService service = new UserService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(UserForm data) {
    	
    	try {
    		service.save(data);
    		
    		return Response
    				.ok(Arrays.asList()) // 200
    				.build(); 
    		
    	} catch (InvalidFormDataException e) {
    		
    		return Response
    				.status(Status.BAD_REQUEST) // 400
    				.entity(e.getErrors())
    				.build();
    		
    	} catch (InvalidJsValidatorException e) {
			
    		return Response
    				.serverError() // 500
    				.entity(Arrays.asList("Server error"))
    				.build(); 
		}
    	
    }
}
