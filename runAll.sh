#!/bin/bash

for ver in 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4;do
  echo
  echo Testing version ${ver}

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=stream
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=file
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=path

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcstream
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcfile
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcpath

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialstream
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialfile
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialpath
done
