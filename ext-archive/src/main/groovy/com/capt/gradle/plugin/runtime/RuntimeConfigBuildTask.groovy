package com.capt.gradle.plugin.runtime

import com.capt.util.TextUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class RuntimeConfigBuildTask extends DefaultTask {
    public static final String TASK_MIX_RUNTIME_CONFIG = 'mixRuntimeConfig';

    private RuntimeMeta mRuntimeMeta;

    @TaskAction
    void action() {
        mRuntimeMeta = project.extensions."${RuntimeMeta.RUNTIME_CONFIG}";

        def text = mRuntimeMeta.toProperties() +
                mRuntimeMeta."${AppRuntimeMeta.META_APP_RUNTIME}".toProperties() +
                mRuntimeMeta."${ALiYunMeta.META_ALIYUN}".toProperties()

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

        File file = new File(dir, 'runtime_config.ppm')
        file.write(text, 'UTF-8')

        return file.exists()
    }
}