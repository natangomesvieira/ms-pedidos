docker build -t ms-pedidos:dev .

docker network create dev-ms-pedidos

docker compose -f compose.yaml up -d

docker rm dev-ms-pedidos
docker run -d \
    --name dev-ms-pedidos \
    --network dev-ms-pedidos \
    --env-file .env \
    ms-pedidos:dev