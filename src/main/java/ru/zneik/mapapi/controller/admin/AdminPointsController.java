package ru.zneik.mapapi.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zneik.mapapi.model.Point;
import ru.zneik.mapapi.service.base.PointService;
import ru.zneik.mapapi.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static ru.zneik.mapapi.controller.admin.AdminPointsController.URL;

@RestController
@RequestMapping(value = URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminPointsController {
    public static final String URL = "/admin";

    private final PointService pointService;

    public AdminPointsController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping(value = "/groups/{groupUuid}/points")
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<Point> addToGroup(@PathVariable UUID groupUuid, @Valid @RequestBody List<Point> points) {
        ValidationUtil.checkNew(points);
        return pointService.addToGroup(groupUuid, points);
    }

    @PutMapping(value = "/points/{pointUuid}")
    public Point update(@PathVariable UUID pointUuid, @Valid @RequestBody Point point) {
        ValidationUtil.assureUuidConsistent(point, pointUuid);
        return pointService.update(pointUuid, point);
    }

    @DeleteMapping(value = "/points/{pointUuid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID pointUuid) {
        pointService.delete(pointUuid);
    }

}
