package wepa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wepa.model.Account;
import wepa.model.FriendRequest;
import wepa.repository.FriendRequestRepository;

@Service
public class FriendRequestService {

    @Autowired
    FriendRequestRepository friendRequestRepository;

    public void createNewRequest(FriendRequest fr) {
        friendRequestRepository.save(fr);
    }
    
    public List<FriendRequest> getRequestsToBeReviewed(Account account) {
        return friendRequestRepository.findByReviewer(account);
    }

    public void acceptRequest(Long id) {
        FriendRequest fr = friendRequestRepository.getOne(id);
        fr.getInitiator().addFriend(fr.getReviewer());
        friendRequestRepository.deleteById(id);
    }
}
