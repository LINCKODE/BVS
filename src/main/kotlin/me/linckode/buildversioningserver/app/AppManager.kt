@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package me.linckode.buildversioningserver.app

import me.linckode.buildversioningserver.yaml.Yaml
import java.io.File

object AppManager {


    @JvmStatic
    fun loadAppFromFile(file: File): App?{
        return loadApp(file.name.replace(".yaml", ""))
    }

    @JvmStatic
    fun loadApp(appName: String): App?{
        val appFile = File("apps/$appName.yaml")
        if (!appFile.exists())
            return null
        var app = App()
        app = app.getFromFile(appFile) as App
        return app
    }

    @JvmStatic
    fun loadAllApps(): Array<App?>?{
        val appsFolder = File("apps/")
        if (appsFolder.listFiles().isEmpty()){
            return null
        }
        val apps = arrayOfNulls<App>(appsFolder.listFiles().size)
        for ((index, file) in appsFolder.listFiles().withIndex()) {
            val app = loadAppFromFile(file)
            apps[index] = app
        }
        return apps
    }

    @JvmStatic
    fun createApp(app: App): App{
        val appFile = File("apps/${app.name}.yaml")
        Yaml.saveToFile(appFile, app)
        var savedApp = App()
        savedApp = savedApp.getFromFile(appFile) as App
        return savedApp
    }

}