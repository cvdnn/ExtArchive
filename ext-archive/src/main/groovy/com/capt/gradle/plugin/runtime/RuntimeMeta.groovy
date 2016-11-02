package com.capt.gradle.plugin.runtime

import com.capt.gradle.plugin.Meta

public class RuntimeMeta implements Meta {
    public static final String RUNTIME_CONFIG = 'runtimeConfig'

    def os = '2'
    def api = '4'
    def attachment = [:]

    @Override
    String toProperties() {
        def text = '\n# RUNTIME\n'

        text += "os=${os}\n" +
                "api=${api}\n"

        text += '\n# EXTEND\n'
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