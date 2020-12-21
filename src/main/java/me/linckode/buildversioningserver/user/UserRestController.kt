package me.linckode.buildversioningserver.user

import me.linckode.buildversioningserver.Main
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/versioning/users")
class UserRestController {

    @GetMapping
    fun getUsers(@RequestParam("masterToken") masterToken: String): Array<User?>?{
        if (masterToken != Main.config.masterToken)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        return UserManager.loadAllUsers()
    }

    @GetMapping("/{user}")
    fun getUser(@PathVariable("user") user: String, @RequestParam("masterToken") masterToken: String): User?{

        if (masterToken != Main.config.masterToken)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        return UserManager.loadUser(user)
    }

    @PostMapping("/{user}")
     fun createUser(@PathVariable("user") user: String, @RequestParam("masterToken") masterToken: String,
                    @RequestParam("accessToken") accessToken: String): User {

         if (masterToken != Main.config.masterToken)
             throw ResponseStatusException (HttpStatus.UNAUTHORIZED)

        val user = User(user, accessToken, null)

        return UserManager.createUser(user)
     }


    //TODO: ADD APP TO USER APP LIST

}