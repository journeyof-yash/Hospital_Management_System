package Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "iam_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled = true;

    /* === Correct Many-to-Many with Role === */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "iam_user_roles",
            joinColumns        = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = Set.of();   // empty default

    /* ----------------------------------------------------------- */
    /*  Constructors                                               */
    /* ----------------------------------------------------------- */
    public User() {}

    public User(String username, String email, String password, Set<Role> roles) {
        this.username = username;
        this.email    = email;
        this.password = password;
        this.roles    = roles;
    }

    /* ----------------------------------------------------------- */
    /*  Getters / Setters                                          */
    /* ----------------------------------------------------------- */
    public Long getId()             { return id; }
    public void setId(Long id)      { this.id = id; }

    public String getUsername()        { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail()        { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword()     { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isEnabled()      { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public Set<Role> getRoles()     { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}