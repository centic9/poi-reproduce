#!/bin/bash

for ver in 4.0.0 4.0.1 4.1.0 4.1.1 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1 5.5.0 5.5.1 6.0.0;do
  echo
  echo Testing version ${ver}

  javaVersion=11
  if [ ${ver} == "6.0.0" ]; then
    javaVersion=17
  fi

  ./gradlew runClose -PpoiVersion=${ver} -PjavaVersion=${javaVersion} -PcloseTest=stream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=file
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=path
  git co src/main/resources/* | grep -v "^0 "

  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=opcstream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=opcfile
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=opcpath
  git co src/main/resources/* | grep -v "^0 "

  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=specialstream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=specialfile
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion= ${ver} -PjavaVersion=${javaVersion} -PcloseTest=specialpath
  git co src/main/resources/* | grep -v "^0 "
done
