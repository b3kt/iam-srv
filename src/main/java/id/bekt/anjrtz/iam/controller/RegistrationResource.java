package id.bekt.anjrtz.iam.controller;


import id.bekt.anjrtz.common.controller.BaseResource;
import id.bekt.anjrtz.iam.constant.PathConstant;
import id.bekt.anjrtz.iam.vo.UserVO;
import io.quarkus.security.User;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(PathConstant.PUBLIC)
public class RegistrationResource extends BaseResource {

    @POST
    @Path(PathConstant.REGISTER)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public UserVO register(SecurityIdentity identity) {
        return new UserVO(identity);
    }

    @POST
    @Path(PathConstant.LOGIN)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public UserVO login(SecurityIdentity identity) {
        return new UserVO(identity);
    }


    @POST
    @Path(PathConstant.RECOVERY)
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    public UserVO recovery(SecurityIdentity identity) {
        return new UserVO(identity);
    }



}
