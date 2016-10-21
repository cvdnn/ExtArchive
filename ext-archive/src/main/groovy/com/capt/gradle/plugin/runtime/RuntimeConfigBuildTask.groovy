package com.capt.gradle.plugin.runtime

import com.capt.gradle.plugin.app.DNSMeta
import com.capt.gradle.plugin.app.MEMeta
import com.capt.gradle.plugin.app.URLMeta
import com.capt.util.TextUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class RuntimeConfigBuildTask extends DefaultTask {
    public static final String TASK_MIX_APP_CONFIG = 'mixAppConfig';

    private RuntimeMeta mAppMeta;

    @TaskAction
    void action() {
        mAppMeta = project.extensions.runtimeConfig;

        def text = mAppMeta."${URLMeta.META_URL}".toProperties() +
                mAppMeta."${MEMeta.META_ME}".toProperties() +
                mAppMeta."${DNSMeta.META_DNS}".toProperties() +
                mAppMeta.toProperties()

        def mix = TextUtils.toFake(text);

        write('debug', mix)
        write('release', mix)

        println ':: mixed App config'
        println text
        println '----------------------'
    }

    private void write(String dirName, String text) {
        File dir = new File(project.buildDir, "intermediates/assets/${dirName}")
        dir.mkdirs();

        new File(dir, 'app_config.ppm').write(text, 'UTF-8')
    }
}