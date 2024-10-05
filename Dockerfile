FROM alpine:latest

RUN apk add --no-cache openjdk21

RUN apk add --no-cache maven

RUN apk add --no-cache bash