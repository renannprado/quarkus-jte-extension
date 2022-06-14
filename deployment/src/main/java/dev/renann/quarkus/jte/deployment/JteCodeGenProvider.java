package dev.renann.quarkus.jte.deployment;

import dev.renann.quarkus.jte.runtime.JteTemplateRenderer;
import io.quarkus.deployment.CodeGenContext;
import io.quarkus.deployment.CodeGenProvider;
import io.quarkus.maven.dependency.ResolvedDependency;

import java.io.File;
import java.nio.file.Path;

public class JteCodeGenProvider implements CodeGenProvider {

    @Override
    public String providerId() {
        return "jte";
    }

    @Override
    public String inputExtension() {
        return "jte";
    }

    @Override
    public String inputDirectory() {
        return "jte";
    }

    @Override
    public boolean trigger(CodeGenContext context) {
        determineHotReloadProperties(context);
        return false;
    }

    private void determineHotReloadProperties(CodeGenContext context) {
        if (System.getProperty(JteTemplateRenderer.JTE_QUARKUS_CLASS_PATH) != null && System.getProperty(JteTemplateRenderer.JTE_QUARKUS_SOURCE_DIR) != null) {
            return;
        }

        StringBuilder classPath = new StringBuilder(512);
        for (ResolvedDependency dependency : context.applicationModel().getRuntimeDependencies()) {
            for (Path path : dependency.getResolvedPaths()) {
                appendPath(classPath, path);
            }
        }

        for (Path path : context.applicationModel().getAppArtifact().getResolvedPaths()) {
            appendPath(classPath, path);
        }

        System.setProperty(JteTemplateRenderer.JTE_QUARKUS_CLASS_PATH, classPath.toString());
        System.setProperty(JteTemplateRenderer.JTE_QUARKUS_SOURCE_DIR, context.inputDir().toAbsolutePath().toString());
    }

    private void appendPath(StringBuilder classPath, Path path) {
        if (classPath.length() > 0) {
            classPath.append(File.pathSeparatorChar);
        }
        classPath.append(path.toAbsolutePath());
    }
}
