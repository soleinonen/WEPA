package wepa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedEntityGraph(name = "Post.comments",
    attributeNodes = {@NamedAttributeNode("comments")})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post extends AbstractPersistable<Long>{

    @ManyToOne
    private Account owner;

    @NotEmpty
    @NotNull
    @Lob
    @Type(type="org.hibernate.type.TextType")
    private String postText;

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostLike> likes = new ArrayList<>();

    private Integer likeCount;
    
}
