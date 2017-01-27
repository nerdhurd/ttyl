# ttyl
[![Build Status](https://travis-ci.org/willsalz/ttyl.svg?branch=master)](https://travis-ci.org/willsalz/ttyl)

## dependencies
- java 8
- maven 3

## build
```lang=bash
$ mvn package
```

## run
```lang=bash
$ env PORT=8888 TWILIO_ACCOUNT_SID=<your-sid> TWILIO_AUTH_TOKEN=<your-auth-token> TWILIO_PHONE_NUMBER=<your-twilio-phone-number> java -jar target/ttyl-1.0-SNAPSHOT.jar server config/development.yaml
```
