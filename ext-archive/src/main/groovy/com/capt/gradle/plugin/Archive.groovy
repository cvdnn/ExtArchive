package com.capt.gradle.plugin

import com.capt.gradle.plugin.app.*
import com.capt.gradle.plugin.git.GitMeta
import com.capt.gradle.plugin.git.GitPushBuildTask
import com.capt.gradle.plugin.runtime.AppRuntimeMeta
import com.capt.gradle.plugin.runtime.RuntimeConfigBuildTask
import com.capt.gradle.plugin.runtime.RuntimeConfigCleanTask
import com.capt.gradle.plugin.runtime.RuntimeMeta
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
        createMixAppConfigTask()
        createMixRuntimeConfigTask()

        // DependsOn
        taskDependsOn('clean', AppConfigCleanTask.TASK_CLEAN_APP_CONFIG, RuntimeConfigCleanTask.TASK_CLEAN_RUNTIME_CONFIG)
        taskDependsOn('preBuild', AppConfigBuildTask.TASK_MIX_APP_CONFIG, RuntimeConfigBuildTask.TASK_MIX_RUNTIME_CONFIG)
    }

    /**
     * mix app config
     */
    private void createMixAppConfigTask() {
        AppMeta appMeta = mProject.extensions.create(AppMeta.APP_CONFIG, AppMeta)

        appMeta.extensions.create(URLMeta.META_URL, URLMeta)
        appMeta.extensions.create(MEMeta.META_ME, MEMeta)
        appMeta.extensions.create(DNSMeta.META_DNS, DNSMeta)

        mProject.task(AppConfigCleanTask.TASK_CLEAN_APP_CONFIG, type: AppConfigCleanTask)
        mProject.task(AppConfigBuildTask.TASK_MIX_APP_CONFIG, type: AppConfigBuildTask)
    }

    /**
     * mix runtime config
     */
    private void createMixRuntimeConfigTask() {
        RuntimeMeta runtimeMeta = mProject.extensions.create(RuntimeMeta.RUNTIME_CONFIG, RuntimeMeta)

        runtimeMeta.extensions.create(AppRuntimeMeta.META_APP_RUNTIME, AppRuntimeMeta)
        runtimeMeta.extensions.create(ALiYunMeta.META_ALIYUN, ALiYunMeta)

        mProject.task(RuntimeConfigCleanTask.TASK_CLEAN_RUNTIME_CONFIG, type: RuntimeConfigCleanTask)
        mProject.task(RuntimeConfigBuildTask.TASK_MIX_RUNTIME_CONFIG, type: RuntimeConfigBuildTask)
    }

    private void gitConfig() {
        // git
        GitMeta gitMeta = mProject.extensions.create(GitMeta.GIT_CONFIG, GitMeta)

        mProject.task(GitPushBuildTask.TASK_GIT_PUSH, type: GitPushBuildTask)
    }

    private void taskDependsOn(String taskName, String... dependTasks) {
        Set<Task> taskSet = mProject.getTasksByName(taskName, true)
        taskSet?.each { t ->
            t?.dependsOn dependTasks
        }
    }
}
