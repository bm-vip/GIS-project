package se.dzm.electricvehiclechargingstationmanagement.filter;

import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.Set;

@Setter
@ToString
public class UserFilter {
    private Long id;
    private String userName;
    private String title;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Set<Long> roles;

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getTitle() {
        if (title == null || title.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(title);
    }

    public Optional<String> getUserName() {
        if (userName == null || userName.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userName);
    }

    public Optional<String> getEmail() {
        if (email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(email);
    }

    public Optional<String> getFirstName() {
        if (firstName == null || firstName.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(firstName);
    }

    public Optional<String> getLastName() {
        if (lastName == null || lastName.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(lastName);
    }

    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }

    public Optional<Set<Long>> getRoles() {
        return Optional.ofNullable(roles);
    }
}
