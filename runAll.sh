#!/bin/bash

for ver in 4.1.2 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4;do
  echo
  echo Testing version ${ver}
  ./gradlew run -PpoiVersion=${ver}
done
