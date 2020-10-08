package ru.zneik.mapapi.service.base;

import ru.zneik.mapapi.model.PointsGroup;

import java.util.List;
import java.util.UUID;

public interface PointsGroupService {
    List<PointsGroup> getAllByMap(UUID mapUuid);

    PointsGroup getByUuidWithPoints(UUID uuid);

    PointsGroup create(PointsGroup pointsGroup);

    PointsGroup update(PointsGroup pointsGroup, UUID uuid);

    void delete(UUID uuid);
}
