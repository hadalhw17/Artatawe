
mkdir -p bin
find src -name "*.java" > sources.temp
javac -d bin -cp "lib/java9/jfoenix-9.0.0.jar" @sources.temp
rm sources.temp
