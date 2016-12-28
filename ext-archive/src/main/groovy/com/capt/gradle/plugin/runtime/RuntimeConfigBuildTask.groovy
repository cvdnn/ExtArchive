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

        write('debug', mix)
        write('release', mix)

        println ':: mixed App config'
        println text
        println '----------------------'
    }

    private void write(String dirName, String text) {
        File dir = new File(project.buildDir, "generated/assets/shaders/${dirName}")
        dir.mkdirs();

        new File(dir, 'runtime_config.ppm').write(text, 'UTF-8')

        File gdir = new File(project.buildDir, "generated")
    }
}