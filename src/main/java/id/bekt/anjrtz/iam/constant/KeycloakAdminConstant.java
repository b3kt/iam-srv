package id.bekt.anjrtz.iam.constant;

public class KeycloakAdminConstant {

    public static final String BASE_URL = "http://localhost:8080/auth";
    public static final String ADMIN_URL = "/admin";
    public static final String ADMIN_REALM = "/realms/master";

    public static final String ADMIN_TOKEN_URL = ADMIN_REALM + "/protocol/openid-connect/token";
    public static final String ADMIN_USER_LIST = "/admin/realms/master/users" ;

    public static final String USERS = "/users";


    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_GRANT_TYPE = "grant_type";
    public static final Object FIELD_ACCESS_TOKEN = "access_token";


    private KeycloakAdminConstant(){}
}
