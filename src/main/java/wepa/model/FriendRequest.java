package wepa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
