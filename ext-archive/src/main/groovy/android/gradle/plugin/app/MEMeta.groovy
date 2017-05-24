package android.gradle.plugin.app

import android.gradle.plugin.Meta

public class MEMeta implements Meta {
    public static final String META_ME = 'me'

    def host = 'mq.sq84.com'
    def port = '1883'
    def userName = 'bi'
    def password = 'Horizon147503'

    @Override
    String toProperties() {

        return "\n# ME\n" +
                "me_host=${host}\n" +
                "me_port=${port}\n" +
                "me_user_name=${userName}\n" +
                "me_password=${password}\n"
    }

    @Override
    String toString() {

        return toProperties()
    }
}