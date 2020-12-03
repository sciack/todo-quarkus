#!/bin/bash

BASEDIR=$(dirname "$0")

docker exec mongodb mongo admin --eval  "$(cat ${BASEDIR}/createUser.js)"
