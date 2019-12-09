package com.yzg.spider.html.test;

import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlUnit {
	 @Test
	    public void homePage() throws Exception {
	        //实例化webClent 对象
	        WebClient webClient = new WebClient();
	        //获取页面
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        //返回此页面的标题或如果指定的标题不是一个空字符串。
	        System.out.println("获取的标题是： 与" + page.getTitleText());
	        //获取网页中的内容，以XML方法返回
	        //返回一个字符串表示XML文档的元素和所有它的孩子(递归)。是当前页面编码使用的字符集。
	        String pageAsXml = page.asXml();
	        //contains 包含
	        System.out.println(pageAsXml.contains("<body class=\"composite\">"));
	        //获取网页中的内容以文本方式返回
	        //返回一个代表该元素的文本表示会对用户可见的如果这个页面是显示在一个web浏览器。例如,single-selection选择元素将返回当前选中的值为文本。
	 
	        String pageAsText = page.asText();
	        //是否包含以下内容
	        System.out.println(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	 
	 
	    }
	 
	    @Test
	    public void homePage_Firefox() throws Exception {
	        //实例化webClent 对象,并设定指定的浏览器
	        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        //返回此页面的标题或如果指定的标题不是一个空字符串。
	        System.out.println("HtmlUnit - Welcome to HtmlUnit" + page.getTitleText());
	 
	 
	    }
	 
	    //获取指定的元素
	    @Test
	    public void getElements() throws Exception {
	        //实例化webClient 对象
	        WebClient webClient = new WebClient();
	        //执行get方法，获取网页
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        //根据指定ID搜索
	        HtmlDivision div = page.getHtmlElementById("banner");
	        //根据指定名称搜索
	        HtmlAnchor anchor = page.getAnchorByName("HtmlUnit");
	        System.out.println(page.toString());
	        System.out.println("-------------------------");
	        System.out.println(anchor.toString());
	    }
	 
	 
	    @Test
	    public void xpath() throws Exception {
	        WebClient webClient = new WebClient();
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	 
	        //得到的所有div的列表
	        List<?> divs = page.getByXPath("//div");
	        for (Object div : divs) {
	            System.out.println(div.toString());
	 
	        }
	        //获取div
	        List<Object> xPath = page.getByXPath("/html/body/div[1]/div[3]");
	        for (Object o : xPath) {
	            System.out.println("-------------------");
	            System.out.println(o.toString());
	        }
	    }
	 
	    @Test
	    public void xpath2() throws Exception {
	        WebClient webClient = new WebClient();
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	 
	        //得到的所有div的列表
	        DomNodeList<DomNode> divs = page.querySelectorAll("div");
	        for (DomNode div : divs) {
	            //输出搜索到的div
	            System.out.println(div.toString());
	        }
	 
	        //获取div的id为'breadcrumbs'
	        DomNode div = page.querySelector("div#breadcrumbs");
	        System.out.println("--------------------");
	        System.out.println(div.toString());
	    }
	 
	    @Test
	    public void homePage_proxy() throws Exception {
	        //代理IP地址
	        String myproxyserver = "";
	        //代理IP端口号
	        int myProxyPort = 80;
	        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "myproxyserver", myProxyPort);
	        //代理设置用户名和密码
	        DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
	        credentialsProvider.addCredentials("username", "password");
	 
	        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        System.out.println("HtmlUnit - Welcome to HtmlUnit" + page.getTitleText());
	    }
	 
	    @Test
	    public void submittingForm() throws Exception {
	        WebClient webClient = new WebClient();
	 
	        // 得到第一页
	        HtmlPage page1 = webClient.getPage("http://some_url");
	 
	        // 我们正在处理的形式,在这种形式,
	        // 找到submit按钮,我们想改变的。
	        HtmlForm form = page1.getFormByName("myform");
	        HtmlSubmitInput button = form.getInputByName("submitbutton");
	        HtmlTextInput textField = form.getInputByName("userid");
	 
	        // 改变文本框的值
	        textField.setValueAttribute("root");
	 
	        // 现在提交表单通过单击按钮,回到第二页。
	        final HtmlPage page2 = button.click();
	 
	    }

}
