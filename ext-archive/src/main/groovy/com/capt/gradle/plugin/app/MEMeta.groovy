package com.capt.gradle.plugin.app

import com.capt.gradle.plugin.Meta

public class MEMeta implements Meta {
    public static final String META_ME = 'me'

    def host = 'mq.sq84.com'
    def port = '1883'
    def userName = 'bi'
    def password = 'Horizon147503'
    def _project = ''

    @Override
    String toProperties() {

        return "\n# ME\n" +
                "me_host=${host}\n" +
                "me_port=${port}\n" +
                "me_user_name=${userName}\n" +
                "me_password=${password}\n" +
                "me_project=${_project}\n"
    }

    @Override
    String toString() {

        return toProperties()
    }
}