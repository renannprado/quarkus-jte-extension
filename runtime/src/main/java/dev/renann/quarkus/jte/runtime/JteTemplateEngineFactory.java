package dev.renann.quarkus.jte.runtime;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class JteTemplateEngineFactory {
    public static TemplateEngine create(JteConfiguration configuration, ClassLoader classLoader, Path targetDirectory) {
        String classPath = System.getProperty(JteTemplateRenderer.JTE_QUARKUS_CLASS_PATH);
        String sourceDir = System.getProperty(JteTemplateRenderer.JTE_QUARKUS_SOURCE_DIR);

        if (classPath == null) {
            throw new IllegalStateException(JteTemplateRenderer.JTE_QUARKUS_CLASS_PATH + " not found, template engine cannot be created");
        }

        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Paths.get(sourceDir));

        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, targetDirectory, ContentType.valueOf(configuration.contentType), classLoader, configuration.packageName);
        templateEngine.setClassPath(Arrays.asList(classPath.split(File.pathSeparator)));

        return templateEngine;
    }
}
