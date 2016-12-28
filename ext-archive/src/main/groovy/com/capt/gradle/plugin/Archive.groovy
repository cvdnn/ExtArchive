package com.capt.gradle.plugin

import com.capt.gradle.plugin.app.*
import com.capt.gradle.plugin.git.GitMeta
import com.capt.gradle.plugin.git.GitPushBuildTask
import com.capt.gradle.plugin.runtime.*
import com.capt.gradle.plugin.setting.AppSettingPullTask
import org.gradle.api.Plugin
import org.gradle.api.Project

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

        createAppSettingTask()
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

    /**
     * pull app setting
     */
    private void createAppSettingTask() {
        mProject.task(AppSettingPullTask.TASK_PULL_APP_SETTING, type: AppSettingPullTask)
    }

    private void gitConfig() {
        // git
        GitMeta gitMeta = mProject.extensions.create(GitMeta.GIT_CONFIG, GitMeta)

        mProject.task(GitPushBuildTask.TASK_GIT_PUSH, type: GitPushBuildTask)
    }
}
