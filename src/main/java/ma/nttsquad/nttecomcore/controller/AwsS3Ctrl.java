package ma.nttsquad.nttecomcore.controller;

import com.amazonaws.HttpMethod;
import ma.nttsquad.nttecomcore.service.AwsS3Srv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aws-s3")
@CrossOrigin(origins = "*") // remove when done testing, or when setup spring security
public class AwsS3Ctrl {

    private AwsS3Srv awsS3Srv;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public AwsS3Ctrl(AwsS3Srv awsS3Srv) {
        this.awsS3Srv = awsS3Srv;
    }

    @PostMapping("/generate-pre-signed-url")
    public AwsS3PreSignedResponse generatePreSignedUrl(@RequestBody AwsS3PreSignedRequest awsS3PreSignedRequest){

        String filePath = awsS3PreSignedRequest.getFilePath();
        HttpMethod httpMethod = HttpMethod.valueOf(awsS3PreSignedRequest.getMethod());
        String preSignedUrl = awsS3Srv.generatePreSignedUrl(filePath, this.bucketName, httpMethod);

        return new AwsS3PreSignedResponse(preSignedUrl);
    }
}
