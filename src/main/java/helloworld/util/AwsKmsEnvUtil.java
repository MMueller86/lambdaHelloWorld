package helloworld.util;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by mimo on 24.04.2017.
 */
public class AwsKmsEnvUtil {
    private static final Logger log = LoggerFactory.getLogger(AwsKmsEnvUtil.class);

    private AwsKmsEnvUtil() {
        throw new IllegalArgumentException("not supported... it is a Util.");
    }

    public static String decryptKey(final String key) {
        log.info("start decryption of " + key);

        final byte[] encryptedKey = Base64.decode(System.getenv(key));

        final AWSKMS client = AWSKMSClientBuilder.defaultClient();

        final DecryptRequest request = new DecryptRequest()
                .withCiphertextBlob(ByteBuffer.wrap(encryptedKey));

        final ByteBuffer plainTextKey = client.decrypt(request).getPlaintext();
        return new String(plainTextKey.array(), Charset.forName("UTF-8"));
    }
}
