package dev.renann.quarkus.jte.runtime;

import gg.jte.TemplateEngine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.nio.file.Paths;

@Singleton
public class JteTemplateRendererHotReloadProvider {

    @Produces
    @ApplicationScoped
    public TemplateRenderer templateRenderer(JteConfiguration configuration) {
        TemplateEngine templateEngine = JteTemplateEngineFactory.create(configuration, getClass().getClassLoader(), Paths.get("target", "jte-classes"));
        return new JteTemplateRenderer(templateEngine);
    }
}
