package ru.zneik.mapapi.controller.all;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.MapTestData;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zneik.mapapi.data.MapTestData.MATCHER_WITH_IGNORE_FIELDS;

class AllMapControllerTest extends BaseControllerTest {

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AllMapController.URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_IGNORE_FIELDS.contentJson(MapTestData.getMapList()));
    }

    @Test
    void getByUuid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AllMapController.URL + "/" + MapTestData.MAP_1.getUuid()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_IGNORE_FIELDS.contentJson(MapTestData.MAP_1));
    }
}