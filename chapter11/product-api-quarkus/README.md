# Chapter 11 - Product API implemented with Quarkus

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Example requests
```shell script
curl -v -X PUT http://localhost:8080/api/products/AK12345 -d '{"name":"testprod235","description":"test description","price":123.45}' -H 'Content-Type: application/json'

curl -v http://localhost:8080/api/products/

curl -v -X PATCH http://localhost:8080/api/products/AK12345 -d '{"description":"updated description"}' -H 'Content-Type: application/json'

curl -v -X DELETE http://localhost:8080/api/products/AK12345
```
