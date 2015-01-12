package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserResource {

    private final CreateUser createUser;
    private final GetUser getUser;

    @Inject
    public UserResource(CreateUser createUser, GetUser getUser) {
        this.createUser = createUser;
        this.getUser = getUser;
    }

    @GET
    @Path("{userId}")
    public String getByUserId(@PathParam("userId") String userId){
        Optional<String> user = getUser.by(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
        
    }
    
    @POST
    public String create(User user){
        try {
            return createUser.with(user.getName(), user.getRole()).id();
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
        
    }
}
