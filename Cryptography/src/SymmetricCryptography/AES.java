package SymmetricCryptography;

import Tools.Converter;

public class AES {
	
	static int[] sBox = {
			0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
	        0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
	        0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
	        0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
	        0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
	        0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
	        0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
	        0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
	        0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
	        0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
	        0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
	        0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
	        0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
	        0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
	        0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
	        0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16};
	
	static int[] sBoxInv = {
	        0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
	        0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
	        0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
	        0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
	        0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
	        0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
	        0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
	        0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
	        0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
	        0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
	        0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
	        0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
	        0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
	        0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
	        0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
	        0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d};

	static int IrrPoly = 0x11B;
	
	static int[] mixColMat = {
			0x02, 0x03, 0x01, 0x01,
			0x01, 0x02, 0x03, 0x01,
			0x01, 0x01, 0x02, 0x03,
			0x03, 0x01, 0x01, 0x02};
	
	static int[] invMixColMat = {
			0x0E, 0x0B, 0x0D, 0x09,
			0x09, 0x0E, 0x0B, 0x0D,
			0x0D, 0x09, 0x0E, 0x0B,
			0x0B, 0x0D, 0x09, 0x0E};
	
	static class GaloisField256 {
		
		static int GF256Add(int aa, int bb) {
			return aa^bb;
		}
		
		static int GF256Multiply(int aa, int bb) {
			String m, q;
			
			if(aa > bb) {
				m = Integer.toBinaryString(aa);
				q = Integer.toBinaryString(bb);
			}else {
				m = Integer.toBinaryString(bb);
				q = Integer.toBinaryString(aa);
			}
			
			while(m.length() < 8) {
				m = "0" + m;
			}
			
			while(q.length() < 8) {
				q = "0" + q;
			}
			
			int prod = 0x00;
			
			for(int i=0; i<q.length(); i++) {
				int t = Integer.parseInt(q.charAt(i) + "", 2);
				int s = Integer.parseInt(m, 2) * t;
				
				prod = prod ^ (int) (s * Math.pow(2, q.length()-i-1));
			}
			
			int k = 0;
			int mult = 1;
			while(prod >= 256) {
				if(prod < IrrPoly) {
					mult = 1;
				}else {
					mult = prod/IrrPoly;
				}
				k=0;
				
				while((mult/=2) > 0) {
					k++;
				}
				
				mult = (int) Math.pow(2, k);
				prod = GaloisField256.GF256Add(prod, mult*IrrPoly);
			}
			
			return prod;
		}
		
		static String GF256Add(String a, String b) {
			int aa = Integer.parseInt(a, 2);
			int bb = Integer.parseInt(b, 2);
			
			return Integer.toBinaryString(GF256Add(aa, bb));
		}
		
		static String GF256Multiply(String a, String b) {
			int aa = Integer.parseInt(a, 2);
			int bb = Integer.parseInt(b, 2);
			
			int prod = GF256Multiply(aa, bb);
			
			return Integer.toBinaryString(prod);
		}
	
		static String GF256MatrixMult(int[] A, int[] B) throws Exception {
			if(A.length != B.length) throw new Exception("Matrices are not of correct length in this context");
			
			int len = (int) Math.sqrt(A.length);
			int[] C = new int[A.length];
			
			for(int i=0; i<len; i++) {
				for(int j=0; j<len; j++) {
					for(int k=0; k<len; k++) {
						C[i*len + j] = GaloisField256.GF256Add(C[i*len + j], GaloisField256.GF256Multiply(A[i*len + k], B[k*len + j]));
					}
				}
			}
			
			StringBuffer result = new StringBuffer();
			for(int i=0; i<16; i++) {
				StringBuffer helper = new StringBuffer(Integer.toBinaryString(C[i]));
				
				while(helper.length() < 8) {
					helper.insert(0, "0");
				}
				
				result.append(helper);
			}
			
			return result.toString();
		}
	}
	
	static class KeyGenerator{
		
