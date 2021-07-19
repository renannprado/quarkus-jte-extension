package dev.renann.quarkus.jte.test;

import dev.renann.quarkus.jte.runtime.TemplateRenderer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/greet")
public class DevModeGreetingResource {

    @Inject
    TemplateRenderer templateRenderer;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String greet(@QueryParam("toBeGreeted") String toBeGreeted) {
        return templateRenderer.render("devmode.jte", Collections.singletonMap(
            "toBeGreeted", toBeGreeted
        ));
    }
}
