package ro.kuberam.maven.plugins.expath.mojos;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import ro.kuberam.maven.plugins.mojos.KuberamAbstractMojo;
import ro.kuberam.maven.plugins.mojos.NameValuePair;

/**
 * Generates the basic files needed for the library implementing the EXPath
 * module. <br>
 *
 * @author <a href="mailto:claudius.teodorescu@gmail.com">Claudius
 * Teodorescu</a>
 */

@Mojo(name = "generate-lib-basics")
public class GenerateLibBasicsMojo extends KuberamAbstractMojo {

    /**
     * Specification file.
     *
     * @parameter
     * @since 0.3
     */
    @Parameter(required = true)
    private File specFile;

    /**
     * Library's dir.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(required = true)
    private File libDir;

    /**
     * Library's java package name.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(required = true)
    private String javaPackageName;

    /**
     * Library's version.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(required = true)
    private String libVersion;

    /**
     * Library's URL.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(defaultValue = "${project.url}")
    private String libUrl;

    /**
     * Library's artifactId for generating the pom file needed for library.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(required = true)
    private String libArtifactId;

    /**
     * Library's name.
     *
     * @parameter
     * @since 0.2
     */
    @Parameter(required = true)
    private String libName;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        FileUtils.mkdir(libDir.getAbsolutePath());

        final NameValuePair[] parameters = new NameValuePair[]{
                new NameValuePair("javaPackageName", javaPackageName),
                new NameValuePair("libDirPath", libDir.getAbsolutePath()),
                new NameValuePair("libUrl", libUrl), new NameValuePair("libArtifactId", libArtifactId),
                new NameValuePair("libName", libName), new NameValuePair("libVersion", libVersion)};

        xsltTransform(specFile,
                this.getClass().getResource("/ro/kuberam/maven/plugins/expath/generate-lib-basics.xsl")
                        .toString(), libDir.getAbsolutePath(), parameters);
    }

}
