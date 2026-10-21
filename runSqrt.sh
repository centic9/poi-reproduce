#!/bin/bash

# Versions up to 5.5.1 are failing
for ver in 5.0.0 5.1.0 5.2.0 5.2.1 5.2.2 5.2.3 5.2.4 5.2.5 5.3.0 5.4.0 5.4.1 5.5.0 5.5.1 6.0.0;do
  javaVersion=11
  if [ ${ver} == "6.0.0" ]; then
    javaVersion=17
  fi

  echo
  echo Testing Sqrt version ${ver}
  ./gradlew runSqrt -PpoiVersion=${ver} -PjavaVersion=${javaVersion}
done
