package ma.nttsquad.nttecomcore.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserS",uniqueConstraints = {@UniqueConstraint(columnNames = { "username", "email"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Username is required")
    @Length(max = 30)
    private String username;

    @NotNull(message = "firstName is required")
    @Length(max = 20)
    private String firstName;

    @NotNull(message = "lastName is required")
    @Length(max = 20)
    private String lastName;

    @NotNull(message = "email is required")
    @Length(max = 50)
    private String email;

    @NotNull(message = "password is required")
    @Length(max = 255)
    private String password;

    @NotNull(message = "phoneNumber is required")
    @Length(max = 20)
    private String phoneNumber;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude private List<Address> addresses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

}
