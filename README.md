# orders-service
### Run Instructions
- In the command line, run `./gradlew bootRun`
- Open another terminal window and enter the following curl command (for example): 
  ` curl -X POST -H "Content-Type: application/json" -d '{"numApples": 3, "numOranges": 5}' http://localhost:8080/orders`
  - This same process can also be done using Postman, SoapUI, etc.
- To run the unit tests, run `./gradlew test`