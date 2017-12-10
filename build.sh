
mkdir -p bin
find src -name "*.java" > sources.temp
javac -d bin -cp " lib/java9\*" @sources.temp
rm sources.temp
