package wepa.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    @NotNull
    String firstname;

    @NotEmpty
    @NotNull
    String surname;

    @NotEmpty
    @NotNull
    String username;

    @NotEmpty
    @NotNull
    String password;
}