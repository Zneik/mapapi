package ru.zneik.mapapi.service.base;

import ru.zneik.mapapi.model.Point;

import java.util.List;
import java.util.UUID;

public interface PointService {

    List<Point> addToGroup(UUID groupUuid, List<Point> points);

    Point update(UUID pointUuid, Point point);

    void delete(UUID pointUuid);
}
