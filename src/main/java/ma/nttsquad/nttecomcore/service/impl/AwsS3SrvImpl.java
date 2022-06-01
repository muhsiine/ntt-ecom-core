package ma.nttsquad.nttecomcore.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import ma.nttsquad.nttecomcore.service.AwsS3Srv;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AwsS3SrvImpl implements AwsS3Srv {

    private AmazonS3 amazonS3;

    public AwsS3SrvImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String generatePreSignedUrl(String filePath, String bucketName, HttpMethod httpMethod) {

        // TODO: Calculate the estimate time to upload or download the file depends on the file size and type.. example: 2MB of file 2 min.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if(httpMethod.equals(HttpMethod.PUT)){
            calendar.add(Calendar.MINUTE, 10);
        } else {
            calendar.add(Calendar.SECOND, 120);
        }

        return amazonS3
                .generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod)
                .toString();
    }
}
