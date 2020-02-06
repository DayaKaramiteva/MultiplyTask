#!/bin/bash
# execute ./start.sh from the root dir of the project

function build_maven {
    mvn clean install    
}

function container_ops {
    docker build -t dayanak/multi-task .
#    docker run -p 8080:8080 -t dayanak/multi-task
}

function k8s_setup {
    kubectl apply -f multitask.yaml        
    kubectl expose deployment multi-task --type=LoadBalancer --port=8080
}

function run_app {
    build_maven
    container_ops
    k8s_setup
}

run_app
