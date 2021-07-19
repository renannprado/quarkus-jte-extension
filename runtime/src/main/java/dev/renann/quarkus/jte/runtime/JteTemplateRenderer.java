package dev.renann.quarkus.jte.runtime;

import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;

import java.util.Map;

public class JteTemplateRenderer implements TemplateRenderer {

    public static final String JTE_QUARKUS_CLASS_PATH = "JTE_QUARKUS_CLASS_PATH";
    public static final String JTE_QUARKUS_SOURCE_DIR = "JTE_QUARKUS_SOURCE_DIR_HACK";

    private final TemplateEngine templateEngine;

    public JteTemplateRenderer(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String render(String name, Map<String, Object> params) {
        StringOutput output = new StringOutput();
        templateEngine.render(name, params, output);
        return output.toString();
    }
}
