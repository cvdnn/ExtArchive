package com.capt.gradle.plugin.setting

import com.capt.gradle.plugin.runtime.RuntimeMeta
import com.capt.gradle.plugin.runtime.AppRuntimeMeta
import com.capt.util.Assert
import com.capt.util.TextUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class AppSettingPullTask extends DefaultTask {
    public static final String TASK_PULL_APP_SETTING = 'pullAppSetting'

    private static final def BUILD_TEMP_PATH = 'tmp/config'
    private static final def APP_SETTING_FILE = 'app_setting.ppm'

    private static String mProjectPath
    private static File tempFile;

    private static RuntimeMeta mRuntimeMeta;

    @TaskAction
    void action() {
        mRuntimeMeta = project.extensions."${RuntimeMeta.RUNTIME_CONFIG}";
        mProjectPath = mRuntimeMeta."${AppRuntimeMeta.META_APP_RUNTIME}".sdpath

        tempFile = new File(project.buildDir, BUILD_TEMP_PATH)
        tempFile.mkdirs()

        def pullAppSettingCommand = "adb pull /sdcard/${mProjectPath}/bin/${APP_SETTING_FILE} ${tempFile.absolutePath}"
        def process = pullAppSettingCommand.execute()
        process.waitFor()
        if (process.exitValue()) {
            println process.err.text

        } else {
            println process.text

            def appSettingFile = new File(tempFile, APP_SETTING_FILE)
            if (Assert.exists(appSettingFile)) {
                def mixText = appSettingFile.getText('UTF-8');

                println mixText
                println '----------------------'

                def settingText = TextUtils.fromFake(mixText);

                println settingText
            } else {
                println ':: AppSetting is not exists'
            }
        }
    }

    public static void main(String[] args) {
        String cmd = "adb pull /sdcard/SmartHome/bin/app_setting.ppm";

        def process = cmd.execute()
        process.waitFor()
        if (process.exitValue()) {
            println process.err.text

        } else {
            println process.text
        }

        def sout = new StringBuilder(), serr = new StringBuilder()
        process = cmd.execute()
        process.consumeProcessOutput(sout, serr)

        println "Standard output: ${sout}, Standard error: ${serr}"
    }
}