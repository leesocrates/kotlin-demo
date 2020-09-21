package com.lee.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println("====================")
        println("hello my first plugin")
        println("====================")
    }
}