# API Calls

## GETS
### Get info of a specific city
**Example**
**GET ``` /api/search?name=<String name>```**
```json
{
        "id": 1,
        "name": "name",
        "aqi": 22,
        "pm10": 16,
        "pm25": 12,
        "dominentpol": "o3"
}
```

### Get info of all cities
**Example**
**GET ``` /api/cities```**
```json
[
   {
        "id": 1,
        "name": "name",
        "aqi": 22,
        "pm10": 16,
        "pm25": 12,
        "dominentpol": "o3"
  },
  {
        "id": 2,
        "name": "name2",
        "aqi": 9,
        "pm10": 1,
        "pm25": 0,
        "dominentpol": "pm10"
  },
  ...
  ... 
]
```

## POST
### Insert a city, returns city on success
**Example**
**POST ``` /api/search```**
```json
INPUT: 
{
        "id": 1,
        "name": "name",
        "aqi": 22,
        "pm10": 16,
        "pm25": 12,
        "dominentpol": "o3"
}
OUTPUT:
{
        "id": 1,
        "name": "name",
        "aqi": 22,
        "pm10": 16,
        "pm25": 12,
        "dominentpol": "o3"
}
```

## DELETE
### Delete a city
**Example**
**DELETE ``` /api/search/{id}```**
```json 
true
```