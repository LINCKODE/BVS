@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package me.linckode.buildversioningserver.user

import me.linckode.buildversioningserver.yaml.Yaml

import java.io.File

object UserManager {


    @JvmStatic
    fun loadUserFromFile(file: File): User?{
        return loadUser(file.name.replace(".yaml", ""))
    }

    @JvmStatic
    fun loadUser(userName: String): User?{
        val userFile = File("users/$userName.yaml")
        if (!userFile.exists())
            return null
        var user = User()
        user = user.getFromFile(userFile) as User
        return user
    }

    @JvmStatic
    fun loadAllUsers(): Array<User?>? {
        val userFolder = File("users/")
        if (userFolder.listFiles().isEmpty()){
            return null
        }
        val users = arrayOfNulls<User>(userFolder.listFiles().size)
        for ((index, file) in userFolder.listFiles().withIndex()) {
            val user = loadUserFromFile(file)
            users[index] = user
        }
        return users
    }

    @JvmStatic
    fun createUser(user: User): User{
        val userFile = File("users/${user.name}.yaml")
        Yaml.saveToFile(userFile, user)
        var savedUser = User()
        savedUser = savedUser.getFromFile(userFile) as User
        return savedUser
    }


}