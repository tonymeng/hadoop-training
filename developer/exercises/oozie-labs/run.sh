#!/bin/bash
set -e
LAB=${1?Usage: $0 job}
cp -f *.jar $LAB/job/lib
hadoop fs -rmr $LAB 2>/dev/null || true
hadoop fs -put $LAB .
oozie job -oozie http://localhost:11000/oozie -config $LAB/job/job.properties -auth simple -run
