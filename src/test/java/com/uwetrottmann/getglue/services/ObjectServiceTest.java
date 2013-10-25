package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueInteraction;
import com.uwetrottmann.getglue.entities.GetGlueObjectResource;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class ObjectServiceTest extends BaseTestCase {

    public void test_get() {
        ObjectService service = getManager().objectService();
        GetGlueObjectResource resource = service.get("tv_shows/glee");
        assertThat(resource).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object).isNotNull();
        assertThat(resource.object.id).isEqualTo("tv_shows/glee");
        assertThat(resource.object.title).isEqualTo("Glee");
    }

    public void test_checkin() {
        ObjectService service = getManager().objectService();
        GetGlueInteraction resource = service.checkin("tv_shows/glee");
        assertThat(resource).isNotNull();
        assertThat(resource.id).isNotEmpty();
    }

    public void test_checkinWithComment() {
        ObjectService service = getManager().objectService();
        GetGlueInteraction resource = service.checkin("tv_shows/how_i_met_your_mother",
                "Testing getglue-java.");
        assertThat(resource).isNotNull();
        assertThat(resource.id).isNotEmpty();
    }

    public void test_feed() {
        ObjectService service = getManager().objectService();
        List<GetGlueInteraction> interactions = service.feed("tv_shows/glee");
        assertThat(interactions).isNotEmpty();
        assertThat(interactions.get(0)).isNotNull();
        assertThat(interactions.get(0).id).isNotEmpty();
    }

    public void test_like() {
        ObjectService service = getManager().objectService();
        GetGlueInteraction resource = service.like("tv_shows/glee");
        assertThat(resource).isNotNull();
        assertThat(resource.id).isNotEmpty();
    }

}
