package ru.zneik.mapapi.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zneik.mapapi.model.Map;
import ru.zneik.mapapi.service.base.MapService;
import ru.zneik.mapapi.util.ValidationUtil;

import javax.validation.Valid;
import java.util.UUID;

import static ru.zneik.mapapi.controller.admin.AdminMapController.URL;

@RestController
@RequestMapping(value = URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminMapController {
    public static final String URL = "/admin/maps";

    private final MapService mapService;

    public AdminMapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map create(@Valid @RequestBody Map map) {
        ValidationUtil.checkNew(map);
        return mapService.create(map);
    }

    @PutMapping("/{uuid}")
    public Map update(@Valid @RequestBody Map map, @PathVariable UUID uuid) {
        ValidationUtil.assureUuidConsistent(map, uuid);
        return mapService.update(map, uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        mapService.delete(uuid);
    }

    @PostMapping(value = "/{mapUuid}/groups/{groupUuid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void addGroupToMap(@PathVariable UUID mapUuid, @PathVariable UUID groupUuid) {
        mapService.addMapToGroup(mapUuid, groupUuid);
    }

    @DeleteMapping(value = "/{mapUuid}/groups/{groupUuid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteGroupFromMap(@PathVariable UUID mapUuid, @PathVariable UUID groupUuid) {
        mapService.deleteMapFromGroup(mapUuid, groupUuid);
    }

}
