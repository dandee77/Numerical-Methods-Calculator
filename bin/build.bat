@echo off
javac -cp libs/jaylib-5.0.0-0.jar src/*.java
java -Djava.library.path=libs/native -cp libs/jaylib-5.0.0-0.jar;. src/Main
cls