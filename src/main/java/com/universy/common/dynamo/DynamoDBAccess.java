package com.universy.common.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public abstract class DynamoDBAccess {

    private final String stage;
    private final String region;

    public DynamoDBAccess(String stage, String region) {
        this.stage = stage;
        this.region = region;
    }

    public DynamoDBMapper getDynamoDBMapper(){
        return new DynamoDBMapper(getClient(), dynamoDBMapperConfig());
    }

    protected abstract AmazonDynamoDB getClient();

    public String getStage() {
        return stage;
    }

    public String getRegion() {
        return region;
    }

    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        DynamoDBMapperConfig.Builder builder = DynamoDBMapperConfig.builder();
        builder.setTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(getFormattedPrefix()));
        return builder.build();
    }

    private String getFormattedPrefix(){
        return String.format("%s-", stage);
    }


}
