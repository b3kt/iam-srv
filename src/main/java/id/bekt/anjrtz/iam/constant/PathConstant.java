package id.bekt.anjrtz.iam.constant;

public class PathConstant {
    /**
     * Public resources
     */
    public static final String LOGIN = "/iam/api/login";
    public static final String REGISTER = "/iam/api/register";
    public static final String RECOVERY = "/iam/api/recovery";

    /**
     * Private resources
     */
    public static final String USER = "/iam/api/user";
    public static final String USER_ME = "/me";
    public static final String USER_USERNAME = "/{username}";

    public static final String USERS = "/iam/api/users";
}
