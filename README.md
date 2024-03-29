### [Gradle Command]

```gradle
./gradlew -p ext-archive clean build uploadArchives pushArchive --stacktrace
```

### [Maven]

```gradle
uploadArchives {
    repositories.mavenDeployer {
        repository(url: LOCAL_REPO_URL)
        pom.groupId = GROUP_ID
        pom.artifactId = ARTIFACTID
        pom.version = LIBRARY_VERSION
    }
}
```
相应的 `gradl.properties` 配置文件如下：

```properties
#包信息
GROUP_ID = android.ztone
ARTIFACTID = ext-archive
LIBRARY_VERSION = 0.2

#Mac下地址:file:///Users/<username>/my/local/repo
LOCAL_REPO_URL = file:///datum/workspace/m2/mvn-repo/
```

执行`gradle`命令：
```shell
./gradlew -p ext-archive clean build uploadArchives
```

### [Apply]

在 `build.gradle` 最后添加下列代码：

```gradle
buildscript {
    repositories {
        maven {
            url REMOTE_REPO_URL
        }
    }

    dependencies {
        classpath 'com.capt:ext-archive:0.2'
    }
}

apply from: 'https://raw.githubusercontent.com/jzg01/mvn-repo/master/mixAppConfig.gradle'
```

其中 `gradle.properties` 中添加下列配置：

```properties
# ###################################
# (1) The document provides application required for normal operation
#     parameters at runtime priority caught in the '$app_path' under the profile;
# (2) The document is used to simplify the development and ancillary testing;
#
# ###################################

# url
url_base						= http://talkback.sq84.com/app
#account interface url
url_cas							= http://cas.sq84.com/v2/account
url_image						= http://img.sq84.com/upload

#get tgt server
tgt_server						= http://cas.sq84.com/v1/tickets

# message exchage
me_host							= mq.sq84.com

# mqtt: 1883; rabbitmq:5672
me_port							= 1883

me_user_name					= bi
me_password						= Horizon147503

me_project						= talkback

aes_pwd 						= horizon147503

wx_appid						= wxc66eb581a42500b7

# PreResolveHosts
dns_resolve_hosts				= talkback.sq84.com,mq.sq84.com,cas.sq84.com,img.sq84.com,access.sq84.com,sq84.com,test.sq84.com
```

### [MODULE]

执行`gradle`命令

```
./gradlew :app.main:pullAppSetting

```
会得到下面的结果（获取app_setting.ppm内容）：

```
Parallel execution with configuration on demand is an incubating feature.
Incremental java compilation is an incubating feature.
WARNING [Project: :app.main] To shrink resources you must also enable ProGuard
:app.main:pullAppSetting
[100%] /sdcard/SmartHome/bin/app_setting.ppm

jiIPGw4TMhQjGhkuWYMgRVOBI1BChHtoj3JocXhtc26BgSIoF3t9aHVxb4txdH5yZTFAOjI3SC5TbgZIEFpOP048NEU3cX5vd3FvfHJsfXB/Vw==
----------------------
#APP_SETTING
#Wed Dec 28 19:59:26 GMT+08:00 2016
password=[m]
account=13800138000


BUILD SUCCESSFUL

```