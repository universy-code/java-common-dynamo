package com.universy.common.dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBCloudAccess extends DynamoDBAccess {

    public DynamoDBCloudAccess(String stage, String region) {
        super(stage, region);
    }

    @Override
    protected AmazonDynamoDB getClient() {
        return getCloudClient();
    }

    private AmazonDynamoDB getCloudClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.fromName(getRegion()))
                .build();
    }
}
