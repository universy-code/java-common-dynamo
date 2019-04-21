package com.universy.common.dynamo.mappercreators;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.universy.common.dynamo.environment.DynamoEnvironment;

public class DynamoDBCloudAccess extends DynamoDBAccess {

    @Override
    protected AmazonDynamoDB getClient() {
        return getCloudClient();
    }

    private AmazonDynamoDB getCloudClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.fromName(DynamoEnvironment.getRegion()))
                .build();
    }
}
