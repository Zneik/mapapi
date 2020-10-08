package ru.zneik.mapapi.data;

import org.springframework.beans.BeanUtils;
import ru.zneik.mapapi.model.Map;
import ru.zneik.mapapi.util.TestMatcher;

import java.util.List;
import java.util.UUID;

public class MapTestData {
    public static final TestMatcher<Map> MATCHER_WITH_IGNORE_FIELDS = TestMatcher
            .usingFieldsWithIgnoringAssertions(Map.class, "pointsGroups");

    public static final Map MAP_1 = new Map() {{
        setUuid(UUID.fromString("0b37c9df-7bbe-483b-a851-a8f5e83fbb0f"));
        setName("Карта точек стоянки");
    }};

    public static final Map MAP_2 = new Map() {{
        setUuid(UUID.fromString("cf35844f-a49f-4075-8ea5-a093ca01945a"));
        setName("Карта интересных мест");
    }};

    public static final Map MAP_3 = new Map() {{
        setUuid(UUID.fromString("d9c872a3-85f2-47a8-a1ea-410a6b2d11b1"));
        setName("Просто обновленная карта");
    }};

    public static List<Map> getMapList() {
        return List.of(MAP_1, MAP_2, MAP_3);
    }

    public static Map getMap1WithPointsGroups() {
        MAP_1.setPointsGroups(List.of(PointsGroupTestData.POINTS_GROUP_1, PointsGroupTestData.POINTS_GROUP_2));
        return MAP_1;
    }

    public static Map getMap2WithPointsGroups() {
        MAP_2.setPointsGroups(List.of(PointsGroupTestData.POINTS_GROUP_2));
        return MAP_2;
    }

    public static Map getNew() {
        Map map = new Map();
        map.setName("New map name");
        return map;
    }

    public static Map getUpdated() {
        Map map = new Map();
        BeanUtils.copyProperties(MAP_1, map);
        map.setName("Updated map name");
        return map;
    }
}
