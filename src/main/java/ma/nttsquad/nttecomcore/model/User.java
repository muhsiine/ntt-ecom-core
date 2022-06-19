package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String createdAt;
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude private List<Address> addresses = new ArrayList<>();

}
