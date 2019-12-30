curl \
 -d "[{\"id\": -1, \"name\": \"test\" \"crew\": 10}]" \
  -H 'Accept: application/json'   -H 'cache-control: no-cache'   \
  -H 'Content-Type: application/json' \
  -v \
  http://localhost:8080/ships/create


