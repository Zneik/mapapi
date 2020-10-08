package ru.zneik.mapapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zneik.mapapi.exception.NotFoundException;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.repository.PointsGroupRepository;
import ru.zneik.mapapi.service.base.PointsGroupService;

import java.util.List;
import java.util.UUID;

@Service
public class PointsGroupServiceImpl implements PointsGroupService {
    private final PointsGroupRepository pointsGroupRepository;

    public PointsGroupServiceImpl(PointsGroupRepository pointsGroupRepository) {
        this.pointsGroupRepository = pointsGroupRepository;
    }

    @Override
    public List<PointsGroup> getAllByMap(UUID mapUuid) {
        return pointsGroupRepository.getAllByMapsUuid(mapUuid);
    }

    @Override
    public PointsGroup getByUuidWithPoints(UUID uuid) {
        return pointsGroupRepository.getByUuid(uuid)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public PointsGroup create(PointsGroup pointsGroup) {
        return pointsGroupRepository.save(pointsGroup);
    }

    @Transactional
    @Override
    public PointsGroup update(PointsGroup pointsGroup, UUID uuid) {
        return pointsGroupRepository.save(pointsGroup);
    }

    @Override
    public void delete(UUID uuid) {
        pointsGroupRepository.deleteById(uuid);
    }
}
