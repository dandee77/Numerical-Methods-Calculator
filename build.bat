@echo off

REM Ensure bin exists and is clean
mkdir bin 2>nul
del /q bin\*.class >nul 2>&1
del /q src\*.class >nul 2>&1

REM Compile source files (no packages assumed)
javac -cp libs/jaylib-5.0.0-0.jar src/*.java

REM Move .class files to bin/
move /Y src\*.class bin\ >nul

REM Run the app (from default package)
java -Djava.library.path=libs/native -cp libs/jaylib-5.0.0-0.jar;bin src/Main