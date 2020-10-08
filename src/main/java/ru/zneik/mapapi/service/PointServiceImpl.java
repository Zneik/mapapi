package ru.zneik.mapapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zneik.mapapi.exception.NotFoundException;
import ru.zneik.mapapi.model.Point;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.repository.PointRepository;
import ru.zneik.mapapi.repository.PointsGroupRepository;
import ru.zneik.mapapi.service.base.PointService;
import ru.zneik.mapapi.util.ValidationUtil;

import java.util.List;
import java.util.UUID;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final PointsGroupRepository pointsGroupRepository;

    public PointServiceImpl(PointRepository pointRepository, PointsGroupRepository pointsGroupRepository) {
        this.pointRepository = pointRepository;
        this.pointsGroupRepository = pointsGroupRepository;
    }

    public Point getByUuid(UUID uuid) {
        return pointRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    @Override
    public List<Point> addToGroup(UUID groupUuid, List<Point> points) {
        PointsGroup pointsGroup = ValidationUtil.checkExist(pointsGroupRepository.getByUuid(groupUuid));
        points.forEach(point -> point.setPointsGroup(pointsGroup));
        return pointRepository.saveAll(points);
    }

    @Transactional
    @Override
    public Point update(UUID pointUuid, Point point) {
        getByUuid(pointUuid);
        return pointRepository.save(point);
    }

    @Override
    public void delete(UUID pointUuid) {
        pointRepository.deleteById(pointUuid);
    }
}
