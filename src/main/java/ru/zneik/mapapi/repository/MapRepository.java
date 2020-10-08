package ru.zneik.mapapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zneik.mapapi.model.Map;

import java.util.UUID;

public interface MapRepository extends JpaRepository<Map, UUID> {
}
