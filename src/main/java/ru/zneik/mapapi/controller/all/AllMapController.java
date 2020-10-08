package ru.zneik.mapapi.controller.all;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zneik.mapapi.model.Map;
import ru.zneik.mapapi.service.base.MapService;
import ru.zneik.mapapi.util.json.Views;

import java.util.List;
import java.util.UUID;

import static ru.zneik.mapapi.controller.all.AllMapController.URL;

@RestController
@RequestMapping(value = URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AllMapController {
    public static final String URL = "/maps";

    private final MapService mapService;

    public AllMapController(MapService mapService) {
        this.mapService = mapService;
    }

    @JsonView(Views.Rest.class)
    @GetMapping
    public List<Map> getAll() {
        return mapService.getAll();
    }

    @JsonView(Views.Rest.class)
    @GetMapping(value = "/{uuid}")
    public Map getByUuid(@PathVariable("uuid") UUID uuid) {
        return mapService.getByUuid(uuid);
    }

}
