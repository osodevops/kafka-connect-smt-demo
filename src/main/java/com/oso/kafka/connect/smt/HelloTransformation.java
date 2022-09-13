package com.oso.kafka.connect.smt;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HelloTransformation<R extends ConnectRecord<R>> implements Transformation<R> {

    private Logger logger = LoggerFactory.getLogger(HelloTransformation.class);

    @Override
    public ConfigDef config() {
        return new ConfigDef();
    }

    @Override
    public void configure(Map<String, ?> settings) {
    }

    @Override
    public void close() {
    }

    @Override
    public R apply(R record) {
        Object value = record.value();

        logger.debug("*** Record: " + record.toString());

        if (value instanceof String) {
            value = "Hello " + value.toString();

            return record.newRecord(
                    record.topic(),
                    record.kafkaPartition(),
                    record.keySchema(),
                    record.key(),
                    record.valueSchema(),
                    value,
                    record.timestamp()
            );
        }

        return record;
    }
}