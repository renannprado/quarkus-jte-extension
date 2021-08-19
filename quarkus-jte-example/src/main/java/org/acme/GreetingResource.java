package org.acme;

import dev.renann.quarkus.jte.runtime.TemplateRenderer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/hello")
public class GreetingResource {

    @Inject
    private TemplateRenderer templateRenderer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return templateRenderer.render("example1.jte", Collections.singletonMap(
            "foo", "bar2212"
        ));
    }
}
