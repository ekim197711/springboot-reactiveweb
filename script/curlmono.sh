curl \
 -d "{\"id\": -1, \"name\": \"test\" \"crew\": 10}" \
  -H 'Accept: application/stream+json'   -H 'cache-control: no-cache'   \
  -H 'Content-Type: application/stream+json' \
  http://localhost:8080/ship/create


