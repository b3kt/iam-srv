package id.bekt.anjrtz.iam.service;

import id.bekt.anjrtz.iam.constant.KeycloakAdminConstant;
import id.bekt.anjrtz.iam.vo.UserVO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path(KeycloakAdminConstant.ADMIN_URL)
@RegisterRestClient
public interface IKeycloakClientService {

    @GET
    @Path(KeycloakAdminConstant.ADMIN_URL + KeycloakAdminConstant.ADMIN_REALM + KeycloakAdminConstant.USERS)
    @Produces(MediaType.APPLICATION_JSON)
    List<UserVO> getAllUsers();

    Optional<UserVO> getUserByUsername(@PathParam String username);
}
