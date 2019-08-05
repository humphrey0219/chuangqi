
## Mysql

```
docker run \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=123456 \
    -e MYSQL_DATABASE=chuangqi  \

    --name mysql创启 \
    --restart=always \
    -d mysql：8.0
docker run --name mysql-chuangqi  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=chuangqi -d mysql:5.7
```