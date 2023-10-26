[![Build Status](https://github.com/centic9/poi-rerproduce/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/centic9/poi-rerproduce/actions)
[![Gradle Status](https://gradleupdate.appspot.com/centic9/poi-rerproduce/status.svg?branch=master)](https://gradleupdate.appspot.com/centic9/poi-rerproduce/status)

Small project to test things against different versions of Apache POI.

## How it works

TBD

## Use it

### Grab and build it

    git clone https://github.com/centic9/poi-rerproduce.git
    cd poi-rerproduce
    ./gradlew check

### Run single check for single version

    ./gradlew runClose -PpoiVersion=3.17 -PcloseTest=stream

### Run all checks

    ./runAll.sh

Remove some unnecessary output:

    ./runAll.sh 2>&1 | grep -v "^WARNING:" | grep -v "^> Task :" | grep -v "File leak detector installed" | grep -v "^----$" > /tmp/output.txt

## Summary

See https://docs.google.com/spreadsheets/d/1naqkg7rvXGctJMoZ4blWb0n4vseuoD4g0cU7PQpwwOU/edit#gid=0
for a summary of running this experiment with the various versions of Apache POI

## Support this project

If you find this project useful and would like to support work on it, you can [Sponsor the author](https://github.com/sponsors/centic9)

## Change it

### Build it and run tests

    cd poi-rerproduce
    ./gradlew check jacocoTestReport

#### Licensing

* poi-rerproduce is licensed under the [BSD 2-Clause License].

[BSD 2-Clause License]: https://www.opensource.org/licenses/bsd-license.php
