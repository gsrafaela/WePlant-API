package br.com.api.weplant.entities;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_user")
@Check(constraints = "user_status IN ('A', 'I')")
@SequenceGenerator(name = "userSq", sequenceName = "SQ_TB_WP_USER", allocationSize = 1)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSQ")
    private Long id;

    @Column(name = "complete_name", length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "user_email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_status", length = 1, nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "phone_id", nullable = false)
    private Phone phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Garden> gardenList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public void addGarden(Garden garden) {
        gardenList.add(garden);
    }

    public void addPost(Post post) {
        this.postList.add(post);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.birthday = userRegisterDTO.birthday();
        this.username = userRegisterDTO.username();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
        this.status = "A";
        setAddress(userRegisterDTO.address());
        setPhone(userRegisterDTO.phone());
    }

    public User(UserNoProtectedDataDTO userNoProtectedDataDTO) {
        this.name = userNoProtectedDataDTO.name();
        this.birthday = userNoProtectedDataDTO.birthday();
        this.username = userNoProtectedDataDTO.username();
        this.email = userNoProtectedDataDTO.email();
        this.address = userNoProtectedDataDTO.address();
        this.phone = userNoProtectedDataDTO.phone();
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
