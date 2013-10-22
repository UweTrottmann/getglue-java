package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueResource;

import static org.fest.assertions.api.Assertions.assertThat;

public class ObjectServiceTest extends BaseTestCase {

    public void test_getObject() {
        ObjectService service = getManager().objectService();
        GetGlueResource resource = service.getObject("tv_shows/glee");
        assertThat(resource).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object.id).isEqualTo("tv_shows/glee");
        assertThat(resource.object.title).isEqualTo("Glee");
    }

}
