package com.zm.jvmtest

import org.gradle.api.Plugin
import org.gradle.api.Project

public class MySecondPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println("====================")
        println("this is my second plugin")
        println("====================")
        println "test no bracket"
    }
}