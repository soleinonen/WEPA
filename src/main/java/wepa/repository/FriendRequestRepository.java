package wepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.Account;
import wepa.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReviewer(Account reviewer);
}
