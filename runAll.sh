#!/bin/bash

for ver in 3.17 4.0.0 4.0.1 4.1.0 4.1.1 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1;do
  echo
  echo Testing version ${ver}

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=stream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=file
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=path
  git co src/main/resources/* | grep -v "^0 "

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcstream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcfile
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=opcpath
  git co src/main/resources/* | grep -v "^0 "

  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialstream
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialfile
  git co src/main/resources/* | grep -v "^0 "
  ./gradlew runClose -PpoiVersion=${ver} -PcloseTest=specialpath
  git co src/main/resources/* | grep -v "^0 "
done
