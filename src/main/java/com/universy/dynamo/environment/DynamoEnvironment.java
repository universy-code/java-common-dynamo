package com.universy.dynamo.environment;


import com.universy.dynamo.environment.exceptions.CorruptDynamoEnvironmentSetUpException;

import java.util.Optional;

public class DynamoEnvironment {

    public static String getRegion() {
        Optional<String> awsRegion = Optional.ofNullable(System.getenv("AWS_REGION"));
        return awsRegion.orElseThrow(CorruptDynamoEnvironmentSetUpException::new);
    }

    public static String getStage() {
        Optional<String> environment = Optional.ofNullable(System.getenv("STAGE"));
        return environment.orElseThrow(CorruptDynamoEnvironmentSetUpException::new);
    }

    public static String getDynamoEndpoint(){
        Optional<String> dynamoUrl = Optional.ofNullable(System.getenv("DYNAMO_URL"));
        return dynamoUrl.orElseThrow(CorruptDynamoEnvironmentSetUpException::new);
    }

    public static String getAccessKeyId(){
        Optional<String> accessKeyId = Optional.ofNullable(System.getenv("AWS_ACCESS_KEY_ID"));
        return accessKeyId.orElseThrow(CorruptDynamoEnvironmentSetUpException::new);
    }

    public static String getSecretAccessKey(){
        Optional<String> secretAccessKey = Optional.ofNullable(System.getenv("AWS_SECRET_ACCESS_KEY"));
        return secretAccessKey.orElseThrow(CorruptDynamoEnvironmentSetUpException::new);
    }


}
