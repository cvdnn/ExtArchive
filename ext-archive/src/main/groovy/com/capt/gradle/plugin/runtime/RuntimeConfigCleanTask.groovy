package com.capt.gradle.plugin.runtime

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class RuntimeConfigCleanTask extends DefaultTask {
    public static final String TASK_CLEAN_RUNTIME_CONFIG = 'cleanRuntimeConfig';

    @TaskAction
    void action() {
        new File(project.buildDir, "intermediates/assets/debug/runtime_config.ppm").delete()
        new File(project.buildDir, "intermediates/assets/release/runtime_config.ppm").delete()

        println ':: cleaned runtime config'
        println()
    }
}