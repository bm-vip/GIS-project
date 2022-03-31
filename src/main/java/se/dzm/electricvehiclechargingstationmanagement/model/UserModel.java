package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserModel extends BaseModel<Long> {
    @NotNull(message = "userName is mandatory")
    @NotBlank(message = "userName is mandatory")
    private String userName;
    @Email(message = "email is not valid")
    private String email;
    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotNull(message = "firstName is mandatory")
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotNull(message = "lastName is mandatory")
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    private Boolean active;
    private Set<RoleModel> roles;
}
