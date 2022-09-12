# kafka-connect-smt-demo


```shell
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

```