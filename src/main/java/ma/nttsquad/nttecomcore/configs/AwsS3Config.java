package ma.nttsquad.nttecomcore.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    @Value("${aws.access-key-id}")
    private String awsAccessKeyId;

    @Value("${aws.secret-key}")
    private String awsSecretKey;

    @Value("${aws.s3.region}")
    private String s3Region;

    @Bean
    public AmazonS3 getAmazonS3ClientBuilder(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(s3Region)
                .build();
    }

}
