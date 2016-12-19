Manual (eng)
To start the project need the latest version of Android Studio.

1.After installation, in the project selection window will point to the opening of projects with source control systems. 
2.Select GitHub, copy the link to the project. 
3.Then we open it. 
4.Connect any device on Android or create emulation. 
5.In the end, we run the project from the Studio and begin to interact with the program.

Manual (eng)
To start the project in docker-android

To build the image must enter the command:
    docker build --tag ashtanko/wowsassistant <PROJECT_DIR>
In image was organized all depends needed for the project.
To start a container image must enter the command:
    docker run -t -v $(pwd)/project ashtanko/wowsassistant:latest ./gradlew clean assembleRelease
In container project will built and .apk of project will generated.