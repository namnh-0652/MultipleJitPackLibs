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

### Create version.gradle to manage your library version

```groovy
ext {
    versionName = "1.0.0" // library version, if you want different library versions, add different names to each library/build.gradle
    versionCode = 1
    libGroupId = 'com.github.USER.REPOS'
}
```

### For each library/build.gradle

```groovy
plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'maven-publish' // => Add this plugin
}
apply from: '../version.gradle' // import this file
group = project.ext.libGroupId // set groupId for your library

android {
    namespace 'your.library.namespace'
    compileSdk 33

    defaultConfig {
        minSdk 23 // your minSdk
        targetSdk 33 // your targetSdk
        versionName project.ext.versionName
        versionCode project.ext.versionCode

        // other settings
    }
}

afterEvaluate {
    publishing {
        publications {
            release(MavenPublication) {
                from components.release
                groupId = project.ext.libGroupId
                artifactId = 'YOUR_ARTIFACT_ID'
                version = project.ext.versionName
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

### Create release tag to publish library to Jitpack

From your repository create a release tag to publish your library to `Jitpack`
