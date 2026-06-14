#!/bin/bash

for ver in 4.0.0 4.0.1 4.1.0 4.1.1 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1 5.5.0 5.5.1;do
  echo
  echo Testing version ${ver}
  ./gradlew runDocument -PpoiVersion=${ver} -Pfile="src/main/resources/file-sample_1MB.doc"
done
