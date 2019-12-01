package id.bekt.anjrtz.iam.service;

import io.restassured.http.Header;
import org.jose4j.base64url.Base64;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

class KeycloakClientServiceTest {

    Logger logger = LoggerFactory.getLogger("default");

    @Test
    void getAccessToken() {
        logger.info("Begin");


        given()
                .when()
                .header(new Header("Authorization", "Basic " + Base64.encode(("admin-cli:32e433da-1c64-4f89-a51b-6cd67e54ecf1").getBytes())))
                .header(new Header("Content-Type", "application/x-www-form-urlencoded"))
                .param("username", "admin")
                .param( "password", "password")
                .param("grant_type", "password")
                .post("http://localhost:8080/auth/realms/master/protocol/openid-connect/token")

                .then()
                .statusCode(200);

        logger.info("Finish");
    }
}