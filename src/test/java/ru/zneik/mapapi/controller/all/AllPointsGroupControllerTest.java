package ru.zneik.mapapi.controller.all;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.MapTestData;
import ru.zneik.mapapi.data.PointsGroupTestData;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zneik.mapapi.data.PointsGroupTestData.*;

class AllPointsGroupControllerTest extends BaseControllerTest {

    @Test
    void getAllByMaps() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maps/" + MapTestData.MAP_1.getUuid() + "/groups"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_IGNORING_FIELDS.contentJson(List.of(POINTS_GROUP_2)));
    }

    @Test
    void getByUuidWithPoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/" +
                PointsGroupTestData.POINTS_GROUP_1.getUuid() +
                "/points"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_POINTS.contentJson(PointsGroupTestData.getPointsGroup1WithPoints()));
    }
}