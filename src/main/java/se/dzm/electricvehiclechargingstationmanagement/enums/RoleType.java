package se.dzm.electricvehiclechargingstationmanagement.enums;

public class RoleType {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String SUPER_WISER = "ROLE_SUPER_WISER";
    public static final String GUEST = "ROLE_GUEST";
    public static final String USER = "ROLE_USER";

    public static String name(String role){
        return role.replace("ROLE_","");
    }
}