		// g function
		static String g(String m, int round) throws Exception {
			if(m.length() != 32) throw new Exception("Operand must be 32 bits of length");
			
			// Circular byte left shift
			StringBuffer shifted = new StringBuffer();
			for(int i=0; i<32; i+=8) {
				int j = (i + 8)%32;
				shifted.append(m.substring(j, j+8));
			}
			
			// Get the substituted 32 bits using the sBox
			String res = substitute32(shifted.toString());
			
			// Get the current round constant
			String Ri = getRC(round);

			return XOR(res, Ri);
		}
		
		// g inverse function
		static String gInv(String m, int round) throws Exception{
			if(m.length() != 32) throw new Exception("Operand must be 32 bits of length");
			
			// Get the current round constant
			String Ri = getRC(round);
			
			String res = XOR(m, Ri);
			
			// Get the substituted 32 bits using the sBox
			res = substitute32Inv(res);
			
			// Circular byte left shift
			StringBuffer shifted = new StringBuffer();
			for(int i=0; i<32; i+=8) {
				int j = (i - 8+32)%32;
				shifted.append(res.substring(j, j+8));
			}

			return shifted.toString();
		}
		
		// Get round constant of corresponding round number
		static String getRC(int round) throws Exception{
			int R = 0x01;
			
			// Calculate the first Byte of the round constant
			// R(0) = 0x01
			// R(j) = 0x02 * R(j-1) for j>0
			for(int i=0; i<round; i++) {
				R = GaloisField256.GF256Multiply(0x02, R);
			}
			
			// Make the length 8 bits
			StringBuffer Ri = new StringBuffer(Integer.toBinaryString(R));
			while(Ri.length() < 8) {
				Ri.insert(0, "0");
			}
			
			// Append zeros to reach 32 bits of length
			while(Ri.length() < 32) {
				Ri.append("0");
			}
			
			return Ri.toString();
		}
		
		// Get next key knowing the current key & current round
		static String getNextKey(String curr, int round) throws Exception{
			if(curr.length() != 128) throw new Exception("Previous key must be 128 bits of length");
			
			StringBuffer newKey = new StringBuffer();
			
			// Split the key
			String[] subKeys = splitTo4(curr);
			
			// Use g function on the 4th substring of key
			String gOutput = g(subKeys[3], round);
			
			// Just some sequential XOR-ing
			newKey.append(XOR(subKeys[0], gOutput));
			newKey.append(XOR(newKey.substring(0, 32), subKeys[1]));
			newKey.append(XOR(newKey.substring(32, 64), subKeys[2]));
			newKey.append(XOR(newKey.substring(64, 96), subKeys[3]));
			
			return newKey.toString();
		}	
		
		// Get previous key knowing the current key & current round
		static String getPrevKey(String curr, int round) throws Exception{
			if(curr.length() != 128) throw new Exception("Previous key must be 128 bits of length");
			
			StringBuffer newKey = new StringBuffer();
			
			// Split the key
			String[] subKeys = splitTo4(curr);
			
			// Just some sequential XOR-ing
			newKey.insert(0, XOR(subKeys[2], subKeys[3]));
			newKey.insert(0, XOR(subKeys[1], subKeys[2]));
			newKey.insert(0, XOR(subKeys[0], subKeys[1]));
			
			// Use g function on the 4th substring of key
			String gOutput = g(newKey.substring(64, 96), round);
			newKey.insert(0, XOR(gOutput, subKeys[0]));
			
			return newKey.toString();
		}
	}
	
	// Just XOR-ing
	static String XOR(String a, String b) throws Exception {
		if(a.length() != b.length()) throw new Exception("Operands must be of same length");
		
		StringBuffer result = new StringBuffer();
		
		for(int i=0; i<a.length(); i++) {
			result.append(a.charAt(i)^b.charAt(i));
		}
		
		return result.toString();
	}
	
	// Split 128 into four parts
	static String[] splitTo4(String A) throws Exception {
		if(A.length()%4 != 0) throw new Exception("Operand must be multiple of 4");
		int len = A.length()/4;
		
		String[] subKeys = new String[4];
		
		subKeys[0] = A.substring(0, len);
		subKeys[1] = A.substring(len, len*2);
		subKeys[2] = A.substring(len*2, len*3);
		subKeys[3] = A.substring(len*3, len*4);
		
		return subKeys;
	}
	
