# BVS


BVS is a build versioning server written in Kotlin and Java that uses the Spring Framework.

## Features
  - Apps and users are easy to manage using the REST API
  - App and user data are stored in easily editable YAML files
  - Open Source

## Installation
>BVS requires at least java 11 to run if you use the precompiled binary but should work with java 8 if you compile from source.

Download the source code and place it in the directory you would like to run it in.
You can run it from a terminal using :
```sh
./mvnw spring-boot:run
```

On first run it will generate a configuration file and two directories for data storage.
You will have to edit the configuration file to setup a master token that will be used for user and app management.

```yaml
masterToken: "REPLACE-ME"
```

>It is recomended to use a random string of generous length.

>Note that the server will not start until you replace the default value.

## Data Structure
As specified in the features list the data is stored in YAML files.
These files can be found in the "apps" and "users" directories.

### Application YAML example:

> exampleApp.yaml

```yaml
name: "exampleApp"
currentBuild: 8
contributors:
- "exampleUser"
lastUpdated: "12:00:45/21-12-2020"
lastUpdater: "exampleUser"
```

### User YAML example:

> exapleUser.yaml

```yaml
name: "exampleUser"
accessToken: "secret"
apps:
- "exampleApp"

```


## Usage

The REST API runs on port 8080. The port cannot be changed at the moment, but this feature to do so will be added in a future update.

#### Requests:

##### GET /api/versioning/
Parameters: none
>Returns the servers version in plain text.

### User

##### GET /api/versioning/users
Parameters: masterToken
>Returns all the users in JSON format.

##### GET /api/versioning/users/<userName>
Parameters: userName as path variable and masterToken as request parameter.
>Returns the data of a certain user.

##### POST /api/versioning/users/<userName>
Parameters: userName as path variable, masterToken as request parameter and accessToken as request parameter.
>Creates a new user with the name and access token provided in the request and returns it's data.

### App

##### GET /api/versioning/apps
Parameters: none
>Returns all the apps in JSON format.

##### GET /api/versioning/apps/<appName>
Parameters: appName as path variable.
>Returns the specified application data.

##### POST /api/versioning/apps/<appName>
Parameters: appName as path variable and masterToken as request parameter.
>Creates a new application with the name that is provided in the request and empty fields then returns it's data.

##### PUT /api/versioning/apps/<appName>/updateBuild
Parameters: appName as path variable, user as request parameter and accessToken as request parameter.
>Increments the specified app's build count and updates the lastUpdater and lastUpdated variables, then returns the app's data.
