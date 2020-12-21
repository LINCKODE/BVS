package me.linckode.buildversioningserver.config

import me.linckode.buildversioningserver.yaml.Yaml

class Config : Yaml {
    var masterToken = ""

    constructor() {}
    constructor(masterToken: String) {
        this.masterToken = masterToken
    }
}