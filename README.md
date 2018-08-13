### BigPanda Backend task

Implementing a Non Blocking Producer/Consumer stream processing service that exposes an HTTP API.

Configuration

Edit the application.properties before running the tool

Configuration options

stream_file_path - set the executable infinite stream location

To run the utility please run spring-boot-1.0-SNAPSHOT.jar jar file

cd springboot\target
java -jar spring-boot-1.0-SNAPSHOT.jar <stream_location> (for example: C:\Alon\generator-windows-amd64.exe)

Supported REST API

http://localhost:8080/countEventType -  count of events by event type

http://localhost:8080/countDataField - A count of words encountered in the data field

http://localhost:8080/ping - health check


Things to improve

1. Add logger and print info, debug and severe messages.
2. Support multiple subscribers that will deploy as clusters for high throughput.
3. Improve how the exceptions were handled.