package com.capt.gradle.plugin.runtime

import com.capt.gradle.plugin.Meta

class ALiYunMeta implements Meta {
    public static final String META_ALIYUN = 'aliyun'

    def appKey
    def appSecret
    def accountId
    def channel

    @Override
    String toProperties() {

        return "\n# ALiYun\n" +
                "alibaba_appkey=${appKey}\n" +
                "alibaba_appsecret=${appSecret}\n" +
                "alibaba_accountid=${accountId}\n" +
                "alibaba_channel=${channel}\n"
    }
}