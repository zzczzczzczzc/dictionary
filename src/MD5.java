import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//加密算法
public class MD5 {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5() {
    }

    public static String md5(String input) {
        if (input == null) {
            return null;
        } else {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] inputByteArray = input.getBytes();
                messageDigest.update(inputByteArray);
                byte[] resultByteArray = messageDigest.digest();
                return byteArrayToHex(resultByteArray);
            } catch (NoSuchAlgorithmException var4) {
                return null;
            }
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        byte[] var6 = byteArray;
        int var5 = byteArray.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            resultCharArray[index++] = hexDigits[b >>> 4 & 15];
            resultCharArray[index++] = hexDigits[b & 15];
        }

        return new String(resultCharArray);
    }
}
