# A3 Artatawe implementation.

## Environment Setup

There are two ways to compile this project, using Maven or the Command Line.
The command line might be easier because our Maven setup doesn't always work well.

## Compiling from Command Line

If you have java *9* installed on your machine,

Run the bash script:
build.sh

To execute the compiled code run:
run.sh

The reason you should have java 9 is because the library we are using (jfoenix) has to use a different JAR for java 9. Both jars are included in the lib folder.

## Compiling with Maven

### Intellij

1. Open intellij.
2. "File" >> "New" >> "Project From Existing Sources".
3. Select the location of local repository.
4. Tick "Import From External Module" and select Maven.
6. Build project.

### Eclipse

1. Open eclipse
2. Click "File" >> "Import Projects from FileSystem".
3. Set the "Import Source" directory to the location of the local repository.
4. Press finish and wait.
5. Build project.
