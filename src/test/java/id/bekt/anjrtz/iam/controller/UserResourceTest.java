package id.bekt.anjrtz.iam.controller;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.keycloak.common.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

class UserResourceTest {


    Logger logger = LoggerFactory.getLogger("default");

    String accessToken;

    @Test
    void getAccessToken() {
        try{

            Client client = ClientBuilder.newClient();
            Form formData = new Form()
                    .param("username", "admin")
                    .param("password","password")
                    .param("grant_type", "password");


            String auth = Base64.encodeBytes(("admin-cli:32e433da-1c64-4f89-a51b-6cd67e54ecf1").getBytes());
            Response val = client.target("http://localhost:8080/auth/realms/master/protocol/openid-connect/token")
                    .request()
                    .header("Authorization","Basic "+auth)
                    .post(Entity.form(formData));

            JSONObject json = val.readEntity(JSONObject.class);

            accessToken = (String) json.get("access_token");
            logger.info("ENTITY : {}", accessToken);

            Client client1 = ClientBuilder.newClient();
            Form formData1 = new Form();
            String val1 = client1.target("http://localhost:8080/auth/admin/realms/master/users")
                    .request()
                    .header("Authorization", "Bearer "+accessToken)
                    .get(String.class);

//            JSONObject json1 = val1.readEntity(JSONObject.class);
            logger.info("ENTITY : {}", val1);


        }catch(Exception e){
            e.printStackTrace();
        }

    }

}