package ru.zneik.mapapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zneik.mapapi.exception.NotFoundException;
import ru.zneik.mapapi.model.Map;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.repository.MapRepository;
import ru.zneik.mapapi.repository.PointsGroupRepository;
import ru.zneik.mapapi.service.base.MapService;
import ru.zneik.mapapi.util.ValidationUtil;

import java.util.List;
import java.util.UUID;

@Service
public class MapServiceImpl implements MapService {
    private final MapRepository mapRepository;
    private final PointsGroupRepository pointsGroupRepository;

    public MapServiceImpl(MapRepository mapRepository,
                          PointsGroupRepository pointsGroupRepository) {
        this.mapRepository = mapRepository;
        this.pointsGroupRepository = pointsGroupRepository;
    }

    @Override
    public List<Map> getAll() {
        return mapRepository.findAll();
    }

    @Override
    public Map getByUuid(UUID uuid) {
        return mapRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public Map create(Map map) {
        return mapRepository.save(map);
    }

    @Transactional
    @Override
    public Map update(Map map, UUID uuid) {
        getByUuid(uuid);
        return mapRepository.save(map);
    }

    @Override
    public void delete(UUID uuid) {
        mapRepository.deleteById(uuid);
    }

    @Transactional
    @Override
    public void addMapToGroup(UUID mapUuid, UUID groupUuid) {
        Map map = getByUuid(mapUuid);
        PointsGroup pointsGroup = ValidationUtil.checkExist(pointsGroupRepository.findById(groupUuid));
        map.getPointsGroups().add(pointsGroup);
        pointsGroup.getMaps().add(map);
        mapRepository.save(map);
    }

    @Transactional
    @Override
    public void deleteMapFromGroup(UUID mapUuid, UUID groupUuid) {
        Map map = getByUuid(mapUuid);
        PointsGroup pointsGroup = ValidationUtil.checkExist(pointsGroupRepository.findById(groupUuid));
        map.getPointsGroups().remove(pointsGroup);
        pointsGroup.getMaps().remove(map);
        mapRepository.save(map);
    }
}
