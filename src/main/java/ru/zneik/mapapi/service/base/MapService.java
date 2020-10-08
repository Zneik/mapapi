package ru.zneik.mapapi.service.base;

import ru.zneik.mapapi.model.Map;

import java.util.List;
import java.util.UUID;

public interface MapService {
    List<Map> getAll();

    Map getByUuid(UUID uuid);

    Map create(Map map);

    Map update(Map map, UUID uuid);

    void delete(UUID uuid);

    void addMapToGroup(UUID mapUuid, UUID groupUuid);

    void deleteMapFromGroup(UUID mapUuid, UUID groupUuid);

}
