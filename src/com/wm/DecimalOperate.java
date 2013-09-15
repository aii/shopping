package com.wm;

import java.math.BigDecimal;

public class DecimalOperate {
	public static float operate(float num) {
		BigDecimal bd = new BigDecimal(num);
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
