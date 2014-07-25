Lib-Database
============

A library for `easy` accessing an [Apache Derby] database in a [JavaFX] &amp; [Maven] application. Current `version` is `0.0.1-SNAPSHOT` (07.2014).



Content
-------

* [Example](#Example)
* [Requirements](#Requirements)
* [Installation](#Installation)
* [Documentation](#Documentation)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Example<a name="Example" />
-------

```java
/**
 * The factory <code>de.pro.lib.database.api.DatabaseFactory</code> provides a 
 * singleton instance of the Interface <code>de.pro.lib.database.api.IDatabase</code>.
 *
 * @author PRo
 * @see de.pro.lib.database.api.IDatabase
 */
public final class DatabaseFactory
```

```java
/**
 * Create a database with the specific name in the folder System.getProperty("user.dir")
 * + File.separator + "database" if it not exists.
 * 
 * @param databaseName The name for the database which should be created.
 * @param user The user for the database.
 * @param password The password for the database.
 */
DatabaseFactory.getDefault().register(String databaseName, String user, String password);
```

```java
/**
 * Provides a sql connection to the registered database.
 * 
 * @return The sql connection.
 */
DatabaseFactory.getDefault().getConnection();
```

```java
/**
 * Close the sql connection and shutdown the database.
 * 
 * @throws SQLException 
 */
DatabaseFactory.getDefault().shutdown() throws SQLException;
```



Requirements<a name="Requirements" />
------------

* On your system you need [JRE 8] installed.
* The library [Lib-Database-0.0.1-SNAPSHOT.jar](#Installation).
  * Included is the [derby-10.10.2.0.jar].
* The library [Lib-Logger-0.0.1-SNAPSHOT.jar](#Installation).
  * Included is the [log4j-api-2.0-rc2.jar].
  * Included is the [log4j-core-2.0-rc2.jar].


Installation<a name="Installation" />
------------

* If not installed download the [JRE 8] or the [JDK 8].
  * Optional: To work better with [FXML] files in a [JavaFX] application download the [JavaFX Scene Builder] under 'Additional Resources'.
* Choose your preferred IDE (e.g. [NetBeans], [Eclipse] or [IntelliJ IDEA]) for development.
* Download or clone [Lib-Database].
* Download or clone [Lib-Logger].
* Open the projects in your IDE and run them.



Documentation<a name="Documentation" />
-------------

Momentary only the [JavaDoc] in the library is available.



Contribution<a name="Contribution" />
------------

* If you find a bug I will be glad if you will report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
-------

PRo-Database is licensed under [General Public License 3.0].



Autor<a name="Autor" />
-----

Pro-Database is maintained by me, Peter Rogge. See [Contact](#Contact).



Contact<a name="Contact" />
-------

You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Links)
[Apache Derby]:http://db.apache.org/derby/
[derby-10.10.2.0.jar]:http://db.apache.org/derby/
[Eclipse]:https://www.eclipse.org/
[FXML]:http://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[IntelliJ IDEA]:http://www.jetbrains.com/idea/
[Issue]:https://github.com/Naoghuman/lib-database/issues
[JavaDoc]:http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JavaFX Scene Builder]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[Lib-Database]:https://github.com/Naoghuman/lib-database
[Lib-Logger]:https://github.com/Naoghuman/lib-logger
[log4j-api-2.0-rc2.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[log4j-core-2.0-rc2.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[Maven]:http://maven.apache.org/
[NetBeans]:https://netbeans.org/
[Pull Request]:https://help.github.com/articles/using-pull-requests


