package gov.rw.brd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Nick on 2/5/2020.
 */
@Entity
@Data
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @NotBlank(message = "user full names must not be blank")
    private String names;
    @NotBlank(message = "username must not be black")
    private String username;
    @NotBlank(message = "email must not be blank")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "password must be blank")
    @Size(min = 6, message = "password must be atleast 6 characters")
    private String password;
    @Transient
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private String role;
    private Date created_At;
    private Date updated_At;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Loanee loanee;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
        this.status=EStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

    public User() {
    }
//    implementing method from UserDetail Interface

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority>list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_"+role));

        return list;
}

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public Long getUser_id() {
        return user_id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "["+user_id +","+names +","+ username +","+ email +","+status +","+created_At +","+ role+"]";
    }
}

