package dev.renann.quarkus.jte.runtime;

import java.util.Map;

public interface TemplateRenderer {
    String render(String name, Map<String, Object> params);
}
