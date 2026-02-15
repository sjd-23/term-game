#!/bin/bash

javac *.java
java Main

find . -name "*.class" -type f -delete