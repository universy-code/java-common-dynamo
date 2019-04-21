package com.universy.common.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.universy.common.dynamo.environment.DynamoEnvironment;
import com.universy.common.dynamo.mappercreators.DynamoDBAccess;
import com.universy.common.dynamo.mappercreators.DynamoDBCloudAccess;
import com.universy.common.dynamo.mappercreators.DynamoDBLocalAccess;

public class DynamoDBMapperFactory {


    public static DynamoDBMapper createMapper() {
        DynamoDBAccess access;

        if(isLocalStage()){
            access = new DynamoDBLocalAccess();
        } else {
            access = new DynamoDBCloudAccess();
        }
        return access.getDynamoDBMapper();
    }

    private static boolean isLocalStage(){
        return DynamoEnvironment.getStage().contains("local");
    }


}
