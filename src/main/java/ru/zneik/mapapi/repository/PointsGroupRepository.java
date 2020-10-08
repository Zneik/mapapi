package ru.zneik.mapapi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.zneik.mapapi.model.PointsGroup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointsGroupRepository extends JpaRepository<PointsGroup, UUID> {

    List<PointsGroup> getAllByMapsUuid(UUID mapUuid);

    @EntityGraph(value = "pointGroup.points")
    Optional<PointsGroup> getByUuid(UUID uuid);

}
