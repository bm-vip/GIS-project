package se.dzm.electricvehiclechargingstationmanagement.entity;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_user")
@Audited
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_user",sequenceName="seq_user",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	private Long id;

	@Column(name = "user_name", unique = true)
	@NotNull
	private String userName;

	@Column(unique = true)
	@Email
	private String email;

	@NotNull
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "active_flag", nullable = false)
	private boolean active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	@Override
	public String getSelectTitle() {
		return firstName.concat(" ").concat(lastName);
	}
}
