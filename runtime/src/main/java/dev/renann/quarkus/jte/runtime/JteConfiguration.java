package dev.renann.quarkus.jte.runtime;

import gg.jte.ContentType;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class JteConfiguration {

    /**
     * The {@link ContentType} of jte templates
     */
    @ConfigItem(defaultValue = "Html")
    public String contentType;

    /**
     * The package templates are generated to
     */
    @ConfigItem(defaultValue = "gg.jte.quarkus.generated")
    public String packageName;


}
