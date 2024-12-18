# Chapter 11 - Product API implemented with Helidon

## Build and run


With JDK21
```bash
mvn package
java -jar target/product-api-helidon.jar
```

## Example requests
```shell script
curl -v -X PUT http://localhost:8080/api/products/AK12345 -d '{"name":"testprod235","description":"test description","price":123.45}' -H 'Content-Type: application/json'

curl -v http://localhost:8080/api/products/

curl -v -X PATCH http://localhost:8080/api/products/AK12345 -d '{"description":"updated description"}' -H 'Content-Type: application/json'

curl -v -X DELETE http://localhost:8080/api/products/AK12345
```
