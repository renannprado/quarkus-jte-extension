package dev.renann.quarkus.jte.test;

import dev.renann.quarkus.jte.runtime.TemplateRenderer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/greet")
public class GreetingResource {

    @Inject
    TemplateRenderer templateRenderer;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String greet() {
        final HashMap<String, Object> data = new HashMap<>();
        data.put("foo", "bar");
        return templateRenderer.render("greet.jte", data);
    }
}
