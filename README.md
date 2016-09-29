# MovieDB

This application is a showcase example for an aplication with the following requirements:

**FUNCTIONAL REQUIREMENTS**
The first release of the app will be very limited in scope, but will serve as the foundation for future releases. It's
expected that user will be able to:
 - Scroll through the list of upcoming movies (limited to the first 50 movies) ­ including movie name, poster
image, genre and release date. This list should be ordered through release date in an increasing order.
 - Select a specific movie to see details (name, poster image, genre, overview and release date).
 - Search for movies by entering a partial or full movie name.

**TECHNICAL REQUIREMENTS**
You should see this project as an opportunity to create an app following modern development best practices (given
your platform of choice), but also feel free to use your own app architecture preferences (coding standards, code
organization, third­party libraries, etc).


Showcase
---

<p align="center">
  <img src="screenshots/demo.gif" align="center" width=200>
  <img src="screenshots/moviedb_ss_01.png" align="center" width=200>
  <img src="screenshots/moviedb_ss_02.png" align="center" width=200>
  <img src="screenshots/moviedb_ss_03.png" align="center" width=200>
  <img src="screenshots/moviedb_ss_04.png" align="center" width=200>
  <img src="screenshots/moviedb_ss_05.png" align="center" width=200>
</p>

Third-party libraries and special thanks
---
A full list of all third party libraries used throughout this project can be found at the About Activity present inside the App, however, a few mentions can be found below:

 - **Dagger 2**: Dependency Injector for Androd and Java, used to grant one of the S.O.L.I.D. principles for OO programming (Dependency Inversion Principle). Besides allowing the high level class to not depend upon low level ones, it makes Unit Test easier to perform with the help of a mocking framework i.e. Mockito;
 - **Gson**: Java library that can be used to convert Java Objects into their JSON representation. Used largely for consuming the payload of The Movie DB API;
 - **Material Dialog**: Android Library for using Honeycomb (Android 3.0) animation API on all version of the platform back to 1.0;
 - **Android Support Collection**: provided commom elements of design for build Android applications;
 - **Volley Plus**: Android library that provide usefull task to deal with webservices;
 - **Kotlin**: Kotlin is a statically typed language that targets the JVM and JavaScript. It is a general-purpose language intended for industry use. It is developed by a team at JetBrains although it is an OSS language and has external contributors. Besides the imports present in the App gradle file, it is required to download the plugins for Android Studio in order to compile the .kt classes and make the project work;
 - **Anko**: Anko is a library which makes Android application development faster and easier. It makes your code clean and easy to read, and lets you forget about rough edges of Android SDK for Java.

 
Code license
---
Copyright 2016 Edgar da Silva Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
