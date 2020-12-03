#!/bin/bash

BASEDIR=$(dirname "$0")

docker container ls -a |grep -q mongodb
result=$?
if [ $result -eq 0 ]; then
  docker start mongodb
else
  docker run --name mongodb -p 27017:27017 -d mongo:4.2.11-bionic
  echo "Waiting 5s for mongo to start up"
  sleep 5s
  ./createUser.sh
fi


