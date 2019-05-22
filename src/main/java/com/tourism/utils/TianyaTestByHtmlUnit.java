/*
package com.cda.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

*/
/**
 * @author xiexingbao
 *http://tianxingzhe.blog.51cto.com/3390077/1755511
 *//*

public class TianyaTestByHtmlUnit{
    
	public String gettable() {
		try {
			 WebClient webClient = new WebClient();
	         // 加载的页面有js语法错误会抛出异常
	         webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
	         webClient.getOptions().setCssEnabled(false); // 禁用css支持
	         // 设置Ajax异步处理控制器即启用Ajax支持
	         webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	         // 当出现Http error时，程序不抛异常继续执行
	         webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	         // 防止js语法错误抛出异常
	         webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
	         // 拿到这个网页
	         HtmlPage page = webClient.getPage("http://passport.tianya.cn/login.jsp");
	         // 填入用户名和密码
	         HtmlInput username = (HtmlInput) page.getElementById("userName");
	         username.type("yourAccount");
	         HtmlInput password = (HtmlInput) page.getElementById("password");
	         password.type("yourPassword");
	         // 提交
	         HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");
	         HtmlPage nextPage = submit.click();
	         return nextPage.asXml();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// jsoup解析文档
    public void jsoupParse() {
 
        try {
            */
/** HtmlUnit请求web页面 *//*

            // 模拟chorme浏览器，其他浏览器请修改BrowserVersion.后面
            WebClient wc = new WebClient(BrowserVersion.CHROME);
 
            wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(false); // 禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(10000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
            HtmlPage page = wc.getPage("http://passport.tianya.cn/login.jsp");
            String pageXml = page.asXml(); // 以xml的形式获取响应文本
            // text只会获取里面的文本,网页html标签和script脚本会被去掉
            String pageText = page.asText();
            System.out.println(pageText);
 
            // 方法一，通过get方法获取
            HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");
 
            // 方法二，通过XPath获取，XPath通常用于无法通过Id搜索，或者需要更为复杂的搜索时
            HtmlDivision div = (HtmlDivision) page.getByXPath("//div").get(0);
 
            // 网络爬虫中主要目的就是获取页面中所有的链接
 
            java.util.List<HtmlAnchor> achList = page.getAnchors();
            for (HtmlAnchor ach : achList) {
                System.out.println(ach.getHrefAttribute());
            }
 
            System.out.println("-------jsoup部分------");
            // 服务器端进行校验并清除有害的HTML代码,防止富文本提交有害代码
         
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
*/
