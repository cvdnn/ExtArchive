package com.capt.gradle.plugin.app

import com.capt.util.TextUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class AppConfigBuildTask extends DefaultTask {
    public static final String TASK_MIX_APP_CONFIG = 'mixAppConfig'

    private AppMeta mAppMeta

    @TaskAction
    void action() {
        mAppMeta = project.extensions."${AppMeta.APP_CONFIG}"

        def text = mAppMeta."${URLMeta.META_URL}".toProperties() +
                mAppMeta."${MEMeta.META_ME}".toProperties() +
                mAppMeta."${DNSMeta.META_DNS}".toProperties() +
                mAppMeta.toProperties()

        def mix = TextUtils.toFake(text);

        boolean writeDebug = write('debug', mix)
        boolean writeRelease = write('release', mix)

        println ':: mixed App config'
        println text
        println '----------------------'
        println "::result: debug: ${writeDebug}; release: ${writeRelease}"
    }

    private boolean write(String dirName, String text) {
        File dir = new File(project.buildDir, "generated/assets/shaders/${dirName}")
        dir.mkdirs();

        File file = new File(dir, 'app_config.ppm')
        file.write(text, 'UTF-8')

        return file.exists()
    }
}