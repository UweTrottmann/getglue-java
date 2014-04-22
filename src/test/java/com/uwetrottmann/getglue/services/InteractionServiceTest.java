package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueInteraction;
import com.uwetrottmann.getglue.entities.GetGlueInteractionResource;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class InteractionServiceTest extends BaseTestCase {


    public static final String SAMPLE_INTERACTION = "getgluejava/2014-04-22T17:08:08Z";

    public void test_get() {
        InteractionService service = getManager().interactionService();
        GetGlueInteractionResource resource = service.get(SAMPLE_INTERACTION);
        assertThat(resource).isNotNull();
        assertThat(resource.interaction).isNotNull();
        assertThat(resource.interaction._object).isNotNull();
        assertThat(resource.interaction._object.id).isEqualTo("tv_shows/how_i_met_your_mother");
        assertThat(resource.interaction._object.title).isEqualTo("How I Met Your Mother");
        assertThat(resource.interaction.comment).isEqualTo("Testing getglue-java.");
    }

    public void test_votes() {
        InteractionService service = getManager().interactionService();
        List<GetGlueInteraction> resource = service.votes(SAMPLE_INTERACTION);
        assertThat(resource).isNotNull();
    }

    public void test_replies() {
        InteractionService service = getManager().interactionService();
        List<GetGlueInteraction> resource = service.replies(SAMPLE_INTERACTION);
        assertThat(resource).isNotNull();
    }

}
