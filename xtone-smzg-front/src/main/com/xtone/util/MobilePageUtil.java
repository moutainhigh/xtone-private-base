package com.xtone.util;

import java.util.Map;
import com.xtone.util.*;

public class MobilePageUtil {
	public static String initPageQuery(String url,Map<String, String> params,int rowCount,int pageIndex,String type)
	{
		if(rowCount==0)
			return "";
		
		int pageCount = (rowCount + Constant.MOBILE_PAGE_SIZE -1)/Constant.MOBILE_PAGE_SIZE;
		
//		String result =  "共&nbsp;" + rowCount + "&nbsp;条记录 &nbsp;" + pageIndex + "/" + pageCount + "&nbsp;";
		String result = "";
		String prePage = "";
		String nextPage = "";
		
		String query = url + "?";
		
//		if(params!=null)
//		{
//			for(String key : params.keySet())
//			{
//				query += key + "=" + params.get(key) + "&";
//			}
//		}
		
//		String str = "";
//		for(int i = 1;i<pageCount+1;i++){
//			if(i==pageIndex){
//				str = str + i;
//			}else{
//				str = str + 
//			}
//		}
		
		if(pageIndex!=1)
			prePage = "<a href=\""+ query +"pageindex=" + (pageIndex-1) +"&catalog="+type+ "\">上一页</a>";
		else
			prePage = "";
		result += prePage;
		
		result += "&nbsp;"+pageIndex + "/" + pageCount + "&nbsp;";
		
//		if(1!=pageCount)
//		{
//			for(int i=1; i<=pageCount; i++)
//			{
//				if(i!=pageIndex)
//				{
//					if(i>10)
//					{
//						if(i%5==0 || (i+5)>pageCount  || ((i>(pageIndex-5) && i< (pageIndex+5))))
//							result += "<a href=\""+ query +"pageindex=" + (i) + "\">" + i + "</a>";
//					}
//					else
//						result += "<a href=\""+ query +"pageindex=" + (i) + "\">" + i + "</a>";
//				}
//				else
//				{
//					result += pageIndex + " ";
//					
//				}
//			}
			
//		}
		
//		if(query.length()>1 && params!=null)
//			query = query.substring(0, query.length()-1);
//		
		
		
		
		
		if(pageIndex!=pageCount)
			nextPage = "<a href=\""+ query +"pageindex=" + (pageIndex+1) +"&catalog="+type+ "\">下一页</a>";
		else
			nextPage = "";
		result += nextPage;
		
		
		
		return result;
	}
	
//	   1<a href="http://smzg.xtonegame.com/new/new/index2.html">
//
//     2
//
//     </a>
//
//
//     <a href="http://smzg.xtonegame.com/new/new/index2.html">下一页</a>
	
	public static void main(String[] args) {
		MobilePageUtil util = new MobilePageUtil();
		String result = util.initPageQuery("http://localhost:8080", null , 70, 2,"news");
		System.out.println(result);
	}
//	public static String queryFilter(String query,String... strings)
//	{
//		if(StringUtil.isNullOrEmpty(query)||"null".equalsIgnoreCase(query))
//			return "";
//		
//		 for (int i = 0; i < strings.length; i++)
//		 {
//			 query = query.replaceAll("(^|&)("+ strings[i] +")=.{0,}?($|&)","");
//		 }
//		
//		return query;
//	}
}
