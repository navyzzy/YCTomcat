package com.yc.tomcat.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	private static Map<String,String>map=new HashMap<String,String>();
	public ParseXml() {
		init();
	}
	private void init() {
		SAXReader read=new SAXReader();
		Document doc=null;
		
		try {
			doc=read.read(this.getClass().getClassLoader().getResourceAsStream("web.xml"));
			List<Element > mines=doc.selectNodes("//mime-mapping");
			//循环解析
			for(Element el:mines) {
				map.put(el.selectSingleNode("extension").getText().trim(), el.selectSingleNode("mime-type").getText().trim());
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getcontentType(String key) {
		return map.getOrDefault(key, "text/html;charset=utf-8");
	}
	

}
