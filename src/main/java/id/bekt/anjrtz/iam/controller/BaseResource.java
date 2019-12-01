package id.bekt.anjrtz.iam.controller;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

public class BaseResource {

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    String keycloakAuthServer;

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String keycloakClientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String keycloakClientSecret;


}
