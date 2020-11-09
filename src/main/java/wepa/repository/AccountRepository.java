package wepa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wepa.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findByFirstname(String firstname);
    Account findByProfilePath(String profilePath);
    @Query(value = "SELECT DISTINCT a FROM Account a WHERE LOWER(a.firstname) LIKE LOWER(CONCAT('%',:query,'%')) OR LOWER(a.surname) LIKE LOWER(CONCAT('%',:query,'%')) OR LOWER(CONCAT(a.firstname,' ',a.surname)) LIKE LOWER(CONCAT('%',:query,'%'))")
    List<Account> findByQuery(@Param("query") String query, Pageable pageable);
}