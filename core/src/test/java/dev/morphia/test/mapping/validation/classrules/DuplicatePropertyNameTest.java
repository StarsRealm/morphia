package dev.morphia.test.mapping.validation.classrules;

import java.util.Map;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.mapping.validation.ConstraintViolationException;
import dev.morphia.test.TestBase;

import org.bson.types.ObjectId;
import org.testng.annotations.Test;

public class DuplicatePropertyNameTest extends TestBase {
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDuplicatedPropertyNameDifferentType() {
        getMapper().map(DuplicatedPropertyName2.class);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDuplicatedPropertyNameSameType() {
        getMapper().map(DuplicatedPropertyName.class);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNameShadowMapping() {
        getMapper().map(MappingToExistingFieldName.class);
    }

    @Entity
    private static class DuplicatedPropertyName {
        @Id
        private String id;

        @Property(value = "value")
        private String content1;
        @Property(value = "value")
        private String content2;
    }

    @Entity
    private static class DuplicatedPropertyName2 {
        @Id
        private String id;

        @Property(value = "value")
        private Map<String, Integer> content1;
        @Property(value = "value")
        private String content2;
    }

    @Entity(useDiscriminator = false)
    private static class MappingToExistingFieldName {
        @Id
        ObjectId id;

        @Property("id")
        String customId;
        String whatever;

        public MappingToExistingFieldName(String customId, String whatever) {
            this.customId = customId;
            this.whatever = whatever;
        }
    }
}
