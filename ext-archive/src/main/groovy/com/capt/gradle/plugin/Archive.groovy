package com.capt.gradle.plugin

import com.capt.gradle.plugin.app.*
import com.capt.gradle.plugin.git.GitPushBuildTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

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

        // git
        mProject.task(GitPushBuildTask.TASK_GIT_PUSH, type: GitPushBuildTask)
    }

    private void taskDependsOn(String taskName, String donTask) {
        Set<Task> taskSet = mProject.getTasksByName(taskName, true)
        taskSet?.each { t ->
            t?.dependsOn donTask
        }
    }
}
