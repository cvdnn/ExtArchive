package android.gradle.plugin.app

import android.gradle.plugin.Meta

public class DNSMeta implements Meta {
    public static final String META_DNS = 'dns'

    def resolves = ''

    @Override
    String toProperties() {

        return "\n# DNS\n" +
                "dns_resolve_hosts=${resolves}\n"
    }

    @Override
    String toString() {

        return toProperties()
    }
}