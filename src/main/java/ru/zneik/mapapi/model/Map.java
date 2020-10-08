package ru.zneik.mapapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.zneik.mapapi.model.base.BaseEntity;
import ru.zneik.mapapi.util.json.Views;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(schema = "map",
        name = "maps",
        uniqueConstraints = {@UniqueConstraint(name = "map.uk_map_map_name",
                columnNames = {"name"})}
)
@Getter
@Setter
public class Map extends BaseEntity {
    @Column(name = "name", nullable = false)
    @JsonView(Views.Rest.class)
    @Size(max = 255)
    @NotBlank
    private String name;
    @ManyToMany(mappedBy = "maps")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PointsGroup> pointsGroups;
}
