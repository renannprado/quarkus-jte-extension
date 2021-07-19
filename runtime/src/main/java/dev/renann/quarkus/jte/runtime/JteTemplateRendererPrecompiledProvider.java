package dev.renann.quarkus.jte.runtime;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class JteTemplateRendererPrecompiledProvider {

    @Produces
    @ApplicationScoped
    public TemplateRenderer templateRenderer(JteConfiguration configuration) {
        TemplateEngine templateEngine = TemplateEngine.createPrecompiled(null, ContentType.valueOf(configuration.contentType), null, configuration.packageName);
        return new JteTemplateRenderer(templateEngine);
    }
}
