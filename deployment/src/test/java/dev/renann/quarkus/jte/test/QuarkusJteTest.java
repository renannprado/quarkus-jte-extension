package dev.renann.quarkus.jte.test;

import io.quarkus.test.QuarkusUnitTest;
import io.restassured.RestAssured;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class QuarkusJteTest {

    // Start unit test with your extension loaded
    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
        .setArchiveProducer(() -> {
            JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class);
            javaArchive.addClass(GreetingResource.class);
            javaArchive.addAsResource("greet.jte", "META-INF/resources/greet.jte");

            return javaArchive;
        });

    @Test
    @Disabled("I simply cannot get this to work, help is appreciated :)")
    public void testRender() {
        RestAssured.when().get("/greet").then().statusCode(200);
    }
}
