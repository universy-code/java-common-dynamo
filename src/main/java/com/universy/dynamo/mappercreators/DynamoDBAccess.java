package com.universy.dynamo.mappercreators;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.universy.dynamo.environment.DynamoEnvironment;

public abstract class DynamoDBAccess {


    public DynamoDBMapper getDynamoDBMapper(){
        return new DynamoDBMapper(getClient(), dynamoDBMapperConfig());
    }

    protected abstract AmazonDynamoDB getClient();

    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        DynamoDBMapperConfig.Builder builder = DynamoDBMapperConfig.builder();
        builder.setTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(getFormattedPrefix()));
        return builder.build();
    }

    private String getFormattedPrefix(){
        return String.format("%s-", DynamoEnvironment.getStage());
    }
}
