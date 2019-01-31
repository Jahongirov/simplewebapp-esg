package uz.pdp.esg.collection.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.esg.collection.User;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class UserDateAudit extends DateAudit {

//    @CreatedBy
    private Long createdBy;

//    @LastModifiedBy
    private Long updatedBy;


}
