#!/bin/bash

for ver in 3.17 4.0.0 4.0.1 4.1.0 4.1.1 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1;do
  for compressTemp in true false; do
    for sharedStringTable in true false;do
      echo
      echo Testing version ${ver} - ${compressTemp} - ${sharedStringTable}
      ./gradlew runWriteFile -PpoiVersion=${ver} -PcompressTemp=${compressTemp} -PsharedStringTable=${sharedStringTable}
    done
  done
done
