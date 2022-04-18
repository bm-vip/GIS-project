package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserModel extends BaseModel<Long> {
    @NotNull
    @NotBlank
    private String userName;
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    private Boolean active;
    private Set<RoleModel> roles;
}
