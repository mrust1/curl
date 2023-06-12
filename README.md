# curl
lcoal curl with groovy script

!! To run the code, you can execute the Groovy script using the Groovy interpreter or by using a Groovy-supported IDE.

Example 1: Sending a GET request without headers or request body

```
groovy CurlExample.groovy https://api.example.com/data
```
This command sends a GET request to https://api.example.com/data without any custom headers or request body.

Example 2: Sending a POST request with headers and request body
```
groovy CurlExample.groovy -m POST -H "Content-Type: application/json" -H "Authorization: Bearer token123" -d '{"name": "John", "age": 25}' https://api.example.com/users
```
This command sends a POST request to https://api.example.com/users with the specified headers (Content-Type and Authorization) and the JSON request body {"name": "John", "age": 25}.


Example 3: Sending a PUT request with a custom header and request body from a file

```
groovy CurlExample.groovy -m PUT -H "Authorization: Bearer token456" -d @request.json https://api.example.com/users/123
```

This command sends a PUT request to https://api.example.com/users/123 with the specified header (Authorization) and the request body read from the request.json file.

You can customize the examples by specifying different URLs, HTTP methods (GET, POST, PUT, DELETE, etc.), headers, and request bodies according to your requirements.
