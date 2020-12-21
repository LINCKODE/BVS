package me.linckode.buildversioningserver.app

import me.linckode.buildversioningserver.yaml.Yaml

class App : Yaml {

    lateinit var name: String
    var currentBuild :Int? = null
    var contributors: Array<String?>? = arrayOfNulls(0)
    lateinit var lastUpdated: String
    lateinit var lastUpdater: String

    constructor() {}
    constructor(name: String, currentBuild: Int, contributors: Array<String?>?, lastUpdated: String, lastUpdater: String) : super() {
        this.name = name
        this.currentBuild = currentBuild
        this.contributors = contributors
        this.lastUpdated = lastUpdated
        this.lastUpdater = lastUpdater
    }


}