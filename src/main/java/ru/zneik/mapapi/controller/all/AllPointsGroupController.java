package ru.zneik.mapapi.controller.all;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.service.base.PointsGroupService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AllPointsGroupController {
    private final PointsGroupService pointsGroupService;

    public AllPointsGroupController(PointsGroupService pointsGroupService) {
        this.pointsGroupService = pointsGroupService;
    }

    @GetMapping(value = "/maps/{mapUuid}/groups")
    public List<PointsGroup> getAllByMaps(@PathVariable UUID mapUuid) {
        return pointsGroupService.getAllByMap(mapUuid);
    }

    @GetMapping(value = "/groups/{uuid}/points")
    public PointsGroup getByUuidWithPoints(@PathVariable UUID uuid) {
        return pointsGroupService.getByUuidWithPoints(uuid);
    }
}
