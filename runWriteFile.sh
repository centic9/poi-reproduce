#!/bin/bash

for ver in 4.0.0 4.0.1 4.1.0 4.1.1 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1 5.5.0 5.5.1 6.0.0;do
  javaVersion=11
  if [ ${ver} == "6.0.0" ]; then
    # POI 6.0.0 and newer needs minimum JDK 17
    javaVersion=17
  fi
  for compressTemp in true false; do
    for sharedStringTable in true false;do
      echo
      echo Testing version ${ver} - ${compressTemp} - ${sharedStringTable}
      ./gradlew runWriteFile -PpoiVersion=${ver} -PjavaVersion=${javaVersion} -PcompressTemp=${compressTemp} -PsharedStringTable=${sharedStringTable}
    done
  done
done
