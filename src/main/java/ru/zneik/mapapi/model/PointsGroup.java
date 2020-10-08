package ru.zneik.mapapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.zneik.mapapi.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(schema = "map",
        name = "points_groups",
        uniqueConstraints = {@UniqueConstraint(name = "map.uk_map_points_group_name",
                columnNames = {"name"})})
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "pointGroup.points",
                attributeNodes = {@NamedAttributeNode(value = "points")})
})
@Getter
@Setter
public class PointsGroup extends BaseEntity {
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;
    @OneToMany(mappedBy = "pointsGroup")
    @JsonManagedReference
    private List<Point> points;
    @ManyToMany
    @JoinTable(schema = "map",
            name = "maps_points_groups",
            joinColumns = @JoinColumn(name = "points_group_uuid",
                    referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "map_uuid",
                    referencedColumnName = "uuid"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Map> maps;

}
