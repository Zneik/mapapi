package ru.zneik.mapapi.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.zneik.mapapi.controller.base.BaseControllerTest;
import ru.zneik.mapapi.data.PointsGroupTestData;
import ru.zneik.mapapi.data.UserTestData;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.util.TestUtil;
import ru.zneik.mapapi.util.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminPointsGroupControllerTest extends BaseControllerTest {

    @Test
    void create() throws Exception {
        PointsGroup createPointsGroup = PointsGroupTestData.getNew();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(AdminPointsGroupController.URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(createPointsGroup)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        PointsGroup createdPointsGroup = TestUtil.readFromJsonMvcResult(mvcResult, PointsGroup.class);
        createPointsGroup.setUuid(createdPointsGroup.getUuid());
        PointsGroupTestData.MATCHER_WITH_IGNORING_FIELDS
                .assertMatch(createdPointsGroup, createPointsGroup);
    }

    @Test
    void update() throws Exception {
        PointsGroup updatePointsGroup = PointsGroupTestData.getUpdated();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(AdminPointsGroupController.URL +
                "/" + updatePointsGroup.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeAsString(updatePointsGroup)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        PointsGroup updatedPointsGroup = TestUtil.readFromJsonMvcResult(mvcResult, PointsGroup.class);
        PointsGroupTestData.MATCHER_WITH_IGNORING_FIELDS
                .assertMatch(updatedPointsGroup, updatePointsGroup);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(AdminPointsGroupController.URL +
                "/" + PointsGroupTestData.POINTS_GROUP_1.getUuid())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generateToken(UserTestData.USER_1_USERNAME)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}