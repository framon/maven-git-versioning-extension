package me.qoomon.maven.gitversioning;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.InstantiationStrategy;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static me.qoomon.maven.gitversioning.VersioningMojo.GIT_VERSIONING_POM_NAME;

/**
 * Deletes git versioned pom file
 * <p>
 * !!! DO NOT ADD THIS PLUGIN MANUALLY TO POM !!!
 * <p>
 * utilized by {@link ModelProcessor}
 */

@Mojo(name = CleanMojo.GOAL,
        defaultPhase = LifecyclePhase.CLEAN,
        threadSafe = true)
public class CleanMojo extends AbstractMojo {

    static final String GOAL = "clean";

    @Override
    public synchronized void execute() throws MojoFailureException {
        File gitVersionedPomFile = new File(GIT_VERSIONING_POM_NAME);
        if (gitVersionedPomFile.isFile()) {
            getLog().info("Deleting " + gitVersionedPomFile.getPath());
            boolean deleted = gitVersionedPomFile.delete();
            if (!deleted) {
                throw new MojoFailureException("Could not delete " + gitVersionedPomFile.getAbsolutePath());
            }
        }
    }
}
