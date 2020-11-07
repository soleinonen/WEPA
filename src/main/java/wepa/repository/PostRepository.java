package wepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.Account;
import wepa.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

    @EntityGraph(value="Post.comments")
    List<Post> findTop25ByOwnerInOrderByTimestampDesc(List<Account> friends);

    @EntityGraph(value="Post.comments")
    List<Post> findAll();

}
