package com.capt.gradle.plugin.git

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class GitPushBuildTask extends DefaultTask {
    public static final String TASK_GIT_PUSH = 'gitPush'

    private GitMeta mGitMeta

    @TaskAction
    void action() {
        mGitMeta = project.extensions."${GitMeta.GIT_CONFIG}"

        def sout = new StringBuilder(), serr = new StringBuilder()

        def process = 'ls'.execute([], new File(mGitMeta.repo))
        process.consumeProcessOutput(sout, serr)

        println "Standard output: $sout"
        println "Standard error: $serr"

        println '------'

        'ls'.execute([], new File(mGitMeta.repo))
        process.in.eachLine { line ->
            println line
        }
    }
}