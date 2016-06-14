package cn.cnstrong.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Pager {
	/**
	 * 分页
	 */
	
	private int first;//首页
	private int last;//尾页
	private int previous;//上一页
	private int next;//上一页
	private int pageCount;//总共的页数
	private int rowCount;//总共的数据行数
	private int pageRowCount;//每页有多少数据行
	private int curPage;//当前页
	private Collection data;//得到的数据
	//默认每页显示10条
	public Pager(Collection data,int curPage)
	{
		this.data = data;
		this.curPage = curPage;
		this.rowCount = data.size();
		this.pageCount = (int)Math.ceil((double)this.rowCount/(double)this.pageRowCount);
		
	}
	//自行设置每页显示的条数
	public Pager(Collection data,int curPage,int pageRowCount)
	{
		this.data = data;
		this.curPage = curPage;
		this.pageRowCount = pageRowCount;
		this.rowCount = data.size();
		this.pageCount = (int)Math.ceil((double)this.rowCount/(double)this.pageRowCount);
	}
	/**
	 * 获取首页
	 * @return
	 */
	public int getFirst()
	{
		return this.first = 1;
	}
	
	/**
	 * 最后一页取得
	 * @return
	 */
	public int getLast()
	{
		return this.last = this.pageCount;
	}
	
	/**
	 * 回到上一页
	 * @return
	 */
	public int previous()
	{
		return (this.curPage > 1) ? this.pageCount - 1 : 1;
	}
	/**
	 * 跳到下一页
	 * @return
	 */
	public int next()
	{	
		return (this.curPage < this.pageCount) ? this.pageCount + 1 : this.pageCount;
	}
	
	/**
	 * 判断是否是首页
	 * @return
	 */
	public boolean isFirst()
	{
		return (this.curPage == 1) ? true : false;
	}
	
	/**
	 * 判断是否是最后一页
	 * @return
	 */
	public boolean isLast()
	{
		return (this.curPage == this.pageCount) ? true : false;
	}
	
	/**
	 * 获取当前要显示的数据
	 * @return
	 */
	public Collection getData()
	{
		Collection curData = null;
		if(data != null)
		{
			//定义每一页开始到最后显示的行数
			int start,end;
			start = (this.curPage - 1) * this.pageRowCount;
			//判断当前显示的数据是否足以显示一整页
			if(start + this.pageRowCount > this.rowCount)
			{
				end = this.rowCount;
			}else
			{
				end = start + this.pageRowCount;
			}
			
			ArrayList arrayData = null;
			Vector vectorData = null;
			ArrayList arrayCurData = new ArrayList();
			Vector vectorCurData = new Vector();
			boolean isArrayData = true;
			//判断是属于哪一种集合
			if(data instanceof ArrayList)
			{
				arrayData = (ArrayList)data;
				isArrayData = true;
			}else if(data instanceof Vector)
			{
				vectorData = (Vector)data;
				isArrayData = false;
			}
			//循环得到每一页的数据
			for(int i = start; i < end;i++)
			{
				if(isArrayData)
				{
					arrayCurData.add(arrayData.get(i));
				}else
				{
					vectorCurData.add(vectorData.get(i));
				}
			}
			
			if(isArrayData)
			{
				curData = arrayCurData;
			}else{
				curData = vectorCurData;
			}
		}
		return curData;
	}
	
	/**
	 * 客户端显示的工具条
	 */
	public String getToolBar(String url) {
		String str ,temp;
		
		//用于判断url是否存在？
		if(url.indexOf("?") == -1)
		{
			temp = "?";
		} else {
			temp = "&";
		}
		str = "<form method ='post' name ='frmPage' action='"+url+"'>";
		str +="";
		str +="<span style='font-size:12px;font-weight:bold;'>";
		//判断是否是首页
		if(isFirst())
		{
			str += "首页 上一页";	
		} else {
			str += "<a href ='"+url+temp+this.getFirst()+"'>首页</a>";
			str += "<a href ='"+url+temp+"cur_page="+this.previous()+"'>尾页</a>";
		}
		//判断是否是最后一页
		if(isLast())
		{
			str += "下一页 尾页";	
		} else {
			str += "<a href ='"+url+temp+this.next()+"'>下一页</a>";
			str += "<a href ='"+url+temp+"cur_page="+this.getLast()+"'>尾页</a>";
		}
		
		str += "共<b>" + this.rowCount + "</b>条记录";
		return str;
	}
	

}
