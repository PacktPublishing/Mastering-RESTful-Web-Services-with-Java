## Chapter 9 - Monitoring and Observability

In this chapter you have 3 types of project examples:

- Central Logging Filter
- Micrometer
- Open Telemetry

In each of these projects, you will find both the Order Management API and the Product API implementing each of these specific topics.

To see then working, you need to start both projects for the corresponding feature, for example, Micrometer, start both services under that folder only, otherwise the traces propagation will not work.

### Create a product first

`curl -X 'PUT' \
'http://localhost:8080/api/products/AK12345' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"name": "Grey Laptop",
"description": "A grey high performance laptop",
"price": 600
}'`

### Create an Order with the SKU from the created product

`curl -X 'POST' \
'http://localhost:8090/orders' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"customer": {
"streetAddress": "5th Avenue",
"city": "New York",
"postalCode": "10003",
"customerType": "person",
"firstName": "Igor",
"lastName": "Fraga"
},
"products": [
{
"productSKU": "AK12345",
"quantity": 1
}
]
}'`

### Look for the logs

Now, you should see at the logs of both services the traces being propagated between then