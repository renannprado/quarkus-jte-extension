package dev.renann.quarkus.jte.test;

import io.quarkus.test.QuarkusDevModeTest;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class QuarkusJteDevModeTest {

    // Start hot reload (DevMode) test with your extension loaded
    @RegisterExtension
    static final QuarkusDevModeTest unitTest = new QuarkusDevModeTest()
        .setArchiveProducer(() -> {
            JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class);
            javaArchive.addClass("dev.renann.quarkus.jte.test.DevModeGreetingResource");
            return javaArchive;
        });

    private static Field deploymentSourcePath;

    @BeforeAll
    static void beforeAll() throws Exception {
        deploymentSourcePath = unitTest.getClass().getDeclaredField("deploymentSourcePath");
        deploymentSourcePath.setAccessible(true);
    }

    @Test
    public void testReloadFileDuringDevelopment() {
        final String toBeGreeted = "World";
        addOrModifyJteFile("devmode.jte", "@param String toBeGreeted\nHello, ${toBeGreeted}");

        RestAssured.when()
            .get("/greet?toBeGreeted=" + toBeGreeted)
            .then()
            .statusCode(200)
            .body(CoreMatchers.is("Hello, World"));

        addOrModifyJteFile("devmode.jte", "@param String toBeGreeted\nHello, ${toBeGreeted}!!!");

        RestAssured.when()
            .get("/greet?toBeGreeted=" + toBeGreeted)
            .then()
            .statusCode(200)
            .body(CoreMatchers.is("Hello, World!!!"));
    }

    public void addOrModifyJteFile(String file, String content) {
        try {
            copy(file, content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Path copy(String file, String content) throws Exception {
        Path jteSources = ((Path) deploymentSourcePath.get(unitTest)).getParent().resolve("jte");

        try {
            Path resolved = jteSources.resolve(file);
            Files.createDirectories(resolved.getParent());
            Files.write(resolved, content.getBytes(StandardCharsets.UTF_8));
            return resolved;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
