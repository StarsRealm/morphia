package dev.morphia.test.aggregation.expressions;

import dev.morphia.test.ServerVersion;
import dev.morphia.test.aggregation.AggregationTest;

import org.testng.annotations.Test;

import static dev.morphia.aggregation.expressions.Expressions.field;
import static dev.morphia.aggregation.expressions.Expressions.value;
import static dev.morphia.aggregation.expressions.MathExpressions.subtract;
import static dev.morphia.aggregation.expressions.StringExpressions.strLenBytes;
import static dev.morphia.aggregation.expressions.StringExpressions.substrBytes;
import static dev.morphia.aggregation.stages.Projection.project;

public class TestSubstrBytes extends AggregationTest {
    @Test
    public void testExample1() {
        testPipeline(ServerVersion.ANY, false, true, (aggregation) -> aggregation.pipeline(
                project()
                        .include("item")
                        .include("yearSubstring", substrBytes(field("quarter"), 0, 2))
                        .include("quarterSubtring", substrBytes(field("quarter"), value(2),
                                subtract(strLenBytes(field("quarter")), value(2))))));
    }

    @Test
    public void testExample2() {
        testPipeline(ServerVersion.ANY, false, true, (aggregation) -> aggregation.pipeline(
                project()
                        .include("name")
                        .include("menuCode", substrBytes(field("name"), 0, 3))));
    }

}
