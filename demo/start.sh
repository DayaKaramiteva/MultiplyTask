#!/bin/bash
# execute ./start.sh from the root dir of the project
logfile="./log/bootstrap.log"
function catch_error () {
    if [ $1 -ne 0 ]; then
        printf "ERROR! Function "$2" failed. Please refer to $logfile for more information\n"
        exit 1
    fi
}
function create_log {
    if [[ ! -e $logfile ]]; then
        mkdir -p ./log
        touch $logfile
    else
        :> "$logfile"
    fi
}
function build_maven {
    mvn clean install >> "$logfile" 2>&1
}
function container_ops {
    docker build -t dayanak/multi-task . >> "$logfile" 2>&1
}
function k8s_setup {
    kubectl get deployment multi-task > /dev/null 2>&1
    if [ $? -ne 0 ]; then
        kubectl apply -f multitask.yaml >> "$logfile" 2>&1
        kubectl expose deployment multi-task --type=LoadBalancer --port=8080 >> "$logfile" 2>&1
    fi
}
function run_app {
    printf "Truncating log file ...\n"
    create_log
    catch_error $? "$_"
    printf "Starting maven build ...\n"
    build_maven
    catch_error $? "$_"
    printf "Building Docker image ...\n"
    container_ops
    catch_error $? "$_"
    printf "Creating Kubernetes deployment ...\n"
    k8s_setup
    catch_error $? "$_"
}

run_app
