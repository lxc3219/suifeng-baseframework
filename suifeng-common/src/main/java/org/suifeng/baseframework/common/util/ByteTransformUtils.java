package org.suifeng.baseframework.common.util;

public class ByteTransformUtils {
	/**
	 * 将字节数组转换为十六进制字符串
	 * @Title byteToStr
	 * @param byteArray
	 * @return String
	 * @CreateTime 2018年8月22日 下午12:30:05
	 * @author luoxc
	 * @version 1.0
	 * @since JDK 1.7
	 */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * @Title byteToHexStr
	 * @param mByte
	 * @return String
	 * @CreateTime 2018年8月22日 下午12:30:14
	 * @author luoxc
	 * @version 1.0
	 * @since JDK 1.7
	 */
	public static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
