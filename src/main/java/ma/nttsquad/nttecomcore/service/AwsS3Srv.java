package ma.nttsquad.nttecomcore.service;

import com.amazonaws.HttpMethod;

public interface AwsS3Srv {

    String generatePreSignedUrl(String filePath, String bucketName, HttpMethod httpMethod);

}
