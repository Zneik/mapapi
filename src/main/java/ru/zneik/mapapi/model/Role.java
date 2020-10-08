package ru.zneik.mapapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import ru.zneik.mapapi.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(schema = "system_setting", name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(name = "system_setting.roles.uk_system_setting_roles_name",
                        columnNames = "name")})
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}
