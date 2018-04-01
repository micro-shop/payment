FROM java:8-jre
ADD target/payment-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./payment-0.0.1-SNAPSHOT.jar", "--port=80"]