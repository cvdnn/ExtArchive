package com.capt.gradle.plugin.app

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class AppConfigCleanTask extends DefaultTask {
    public static final String TASK_CLEAN_APP_CONFIG = 'cleanAppConfig';

    private AppMeta mAppMeta;

    @TaskAction
    void action() {
        new File(project.buildDir, "intermediates/assets/debug/app_config.ppm").delete()
        new File(project.buildDir, "intermediates/assets/release/app_config.ppm").delete()

        println ':: cleaned App config'
        println()
    }
}