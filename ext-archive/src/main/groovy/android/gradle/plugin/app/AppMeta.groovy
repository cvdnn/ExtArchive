package android.gradle.plugin.app

import android.gradle.plugin.Meta

public class AppMeta implements Meta {
    public static final String APP_CONFIG = 'appConfig'

    def attachment = [:]

    @Override
    String toProperties() {
        def text = '\n# EXTEND\n'

        attachment.each { k, v ->
            text += "${k}=${v}\n"
        }

        return text
    }

    @Override
    String toString() {

        return toProperties()
    }
}