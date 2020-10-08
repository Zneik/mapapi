package ru.zneik.mapapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zneik.mapapi.model.Point;

import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, UUID> {
}
