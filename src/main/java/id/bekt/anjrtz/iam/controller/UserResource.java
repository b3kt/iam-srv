package id.bekt.anjrtz.iam.controller;

import id.bekt.anjrtz.iam.constant.PathConstant;
import id.bekt.anjrtz.iam.service.IKeycloakClientService;
import id.bekt.anjrtz.iam.vo.UserVO;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(PathConstant.USER)
public class UserResource extends BaseResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    IKeycloakClientService keycloakClientService;


    @GET
    @Path(PathConstant.USER_ME)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public UserVO me() {
        return new UserVO(identity);
    }

    @GET
    @Path(PathConstant.USER_USERNAME)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public UserVO getUserByUsername(@PathParam String username) {
        return new UserVO(identity);
    }

    @GET
    @Path(PathConstant.USERS)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public List<UserVO> getAllUsers() {
        return keycloakClientService.getAllUsers();
    }




}
