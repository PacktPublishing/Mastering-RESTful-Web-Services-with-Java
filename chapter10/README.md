## Chapter 10 - Scaling and Performance Optimization Techniques 

### Uploading a test photo
Create the product first
```bash
curl -v -X PUT http://localhost:8080/api/products/AK12345 -d '{"name":"testprod235","description":"test description","price":123.45}' -H 'Content-Type: application/json'
```
Now add the photo, adjusting the `file=@...` path so that it contains an existing JPEG file on your file system.
```bash
curl -v -F "file=@/home/mv/Documents/image.jpeg" -X PUT http://localhost:8080/api/products/AK12345/photo
```


### Running load testing with Gatling

Run the product API and ensure you have a product with SKU = AK21101

Something like this:

```bash
PUT http://localhost:8080/api/products/AK21101
Content-Type: application/json

{
"name": "Keyboard",
"description": "Ergonomic Keyboard",
"price": 60
}
```

Start the Order Management API and run this maven command:

`mvn gatling:test`

After that a result will be created on the target folder with all the information about the load testing. 
