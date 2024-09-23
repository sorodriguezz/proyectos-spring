# Proyectos SpringBoot

### Proyecto 1
Crear Game
```
curl --location 'localhost:8080/v1/games' \
--header 'Content-Type: application/json' \
--data '{
    "name": "dbz"
}'
```

Consultar Game por ID
```
curl --location 'localhost:8080/v1/games/2'
```

Consultar todos los Games
```
curl --location 'localhost:8080/v1/games'
```

Actualizar Game
```
curl --location --request PUT 'localhost:8080/v1/games/4' \
--header 'Content-Type: application/json' \
--data '{
    "name": "lol"
}'
```

Eliminar Game
```
curl --location --request PUT 'localhost:8080/v1/games/4' \
--header 'Content-Type: application/json' \
--data '{
    "name": "lol"
}'
```

Swagger
```
http://localhost:8080/swagger-ui/index.html
```
