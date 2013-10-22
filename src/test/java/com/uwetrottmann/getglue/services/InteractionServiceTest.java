package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueInteractionResource;

import static org.fest.assertions.api.Assertions.assertThat;

public class InteractionServiceTest extends BaseTestCase {

    public void test_getInteraction() {
        InteractionService service = getManager().interactionService();
        GetGlueInteractionResource resource = service.getInteraction("getgluejava/2013-10-22T12:40:43Z");
        assertThat(resource).isNotNull();
        assertThat(resource.interaction).isNotNull();
        assertThat(resource.interaction._object).isNotNull();
        assertThat(resource.interaction._object.id).isEqualTo("tv_shows/how_i_met_your_mother");
        assertThat(resource.interaction._object.title).isEqualTo("How I Met Your Mother");
    }

}
