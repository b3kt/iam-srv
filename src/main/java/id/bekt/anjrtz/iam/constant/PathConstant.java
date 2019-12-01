package id.bekt.anjrtz.iam.constant;

public class PathConstant {
    /**
     * Public resources
     */

    public static final String PUBLIC = "/iam/api/public";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String RECOVERY = "/recovery";

    /**
     * Private resources
     */
    public static final String USER = "/iam/api/user";
    public static final String USER_ME = "/me";
    public static final String USER_USERNAME = "/{username}";
    public static final String USERS = "/all";
}
