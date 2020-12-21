package me.linckode.buildversioningserver.app

import me.linckode.buildversioningserver.Main
import me.linckode.buildversioningserver.user.User
import me.linckode.buildversioningserver.user.UserManager
import me.linckode.buildversioningserver.yaml.Yaml
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/api/versioning/apps")
class AppRestController {

    @GetMapping
    fun getApps(): Array<App?>?{
        return AppManager.loadAllApps()
    }

    @GetMapping("/{app}")
    fun getApp(@PathVariable("app") app: String): App?{
        return AppManager.loadApp(app)
    }

    @PostMapping("/{appName}")
    fun createApp(@PathVariable("appName") appName: String, @RequestParam("masterToken") masterToken: String, ): App{
        if (masterToken != Main.config?.masterToken)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)

        val app = App(appName, 0, null, "", "")

        return AppManager.createApp(app)
    }

    @PutMapping("/{appName}/updateBuild")
    fun updateBuild(@PathVariable("appName") appName: String,
                    @RequestParam("user") user: String,
                    @RequestParam("accessToken") accessToken: String): App? {

        if (AppManager.loadApp(appName) == null)
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (UserManager.loadUser(user) == null)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        var userObject = User()
        val userFile = File("users/$user.yaml")
        userObject = userObject.getFromFile(userFile) as User
        if (userObject.accessToken != accessToken)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)


        val appFile = File("apps/$appName.yaml")
        var app = App()
        app = app.getFromFile(appFile) as App

        if (app.contributors?.contains(user) == false || app.contributors == null) {

            if (app.contributors == null)
                app.contributors = arrayOf(user)
            else app.contributors = app.contributors?.plus(user)
        }
        if (userObject.apps?.contains(appName) == false || userObject.apps == null ){
            if (userObject.apps == null)
                userObject.apps = arrayOf(appName)
            else userObject.apps = userObject.apps?.plus(appName)

            Yaml.saveToFile(userFile, userObject)
        }

        app.currentBuild = app.currentBuild?.plus(1)
        app.lastUpdater = user
        val currentTime = Date()

        app.lastUpdated = SimpleDateFormat("hh:mm:ss/dd-MM-yyyy").format(currentTime)

        Yaml.saveToFile(appFile, app)
        return AppManager.loadApp(appName)

    }




}