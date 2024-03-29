package com.chuncongcong.zxing.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.zxing.vo.ZxingVo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2019/11/11 16:34
 */

@RestController
@Slf4j
public class ZxingController {

	@GetMapping("/product/zxing")
	public String productZxing(ZxingVo zxingVo) throws Exception {
		String content = "http://sb.chuncongcong.com?brand="+zxingVo.getBrand() + "&itemNumber="+zxingVo.getItemNumber() + "&material="+zxingVo.getMaterial() + "&yearMonth=" + zxingVo.getYearMonth();
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
		log.info("content:{}", content);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		String uuid = UUID.randomUUID().toString();
		String pathName = "/upload/" + uuid + "." + format;
		MatrixToImageWriter.writeToPath(bitMatrix, format, new File(pathName).toPath());
		return "http://www.chuncongcong.com:8888"+pathName;
	}
}
