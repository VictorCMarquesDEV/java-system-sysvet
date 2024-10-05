FROM alpine:latest

RUN apk add --no-cache openjdk21

RUN apk add --no-cache maven

COPY ./ ./

RUN mvn clean install

RUN mvn clean package

CMD ["mvn", "exec:java"]