package wepa.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment extends AbstractPersistable<Long>{

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Post post;

    @Lob
    @Type(type="org.hibernate.type.TextType")
    private String commentText;

    private LocalDateTime timestamp;
}
