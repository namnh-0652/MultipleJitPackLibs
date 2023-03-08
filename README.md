[![](https://jitpack.io/v/namnh-0652/MultipleJitPackLibs.svg)](https://jitpack.io/#namnh-0652/MultipleJitPackLibs)

## MultipleJitPackLibs

Testing release multiple libraries to Jitpack

### Add maven plugin to project
In `settings.gradle`
```groovy
pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // Add this maven
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // Add this maven
    }
}
```
In root project `build.gradle`
```groovy
buildscript {
    dependencies {
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
    }
}
```

### Create gradle.properties to manage each library version

```groovy
groupId=com.github.USER.REPOS
artifactId=YOUR_ATIFACT_ID
versionName=YOUR_ATIFACT_VERSION_NAME
versionCode=YOUR_ATIFACT_VERSION_CODE
```

### For each library/build.gradle

```groovy
plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'maven-publish' // => Add this plugin
}
group = findProperty('groupId') // set groupId for your library

android {
    namespace 'your.library.namespace'
    compileSdk 33

    defaultConfig {
        minSdk 23 // your minSdk
        targetSdk 33 // your targetSdk
        versionName = findProperty('versionName')
        versionCode = findProperty('versionCode')

        // other settings
    }
}

afterEvaluate {
    publishing {
        publications {
            release(MavenPublication) {
                from components.release
                groupId = findProperty('groupId')
                artifactId = findProperty('artifactId')
                version = findProperty('versionName')
            }
        }
    }
}
```

### Setting Java version

By default, Jitpack uses Java 8. If your library uses other Java version, create file `jitpack.yml`
to root project.

```yml
// Example for Java 11
jdk:
  - openjdk11
```

### To exclude specific module

By default, Jitpack provides some default env & support you to custom env, [see here](https://jitpack.io/docs/BUILDING/#build-environment) for detail.
You can exclude any submodule by ignore it in `settings.gradle` by checking specific env value.

For example, we can ignore Jitpack build `sample` module like:
```groovy
// Build sample if not build by JITPACK
if (!System.env.JITPACK)
    include ':sample'
```

### Create release tag to publish library to Jitpack

From your repository create a release tag to publish your library to `Jitpack`
