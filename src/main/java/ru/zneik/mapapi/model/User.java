package ru.zneik.mapapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.zneik.mapapi.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(schema = "system_setting", name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "system_setting.uk_system_setting_users_name",
                columnNames = {"username"})
})
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "user.roles", attributeNodes = {
                @NamedAttributeNode(value = "roles")
        })
})
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    @Column(name = "username", nullable = false)
    @NotBlank
    @Size(min = 10, max = 50)
    private String username;
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;
    @Column(name = "registered")
    private LocalDateTime registered;
    @Column(name = "active", nullable = false)
    @NotNull
    private Boolean active;
    @ManyToMany
    @JoinTable(schema = "system_setting", name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_uuid", referencedColumnName = "uuid")},
            inverseJoinColumns = {@JoinColumn(name = "role_uuid", referencedColumnName = "uuid")})
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return active;
    }
}
