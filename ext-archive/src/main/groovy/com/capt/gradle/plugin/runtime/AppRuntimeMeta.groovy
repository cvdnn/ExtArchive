package com.capt.gradle.plugin.runtime

import com.capt.gradle.plugin.Meta

class AppRuntimeMeta implements Meta {
    public static final String META_APP_RUNTIME = 'app'

    def id = ''
    def secret = ''
    def sdpath = 'CAPT'
    def config = ''
    def pullFilter = ''
    def eventFilter = ''

    @Override
    String toProperties() {

        return "\n# APP RUNTIME\n" +
                "app_id=${id}\n" +
                "app_secret=${secret}\n" +
                "app_path=${sdpath}\n" +
                "app_config=${config}\n" +
                "app_pull_filter=${pullFilter}\n" +
                "app_event_filter=${eventFilter}\n"
    }
}