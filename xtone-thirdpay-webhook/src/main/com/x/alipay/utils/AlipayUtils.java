package com.x.alipay.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.swiftpass.util.SwiftpassConfig;

public class AlipayUtils {

	// // 商户PID
	// private static final String PARTNER = "2088021159783167";
	// // 商户收款账号
	// private static final String SELLER = "xtgame@bjxiangtone.com";
	// // 商户私钥，pkcs8格式
	// private static final String RSA_PRIVATE =
	// "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM4reycyjGhOM/uEvTsva0fZbCAMD8zD+n+BMRtLAVNDczIsyThCby8Awrhr7tVa1r8TvtTDbcpMHBEMkDSiTP0r19uTLiyt+ei8GK7q/2v1xnfDJfsiRjUwdd9xSPlCLRACd/9iklbKZT60gHzcBov7S/8OvgebLVtCANoGVdDzAgMBAAECgYBN31qK+arTEwbLb93R5x1MbDFNAYFORI/vbSrRNklv28A9KXFvkJhSVqU15360k7UdQyYHUzG7AXhwcCBf4RXWtFIFRu1YTX+vtwOJoxo5q27byh9rOKg+4J4b5ojt78dAjYmmj59GhdUcr9gSBi11gDw5DbM80ytbzCuP6biM4QJBAPxT+O0NZRNBquP5PF0aTR6Ex2Hp4KByaNRDFXn0lnSX0zWAcdYnw9v0MKG7wT6K360SnxPPYmH3NDFhvwFSQesCQQDRK4zYB+0S/pgpdUxNxwnkWAO5arXW8hAm3bZUr71+NT3ozg+NTrDpO6+kpD2L6E5nz//tPJiffVshVyWODuMZAkBSJKgZy82Gyk7urlmXWZOXhtQ9rNyifvxfdYNNU3GTfUWV2j204Pci6MjYLf5H9P/CIRjGYzH9AHPuS4rZzESHAkBMVze1VNc62n7QisYJkP5UP6dEUeUCCSDJ/ptgNy/S0z3ALQzSBwlcZnNJhMQNvwB2tRx0CmytsQPEnFjRiy9pAkBfIoi7dj3n9M5iW31DAUvhLFxm5uBGgXg5yCQUSmqNSApYF7GhiHr3Cf227iglgHD3OO3ngCp8jHQ3MbA+zumH";
	// // 支付宝公钥
	// //private static final String RSA_PUBLIC =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdA+n4L9+INfZZ7yfJyzbsknMgW2CsW3BFplPR
	// L8/rXdk9v2lRNAEewJZxLhqcINCJ/dOu7Wi7WFqm293U76QLR3LgAXf18xogJLhHH9P/U/KFkalU
	// 2byEJrqpt809N51G2RakdD4sx/yvpmm+Rk0a8W6y0AzL40KPzB70oEStQwIDAQAB";

	// 商户PID
	private static final String PARTNER = SwiftpassConfig.Alipay_PARTNER;
	// 商户收款账号
	private static final String SELLER = SwiftpassConfig.Alipay_SELLER;
	// 商户私钥，pkcs8格式
	private static final String RSA_PRIVATE = SwiftpassConfig.Alipay_RSA_PRIVATE;
	// 支付回调地址
	private String notify_url = SwiftpassConfig.Alipay_notify_url;

	/**
	 * 
	 * @param subject
	 *            商品名称
	 * @param body
	 *            商品详情
	 * @param price
	 *            价格
	 * @return
	 */
	public String pay(String subject, String body, String price, String xx_notifyData) {

		// 订单
		String orderInfo = getOrderInfo(subject, body, price, xx_notifyData);

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		return payInfo;

	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	private String getOrderInfo(String subject, String body, String price, String xx_notifyData) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		// orderInfo += "&notify_url=" + "\"" +
		// "http://notify.msp.hk/notify.htm"
		orderInfo += "&notify_url=" + "\"" + notify_url + "?xx_notifyData=" + xx_notifyData + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

}
