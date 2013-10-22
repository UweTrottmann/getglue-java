package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueObjectResource;

import static org.fest.assertions.api.Assertions.assertThat;

public class ObjectServiceTest extends BaseTestCase {

    public void test_getObject() {
        ObjectService service = getManager().objectService();
        GetGlueObjectResource resource = service.getObject("tv_shows/glee");
        assertThat(resource).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object.id).isEqualTo("tv_shows/glee");
        assertThat(resource.object.title).isEqualTo("Glee");
    }

    public void test_checkinObject() {
        ObjectService service = getManager().objectService();
        GetGlueObjectResource resource = service.checkinObject("tv_shows/glee");
        assertThat(resource).isNotNull();
    }

    public void test_checkinObjectComment() {
        ObjectService service = getManager().objectService();
        GetGlueObjectResource resource = service.checkinObject("tv_shows/how_i_met_your_mother", "Testing getglue-java.");
        assertThat(resource).isNotNull();
    }

}
