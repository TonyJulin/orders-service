# orders-service
### Run Instructions
- In the command line, run `./gradlew bootRun`
- Open another terminal window and enter the following curl command (for example): 
  ` curl -X POST -H "Content-Type: application/json" -d '{"numApples": 3, "numOranges": 5}' http://localhost:8080/orders/create`
  - This same process can also be done using Postman, SoapUI, etc.
- To run the unit tests, run `./gradlew test`

### Using the API
NOTE: BOGO apple offer and buy 3 oranges at the price of 2
- POST order - `/orders/create`
  - Command:`curl -X POST -H "Content-Type: application/json" -d '{"numApples": 3, "numOranges": 6}' http://localhost:8080/orders/create`
  - Expected output:
    ```json
    {"id":3,"numApples":2,"numOranges":3,"appleOrderCost":0.6,"orangeOrderCost":0.5,"orderTotalCost":1.1}
    ```
- GET order by ID - `/orders/{id}`
  - Command: `curl -X GET http://localhost:8080/orders/{id}`
  - Expected output:
    ```json
    {"id":3,"numApples":2,"numOranges":3,"appleOrderCost":0.6,"orangeOrderCost":0.5,"orderTotalCost":1.1}
    ```
- GET all orders - `/orders`
  - Command: `curl -X GET http://localhost:8080/orders`
  - Expected output:
    ```json
    [{
     "id": 1,
     "numApples": 6,
     "numOranges": 6,
     "appleOrderCost": 1.8,
     "orangeOrderCost": 1.0,
     "orderTotalCost": 2.8
    },
    {
     "id": 2,
     "numApples": 16,
     "numOranges": 7,
     "appleOrderCost": 4.8,
     "orangeOrderCost": 1.25,
     "orderTotalCost": 6.05
    },
    {
     "id": 3,
     "numApples": 2,
     "numOranges": 3,
     "appleOrderCost": 0.6,
     "orangeOrderCost": 0.5,
     "orderTotalCost": 1.1
    },
    {
       "id": 4,
       "numApples": 2,
       "numOranges": 3,
       "appleOrderCost": 0.6,
       "orangeOrderCost": 0.5,
       "orderTotalCost": 1.1
    }]
  ```
### Next Steps
**NOTE**: The unit tests currently do not work. See bullet points below.
- Finish unit tests for the order service (ran into an issue with junit jupiter dependencies)
   - Mock out db calls for retrieving order by id, all orders, and creating an order
- Revamp unit tests for controller to mock out the new Orders service