	// Substitute a byte using the sBox
	static String substituteByte(String A) throws Exception {
		if(A.length() != 8) throw new Exception("Operand must be 8 bits of length");
		
		// Get row & col values
		int row = Integer.parseInt(A.substring(0,4), 2);
		int col = Integer.parseInt(A.substring(4,8), 2);
		
		// Calculate the result
		int res = sBox[row*16 + col];
		
		StringBuffer result = new StringBuffer(Integer.toBinaryString(res));
		
		// Adjusting the length to match 8 bits
		while(result.length() < 8) {
			result.insert(0, "0");
		}
		
		return result.toString();
	}
	
	// Substitute a byte using inverse sBox
	static String substituteByteInv(String A) throws Exception{
		if(A.length() != 8) throw new Exception("Operand must be 8 bits of length");
		
		// Get row & col values
		int row = Integer.parseInt(A.substring(0,4), 2);
		int col = Integer.parseInt(A.substring(4,8), 2);
		
		// Calculate the result
		int res = sBoxInv[row*16 + col];
		
		StringBuffer result = new StringBuffer(Integer.toBinaryString(res));
		
		// Adjusting the length to match 8 bits
		while(result.length() < 8) {
			result.insert(0, "0");
		}
		
		return result.toString();
	}
	
	// Substitute 32 bits using the sBox
	static String substitute32(String A) throws Exception {
		if(A.length() != 32) throw new Exception("Operand must be 32 bits of length");
		
		StringBuffer result = new StringBuffer();
		
		// Substitute each Byte alone and appending it to the result string
		for(int i=0; i<32; i+=8) {
			String x = A.substring(i, i+8);
			result.append(substituteByte(x));
		}
		
		return result.toString();
	}
	
	// Substitute 32 bits using the inverse sBox
	static String substitute32Inv(String A) throws Exception{
		if(A.length() != 32) throw new Exception("Operand must be 32 bits of length");
		
		StringBuffer result = new StringBuffer();
		
		// Substitute each Byte alone and appending it to the result string
		for(int i=0; i<32; i+=8) {
			String x = A.substring(i, i+8);
			result.append(substituteByteInv(x));
		}
		
		return result.toString();
	}
	
	// Substitute 128 bits bloc using the sBox 
	static String substitute128(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		StringBuffer result = new StringBuffer();
		
		for(int i=0; i<128; i+=32) {
			result.append(substitute32(bloc.substring(i, i+32)));
		}
		
		return result.toString();
	}
	
	// Substitute 128 bits bloc using the inverse sBox 
	static String substitute128Inv(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		StringBuffer result = new StringBuffer();
		
		for(int i=0; i<128; i+=32) {
			result.append(substitute32Inv(bloc.substring(i, i+32)));
		}
		
		return result.toString();
	}
	
	// Shift one row
	static String shiftOneRow(String row, int index) throws Exception{
		if(row.length() != 32) throw new Exception("Operand must be 32 bits of length");
		
		StringBuffer result = new StringBuffer();
		String[] rowParts = splitTo4(row);
		
		for(int i=index; i<index+4; i++) {
			result.append(rowParts[i%4]);
		}
		
		return result.toString();
	}
	
	// Unshift one row
	static String unshiftOneRow(String row, int index) throws Exception{
		if(row.length() != 32) throw new Exception("Operand must be 32 bits of length");
		
		StringBuffer result = new StringBuffer();
		String[] rowParts = splitTo4(row);
		
		for(int i=-index; i<-index+4; i++) {
			result.append(rowParts[(i+4)%4]);
		}
		
		return result.toString();
	}
	
	// Shift the rows of the bloc
	static String shiftRows(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		StringBuffer result = new StringBuffer();
		int index = 0;
		while(index < 4) {
			StringBuffer row = new StringBuffer();
			for(int i=index*8; i<128; i+=32) {
				row.append(bloc.substring(i, i+8));
			}
			String newRow = shiftOneRow(row.toString(), index);
			
			int step = 8;
			for(int i=0; i<32; i+=8) {
				int k = i/8;
				result.insert(k*8 + index*step, newRow.substring(i, i+8));
				step += 8;
			}
			
			index++;
		}
		
		return result.toString();
	}
	
