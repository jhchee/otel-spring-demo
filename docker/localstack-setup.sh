#!/bin/sh

echo "Initializing localstack setup..."
awslocal sqs create-queue --queue-name my-queue