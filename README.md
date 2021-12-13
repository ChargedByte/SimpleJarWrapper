# SimpleJarWrapper

SimpleJarWrapper allows you to run another jar file as a subprocess.

## Usage

Download a release jar file from the [releases](https://github.com/ChargedByte/SimpleJarWrapper/releases) page or build
it yourself.

Place the jar file along with the configuration file in to a directory. You can see the example configuration
file ***[here](wrapper.example.yaml)*** for the available options.

Then you can just run the jar file with the following command:

```shell
$ java -jar SimpleJarWrapper-<VERSION>-all.jar
```

## Build

Project is intended to be compiled with JDK 11 or later.

Clone the repository to your desired location and run the following command to build the jar file:

```shell
$ ./gradlew build
```

This should produce a `SimpleJarWrapper-<VERSION>-all.jar` file in the `build/libs` directory.

## License

This project is licensed under the [MIT license](LICENSE).
