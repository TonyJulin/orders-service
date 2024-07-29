# orders-service
### Run Instructions
- In the command line, run `./gradlew bootRun`
- Open another terminal window and enter the following curl command (for example): 
  ` curl -X POST -H "Content-Type: application/json" -d '{"numApples": 3, "numOranges": 5}' http://localhost:8080/orders`
  - This same process can also be done using Postman, SoapUI, etc.
- To run the unit tests, run `./gradlew test`

### Sample Input/Output
NOTE: With BOGO apple offer and buy 3 for the price of 2 oranges offer
- Input: `curl -X POST -H "Content-Type: application/json" -d '{"numApples": 3, "numOranges": 6}' http://localhost:8080/orders`
- Expected output: `{"numOranges":6,"numApples":6,"appleCost":1.8,"orangeCost":1.0,"totalCost":2.8}`