package wepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wepa.model.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    
}
