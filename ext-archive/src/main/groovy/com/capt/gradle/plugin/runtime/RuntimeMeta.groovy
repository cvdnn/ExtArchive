package com.capt.gradle.plugin.runtime

import com.capt.gradle.plugin.Meta
import org.gradle.internal.impldep.org.apache.commons.collections.MapUtils

public class RuntimeMeta implements Meta {
    public static final String APP_CONFIG = 'appConfig'

    def extend = [:]

    @Override
    String toProperties() {
        def text = '\n# EXTEND\n'

        extend.each { k, v ->
            text += "${k}=${v}\n"
        }

        println '>>>>>>>: ' + MapUtils.toProperties(extend).toString()

        return text
    }

    @Override
    String toString() {

        return toProperties()
    }
}