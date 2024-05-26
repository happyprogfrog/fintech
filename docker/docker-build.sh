#!/bin/sh

# Setting versions
VERSION='1.0.0'

cd ..

./gradlew clean build -x test

ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'api Docker Image build...'
cd $ROOT_PATH/api && docker build -t fintech-api:$VERSION .
echo 'api Docker Image build... Done'

echo 'consumer Docker Image build...'
cd $ROOT_PATH/consumer && docker build -t fintech-consumer:$VERSION .
echo 'consumer Docker Image build... Done'

echo 'css Docker Image build...'
cd $ROOT_PATH/css && docker build -t fintech-css:$VERSION .
echo 'css Docker Image build... Done'

echo 'nginx Docker Image build...'
cd $ROOT_PATH/nginx && docker build -t fintech-nginx:$VERSION .
echo 'nginx Docker Image build... Done'