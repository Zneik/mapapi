package ru.zneik.mapapi.data;

import org.springframework.beans.BeanUtils;
import ru.zneik.mapapi.model.PointsGroup;
import ru.zneik.mapapi.util.TestMatcher;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsGroupTestData {
    public static final TestMatcher<PointsGroup> MATCHER_WITH_IGNORING_FIELDS =
            TestMatcher.usingFieldsWithIgnoringAssertions(PointsGroup.class, "map");
    public static TestMatcher<PointsGroup> MATCHER_WITH_POINTS =
            TestMatcher.usingAssertions(PointsGroup.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("map", "points.pointsGroup").ignoringAllOverriddenEquals().isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final PointsGroup POINTS_GROUP_1 = new PointsGroup() {{
        setUuid(UUID.fromString("811fcacc-5403-434b-83a4-9162861bb90c"));
        setName("Просто группа");
    }};

    public static final PointsGroup POINTS_GROUP_2 = new PointsGroup() {{
        setUuid(UUID.fromString("ad1f4342-d60b-4b66-aafe-ef2e2716cc85"));
        setName("Непросто группа");
    }};

    public static PointsGroup getPointsGroup1WithPoints() {
        POINTS_GROUP_1.setPoints(List.of(PointsTestData.POINT_1, PointsTestData.POINT_2));
        return POINTS_GROUP_1;
    }

    public static PointsGroup getNew() {
        PointsGroup pointsGroup = new PointsGroup();
        pointsGroup.setName("New points group");
        return pointsGroup;
    }

    public static PointsGroup getUpdated() {
        PointsGroup pointsGroup = new PointsGroup();
        BeanUtils.copyProperties(POINTS_GROUP_2, pointsGroup);
        pointsGroup.setName("Updated points group");
        return  pointsGroup;
    }

}
