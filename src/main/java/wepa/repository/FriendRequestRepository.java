package wepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.Account;
import wepa.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    @EntityGraph(value = "FriendRequest.initiator")
    List<FriendRequest> findByReviewer(Account reviewer);

    @EntityGraph(value = "FriendRequest.initiator")
    FriendRequest findByInitiatorAndReviewer(Account initiator, Account reviewer);

    @EntityGraph(value = "FriendRequest.initiator")
    FriendRequest getOne(Long id);

}
