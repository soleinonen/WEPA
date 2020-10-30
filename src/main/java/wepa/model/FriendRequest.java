package wepa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedEntityGraph(name = "FriendRequest.initiator",
    attributeNodes = {@NamedAttributeNode("initiator"), @NamedAttributeNode("reviewer")})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest extends AbstractPersistable<Long>{

    @ManyToOne
    private Account initiator;

    @ManyToOne
    private Account reviewer;
    
}
