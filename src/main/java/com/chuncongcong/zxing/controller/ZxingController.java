package com.chuncongcong.zxing.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author HU
 * @date 2019/11/11 16:34
 */

@RestController
public class ZxingController {

	@GetMapping("/test")
	public void test() throws Exception {
		String content = "https://www.baidu.com?dasdasdasdadasdadasdasdasdasdsadasdasdasdas";
		int width = 200;
		int height = 200;
		String format = "jpg";
		Map<EncodeHintType, Object> hints = new HashMap<>(16);
		//内容编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//设置二维码边的空度，非负数
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(bitMatrix, format, new File("/upload/zxing."+format).toPath());
	}
}
