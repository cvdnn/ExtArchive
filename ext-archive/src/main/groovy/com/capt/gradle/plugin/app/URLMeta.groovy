package com.capt.gradle.plugin.app

import com.capt.gradle.plugin.Meta

public class URLMeta implements Meta {
    public static final String META_URL = "url"

    /** url */
    def base = ''

    /** account interface url */
    def cas = ''

    /** tag url */
    def tgt = ''

    /** image url */
    def image = ''

    @Override
    String toProperties() {

        return "\n# URL\n" +
                "url_base=${base}\n" +
                "url_cas=${cas}\n" +
                "url_tgt=${tgt}\n" +
                "url_image=${image}\n"
    }

    @Override
    String toString() {

        return toProperties()
    }
}