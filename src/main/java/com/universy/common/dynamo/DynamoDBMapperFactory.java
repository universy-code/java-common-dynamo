package com.universy.common.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.universy.common.dynamo.mappercreators.DynamoDBAccess;
import com.universy.common.dynamo.mappercreators.DynamoDBCloudAccess;
import com.universy.common.dynamo.mappercreators.DynamoDBLocalAccess;

public class DynamoDBMapperFactory {


    private DynamoDBMapper mapper;

    public DynamoDBMapperFactory(String stage, String region, String endpoint, String accessKey, String secretKey){
        mapper = createMapper(stage, region, endpoint, accessKey, secretKey);
    }

    public DynamoDBMapper getMapper(){
        return mapper;
    }

    private DynamoDBMapper createMapper(String stage, String region, String endpoint, String accessKey, String secretKey) {
        DynamoDBAccess access;

        if(isLocalStage(stage)){
            access = new DynamoDBLocalAccess(stage, region, endpoint, accessKey, secretKey);
        } else {
            access = new DynamoDBCloudAccess(stage, region);
        }
        return access.getDynamoDBMapper();
    }

    private boolean isLocalStage(String stage){
        return stage.contains("local");
    }


}
