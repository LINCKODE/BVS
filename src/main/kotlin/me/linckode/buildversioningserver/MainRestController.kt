package me.linckode.buildversioningserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/versioning")
class MainRestController {

    @GetMapping
    fun getStatus(): String {
        return "BVS version ${Main.version}"
    }

}