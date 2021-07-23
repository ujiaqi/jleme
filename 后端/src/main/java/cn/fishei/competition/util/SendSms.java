package cn.fishei.competition.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

// 导入 SMS 模块的 client
import com.tencentcloudapi.sms.v20190711.SmsClient;

// 导入要请求接口对应的 request response 类
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Tencent Cloud Sms Sendsms
 * https://cloud.tencent.com/document/product/382/38778
 *
 */
public class SendSms
{
    public static String sendSms(String phone, String code, String id)
    {
        try {
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
             * 本示例采用从环境变量读取的方式，需要预先在环境变量中设置这两个值
             * 您也可以直接在代码中写入密钥对，但需谨防泄露，不要将代码复制、上传或者分享给他人
             * CAM 密钥查询：https://console.cloud.tencent.com/cam/capi
             */
            Credential cred = new Credential("查看腾讯验证码文档", "查看腾讯验证码文档");

            // 实例化一个 http 选项，可选，无特殊需求时可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
            //httpProfile.setProxyHost("host");
            //httpProfile.setProxyPort(port);
            /* SDK 默认使用 POST 方法。
             * 如需使用 GET 方法，可以在此处设置，但 GET 方法无法处理较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK 有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名
             * 例如 SMS 的上海金融区域名为 sms.ap-shanghai-fsi.tencentcloudapi.com */
            //httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK 默认用 TC3-HMAC-SHA256 进行签名
             * 非必要请不要修改该字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化 SMS 的 client 对象
             * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "",clientProfile);
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 填充请求参数，这里 request 对象的成员变量即对应接口的入参
             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台：https://console.cloud.tencent.com/smsv2
             * sms helper：https://cloud.tencent.com/document/product/382/3773 */

            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
            String appid = "1400453798";
            req.setSmsSdkAppid(appid);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
            String sign = "Fishei";
            req.setSign(sign);

            /* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
            //String senderid = "328873";
            //req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            //String session = "xxx";
            //req.setSessionContext(session);

            /* 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] */
            //String extendcode = "xxx";
            //req.setExtendCode(extendcode);

            /* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */
            req.setTemplateID(id);

            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
            String[] phoneNumbers = {phone};
            req.setPhoneNumberSet(phoneNumbers);

            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {code};
            req.setTemplateParamSet(templateParams);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));



            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res);
            return SendSmsResponse.toJsonString(res);

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return e.toString();
        }

    }

    public static void main(String[] args){
        //String s = SendSms.sendSms("+8619858180804", "231da12d", "806444");
        //System.out.println("状态："+ s);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(simpleDateFormat.format(new Date()));

        String a = "19858180804";
        try {
            Long i = Long.parseLong(a);
        }catch (NumberFormatException e){
            System.out.println("错误输入");
        }
        System.out.println(a.getClass().getSimpleName());
        //System.out.println("123456".length());
        /*System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);
        System.out.println(new Random().nextInt(10)+1);*/

    }
}
