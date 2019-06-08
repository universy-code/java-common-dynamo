package com.universy.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.universy.dynamo.environment.DynamoEnvironment;
import com.universy.dynamo.mappercreators.DynamoDBAccess;
import com.universy.dynamo.mappercreators.DynamoDBCloudAccess;
import com.universy.dynamo.mappercreators.DynamoDBLocalAccess;

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
