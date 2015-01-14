package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.actions.UpdateUser;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.entities.User;
import org.olid16.infrastructure.rest.adapters.UserAdapter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
@Api("/users")
public class UserResource {

    private final CreateUser createUser;
    private final GetUser getUser;
    private final UserAdapter userAdapter;
    private final UpdateUser updateUser;

    @Inject
    public UserResource(CreateUser createUser,
                        GetUser getUser, 
                        UserAdapter userAdapter, 
                        UpdateUser updateUser) {
        this.createUser = createUser;
        this.getUser = getUser;
        this.userAdapter = userAdapter;
        this.updateUser = updateUser;
    }

    @GET
    @Path("/{userId}")
    @ApiOperation("Get unique user by id")
    public Response getByUserId(@PathParam("userId") String userId){
        Optional<org.olid16.domain.entities.User> user = getUser.by(userId);
        if (user.isPresent()) {
            return Response.ok(userAdapter.fromDomain(user.get())).build();
        }
        throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
        
    }
    
    @POST
    @ApiOperation("Create user")
    public String create(User user){
        try {
            return createUser.with(user.getName(), user.getRole()).id();
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
        
    }

    @PUT
    @ApiOperation("Update name from user")
    public void update(User user) {
        try {
            updateUser.with(user.getId(), user.getName());
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }
}
