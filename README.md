# kafka-connect-smt-demo


```shell

# build the transformer code
mvn clean install

# fire up to the Confluent stack
docker-compose up

# start up the connector
curl -X PUT \
     -H "Content-Type: application/json" \
     --data '{
               "tasks.max": "1",
               "connector.class": "FileStreamSource",
               "topic": "filestream",
               "file": "/tmp/kafka-connect/data/test-data.json",
               "key.converter": "org.apache.kafka.connect.storage.StringConverter",
               "value.converter": "org.apache.kafka.connect.json.JsonConverter",
               "value.converter.schemas.enable": "false",
               "errors.tolerance": "all",
               "errors.log.enable": "true",
               "errors.log.include.messages": "true",
               "transforms": "hello",
               "transforms.hello.type": "com.oso.kafka.connect.smt.HelloTransformation"
          }' \
     http://localhost:8083/connectors/filestream-source/config | jq .

# test the output
docker exec connect kafka-console-consumer -bootstrap-server broker:9092 --topic filestream --from-beginning --max-messages 10

# shut the beast down
docker-compose down -v
```