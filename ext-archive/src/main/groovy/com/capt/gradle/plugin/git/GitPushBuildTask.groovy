package com.capt.gradle.plugin.git

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class GitPushBuildTask extends DefaultTask {
    public static final String TASK_GIT_PUSH = 'gitPush'

    @TaskAction
    void action() {


        println 'git status'.execute().getText()
    }
}