# Devoxx Hands-On Lab

## Batch Processing at Scale: a hands-on JobRunr workshop with experiments on Raspberry Pi

### Overview

This workshop will give you an intro to JobRunr and Java on the RPi and we will be:

0. Configuring JobRunr with Spring Boot
1. Enqueueing a Job with JobRunr
    1. using a Java 8 Lambda and the `JobScheduler` API
    2. using a `JobRequest` and the `JobRequestScheduler` API
2. Monitoring jobs with the Dashboard
    1. changing job name in the dashboard
    2. using labels
    3. logging and job progress
3. Ooh no - an exception occurred!
4. Scheduling a Job
5. Recurring Jobs
    1. Creating a Recurring Job using the `@Recurring` annotation
    2. Creating a Recurring Job using the `JobScheduler` or `JobRequestScheduler` API
6. Optional: what if something goes wrong in your long running Job?

### Documentation

All documentation can be found at:

* https://www.jobrunr.io/en/
* https://www.pi4j.com/

### Test URLs

* http://pi5.local:8080/test/led/on -> all on
* http://pi5.local:8080/test/led/off -> all off
* http://pi5.local:8080/test/led/1 till 6 -> toggle the given LED
* http://pi5.local:8080/test/lcd -> display random text on both lines
* http://localhost:8080/test/lcd/0/test -> display "test" on first line
* http://pi5.local:8080/demo1/drink-beer-via-lambda?beerType=obuz
* http://pi5.local:8080/demo1/drink-beer-via-lambda?beerType=obuz
* http://pi5.local:8080/demo1/brew-beer?beerType=obuz

### Please rate the Workshop!