	// Unshift the rows of the bloc
	static String unshiftRows(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		StringBuffer result = new StringBuffer();
		int index = 0;
		while(index < 4) {
			StringBuffer row = new StringBuffer();
			for(int i=index*8; i<128; i+=32) {
				row.append(bloc.substring(i, i+8));
			}
			String newRow = unshiftOneRow(row.toString(), index);
			
			int step = 8;
			for(int i=0; i<32; i+=8) {
				int k = i/8;
				result.insert(k*8 + index*step, newRow.substring(i, i+8));
				step += 8;
			}
			
			index++;
		}
		
		return result.toString();
	}
	
	// Mixing columns
	static String mixCols(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		int[] array = new int[16];
		int index = 0;
		int j = 0;
		
		while(index < 4) {
			for(int i=index*8; i<128; i+=32) {
				array[j] = Integer.parseInt(bloc.substring(i, i+8), 2);
				j++;
			}
			index++;
		}
		
		String result = GaloisField256.GF256MatrixMult(mixColMat, array);
		
		StringBuffer out = new StringBuffer();
		index = 0;
		while(index < 4) {
			for(int i=index*8; i<128; i+=32) {
				out.append(result.substring(i, i+8));
			}
			
			index++;
		}
		return out.toString();
	}
	
	// Inverse mixcols
	static String mixColsInv(String bloc) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		int[] array = new int[16];
		int index = 0;
		int j = 0;
		
		while(index < 4) {
			for(int i=index*8; i<128; i+=32) {
				array[j] = Integer.parseInt(bloc.substring(i, i+8), 2);
				j++;
			}
			index++;
		}
		
		String result = GaloisField256.GF256MatrixMult(invMixColMat, array);
		
		StringBuffer out = new StringBuffer();
		index = 0;
		while(index < 4) {
			for(int i=index*8; i<128; i+=32) {
				out.append(result.substring(i, i+8));
			}
			
			index++;
		}
		return out.toString();
	}
	
	// Encrypt one round
	static String encryptRound(String bloc, String key) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		bloc = substitute128(bloc);
		bloc = shiftRows(bloc);
		bloc = mixCols(bloc);
		bloc = XOR(bloc, key);
		
		return bloc;
	}
	
	// Decrypt one round
	static String decryptRound(String bloc, String key) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		
		bloc = unshiftRows(bloc);
		bloc = substitute128Inv(bloc);
		bloc = XOR(bloc, key);
		bloc = mixColsInv(bloc);
		
		return bloc;
	}
	
	// Encrypt
	static String encrypt(String bloc, String key) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		if(key.length() != 128) throw new Exception("Key must be 128 bits of length");
		
		bloc = XOR(bloc, key);
		
		for(int i=0; i<9; i++) {
			key = KeyGenerator.getNextKey(key, i);
			bloc = encryptRound(bloc, key);
		}
		
		key = KeyGenerator.getNextKey(key, 9);
		bloc = substitute128(bloc);
		bloc = shiftRows(bloc);
		bloc = XOR(bloc, key);
		
		return bloc;
	}
	
	// Decrypt
	static String decrypt(String bloc, String key) throws Exception{
		if(bloc.length() != 128) throw new Exception("Bloc must be 128 bits of length");
		if(key.length() != 128) throw new Exception("Key must be 128 bits of length");
		
		for(int i=0; i<10; i++) {
			key = KeyGenerator.getNextKey(key, i);
		}
		
		bloc = XOR(bloc, key);
		
		for(int i=9; i>0; i--) {
			key = KeyGenerator.getPrevKey(key, i);
			bloc = decryptRound(bloc, key);
		}
		
		key = KeyGenerator.getPrevKey(key, 0);
		bloc = unshiftRows(bloc);
		bloc = substitute128Inv(bloc);
		bloc = XOR(bloc, key);
		
		return bloc;
	}
	
	public static void main(String[] args) throws Exception{
		String key = "01100101011011100111001101100001001000000110010001100101001000000110101101100101011000001100100101110100011100100110000100101110";		
		String bloc = "01100101011011100111001101100001001000000110010001100101001000000110101101100101011011100110100101110100011100100110000100101110";		
		
		String cipher = encrypt(bloc, key);
		String plain = decrypt(cipher, key);
		
		System.out.println(Converter.binaryToString(plain));
	}
	
}
