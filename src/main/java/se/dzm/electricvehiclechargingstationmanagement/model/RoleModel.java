package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleModel extends BaseModel<Long> {
	@NotNull(message = "role is mandatory")
	@NotBlank(message = "role is mandatory")
	private String role;
	private String title;
}
