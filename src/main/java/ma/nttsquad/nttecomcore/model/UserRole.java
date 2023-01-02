package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users_Roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

}
