#!/bin/bash

# This script allows to test different combinations of log4j modules "api" and "core"
# as we saw compatibility-issues at times, e.g. https://github.com/apache/logging-log4j2/issues/3196
#
# Note: You need to adjust build.gradle somewhat to force different versions of log4j
#

RESULT=

VERSIONS="2.23.1 2.24.0 2.24.1 2.24.2 2.24.3 2.25.0"
for api in ${VERSIONS};do
  for core in ${VERSIONS};do
    echo
    echo Testing versions ${api} and ${core}

    ./gradlew runClose -Plog4jApi=${api} -Plog4jCore=${core}
    RESULT="${RESULT} -- Testing versions ${api} and ${core}: $?"
  done
done

echo "RESULT: ${RESULT}"
