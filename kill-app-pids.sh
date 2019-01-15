#!/bin/bash

for pid in `jps | grep jar | awk '{print $1}'`; do
   kill $pid;
done;

for pid in `jps | grep GradleWrapperMain | awk '{print $1}'`; do
  kill $pid;
done;

for pid in `jps | grep GradleDaemon | awk '{print $1}'`; do
  kill $pid;
done;