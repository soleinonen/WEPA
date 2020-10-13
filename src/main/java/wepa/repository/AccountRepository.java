package wepa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findBySurname(String surname);
    Account findByFirstname(String firstname);
    List<Account> findBySurnameIn(Collection<String> query);
}