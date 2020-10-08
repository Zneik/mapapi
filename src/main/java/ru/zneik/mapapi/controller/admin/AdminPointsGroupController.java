package ru.zneik.mapapi.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.service.base.PointsGroupService;

import javax.validation.Valid;
import java.util.UUID;

import static ru.zneik.mapapi.controller.admin.AdminPointsGroupController.URL;

@RestController
@RequestMapping(value = URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminPointsGroupController {
    public static final String URL = "/admin/groups";

    private final PointsGroupService pointsGroupService;

    public AdminPointsGroupController(PointsGroupService pointsGroupService) {
        this.pointsGroupService = pointsGroupService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PointsGroup create(@Valid @RequestBody PointsGroup pointsGroup) {
        return pointsGroupService.create(pointsGroup);
    }

    @PutMapping(value = "/{uuid}")
    public PointsGroup update(@Valid @RequestBody PointsGroup pointsGroup, @PathVariable UUID uuid) {
        return pointsGroupService.update(pointsGroup, uuid);
    }

    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        pointsGroupService.delete(uuid);
    }

}
