package ru.zneik.mapapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.zneik.mapapi.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "map", name = "points")
@Getter
@Setter
public class Point extends BaseEntity {
    @Column(name = "text")
    private String text;
    @Column(name = "x", nullable = false)
    @Size(max = 255)
    private String x;
    @Column(name = "y", nullable = false)
    @Size(max = 255)
    private String y;
    @ManyToOne
    @JoinColumn(name = "points_group_uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private PointsGroup pointsGroup;

}
