package id.bekt.anjrtz.iam.controller;


import id.bekt.anjrtz.common.controller.PrivateResource;
import id.bekt.anjrtz.iam.constant.PathConstant;
import io.quarkus.security.Authenticated;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(PathConstant.USERS + "TEST")
@Authenticated
public class AdminResource extends PrivateResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String admin() {
        return "granted";
    }
}