package android.gradle.plugin

public class Utils {

    public static File getPPMFile(def project, String fileName) {
        File dir = new File(project.projectDir, "src/main/assets/")
        dir.mkdirs();

        File ppmFile = new File(dir, fileName);

        return ppmFile
    }
}