package com.capt.gradle.plugin

import com.capt.gradle.plugin.app.AppConfigBuildTask
import com.capt.gradle.plugin.app.AppConfigCleanTask
import com.capt.gradle.plugin.app.AppMeta
import com.capt.gradle.plugin.app.DNSMeta
import com.capt.gradle.plugin.app.MEMeta
import com.capt.gradle.plugin.app.URLMeta
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.internal.impldep.org.apache.commons.collections.MapUtils

public class Archive implements Plugin<Project> {
    private Project mProject;

    @Override
    void apply(Project project) {
        mProject = project;

        mixAppConfig()
    }

    private void mixAppConfig() {
        AppMeta appMeta = mProject.extensions.create(AppMeta.APP_CONFIG, AppMeta)

        appMeta.extensions.create(URLMeta.META_URL, URLMeta)
        appMeta.extensions.create(MEMeta.META_ME, MEMeta)
        appMeta.extensions.create(DNSMeta.META_DNS, DNSMeta)

        mProject.task(AppConfigCleanTask.TASK_CLEAN_APP_CONFIG, type: AppConfigCleanTask)
        mProject.task(AppConfigBuildTask.TASK_MIX_APP_CONFIG, type: AppConfigBuildTask)

        taskDependsOn('clean', AppConfigCleanTask.TASK_CLEAN_APP_CONFIG)
        taskDependsOn('preBuild', AppConfigBuildTask.TASK_MIX_APP_CONFIG)

        def extend = [app: 'capt']
        println '>>>>>>>: ' + MapUtils.toProperties(extend).toString()
    }

    private void taskDependsOn(String taskName, String donTask) {
        Set<Task> taskSet = mProject.getTasksByName(taskName, true)
        taskSet?.each { t ->
            t?.dependsOn donTask
        }
    }
}
