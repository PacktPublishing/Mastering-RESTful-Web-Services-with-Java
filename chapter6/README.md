## Chapter 6 - Advanced API Concepts and Implementations

Uploading using the curl command

### Create a product first

`curl -v -X PUT http://localhost:8080/api/products/AK12345 -d '{"name":"testprod235","description":"test description","price":123.45}' -H 'Content-Type: application/json'`

### Upload a photo (positive case)

`curl -v -F "file=@/home/mv/Documents/image.jpeg" -X PUT http://localhost:8080/api/products/AK12345/photo`

### Try to upload a PDF file

`curl -v -F "file=@/home/mv/Documents/document.pdf" -X PUT http://localhost:8080/api/products/AK12345/photo`

### Possible error responses

```aiignore
413

{"type":"about:blank","title":"Payload Too Large","status":413,"detail":"Maximum upload size exceeded","instance":"/api/products/AK12345/photo"}
```

```aiignore
415

curl -v -X PUT http://localhost:8080/api/products/AK12345/photo -d '{"name":"testprod235","description":"testdesc","price":123.45}' -H 'Content-Type: application/json'
{"type":"about:blank","title":"Unsupported Media Type","status":415,"detail":"Content-Type 'application/json' is not supported.","instance":"/api/products/AK12345/photo"}
```

### Download
curl -v  http://localhost:8080/api/products/AK12345/photo --output downloaded.jpg
