package me.linckode.buildversioningserver.user

import me.linckode.buildversioningserver.yaml.Yaml

class User : Yaml {

    lateinit var name: String
    lateinit var accessToken: String
    var apps: Array<String?>? = arrayOfNulls(0)


    constructor()
    constructor(name: String, accessToken: String, apps: Array<String?>?) : super() {
        this.name = name
        this.accessToken = accessToken
        this.apps = apps
    }


}