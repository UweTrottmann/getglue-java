package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.BaseTestCase;
import com.uwetrottmann.getglue.entities.GetGlueObjects;

import static org.fest.assertions.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    public void test_getObject() {
        SearchService service = getManager().searchService();
        GetGlueObjects resource = service.search("How I Met Your Mother");
        assertThat(resource).isNotNull();
        assertThat(resource.objects).isNotNull();
        assertThat(resource.objects.get(0)).isNotNull();
        assertThat(resource.objects.get(0).id).isEqualTo("tv_shows/how_i_met_your_mother");
        assertThat(resource.objects.get(0).title).isEqualTo("How I Met Your Mother");
    }

}
