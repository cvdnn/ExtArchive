### * Maven

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
相应的gradl.properties配置文件如下：
```properties
#包信息
GROUP_ID = com.capt
ARTIFACTID = ext-archive
LIBRARY_VERSION = 0.2

#Mac下地址:file:///Users/<username>/my/local/repo
LOCAL_REPO_URL = file:///datum/workspace/m2/mvn-repo/
```

执行`gradle`命令：
```shell
./gradlew -p ext-archive clean build uploadArchives
```

### * Apply

在build.gradle最后添加下列代码：

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

其中gradle.properties中添加下列配置：

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
