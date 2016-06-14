package cn.cnstrong.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Pager {
	/**
	 * ��ҳ
	 */
	
	private int first;//��ҳ
	private int last;//βҳ
	private int previous;//��һҳ
	private int next;//��һҳ
	private int pageCount;//�ܹ���ҳ��
	private int rowCount;//�ܹ�����������
	private int pageRowCount;//ÿҳ�ж���������
	private int curPage;//��ǰҳ
	private Collection data;//�õ�������
	//Ĭ��ÿҳ��ʾ10��
	public Pager(Collection data,int curPage)
	{
		this.data = data;
		this.curPage = curPage;
		this.rowCount = data.size();
		this.pageCount = (int)Math.ceil((double)this.rowCount/(double)this.pageRowCount);
		
	}
	//��������ÿҳ��ʾ������
	public Pager(Collection data,int curPage,int pageRowCount)
	{
		this.data = data;
		this.curPage = curPage;
		this.pageRowCount = pageRowCount;
		this.rowCount = data.size();
		this.pageCount = (int)Math.ceil((double)this.rowCount/(double)this.pageRowCount);
	}
	/**
	 * ��ȡ��ҳ
	 * @return
	 */
	public int getFirst()
	{
		return this.first = 1;
	}
	
	/**
	 * ���һҳȡ��
	 * @return
	 */
	public int getLast()
	{
		return this.last = this.pageCount;
	}
	
	/**
	 * �ص���һҳ
	 * @return
	 */
	public int previous()
	{
		return (this.curPage > 1) ? this.pageCount - 1 : 1;
	}
	/**
	 * ������һҳ
	 * @return
	 */
	public int next()
	{	
		return (this.curPage < this.pageCount) ? this.pageCount + 1 : this.pageCount;
	}
	
	/**
	 * �ж��Ƿ�����ҳ
	 * @return
	 */
	public boolean isFirst()
	{
		return (this.curPage == 1) ? true : false;
	}
	
	/**
	 * �ж��Ƿ������һҳ
	 * @return
	 */
	public boolean isLast()
	{
		return (this.curPage == this.pageCount) ? true : false;
	}
	
	/**
	 * ��ȡ��ǰҪ��ʾ������
	 * @return
	 */
	public Collection getData()
	{
		Collection curData = null;
		if(data != null)
		{
			//����ÿһҳ��ʼ�������ʾ������
			int start,end;
			start = (this.curPage - 1) * this.pageRowCount;
			//�жϵ�ǰ��ʾ�������Ƿ�������ʾһ��ҳ
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
			//�ж���������һ�ּ���
			if(data instanceof ArrayList)
			{
				arrayData = (ArrayList)data;
				isArrayData = true;
			}else if(data instanceof Vector)
			{
				vectorData = (Vector)data;
				isArrayData = false;
			}
			//ѭ���õ�ÿһҳ������
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
	 * �ͻ�����ʾ�Ĺ�����
	 */
	public String getToolBar(String url) {
		String str ,temp;
		
		//�����ж�url�Ƿ���ڣ�
		if(url.indexOf("?") == -1)
		{
			temp = "?";
		} else {
			temp = "&";
		}
		str = "<form method ='post' name ='frmPage' action='"+url+"'>";
		str +="";
		str +="<span style='font-size:12px;font-weight:bold;'>";
		//�ж��Ƿ�����ҳ
		if(isFirst())
		{
			str += "��ҳ ��һҳ";	
		} else {
			str += "<a href ='"+url+temp+this.getFirst()+"'>��ҳ</a>";
			str += "<a href ='"+url+temp+"cur_page="+this.previous()+"'>βҳ</a>";
		}
		//�ж��Ƿ������һҳ
		if(isLast())
		{
			str += "��һҳ βҳ";	
		} else {
			str += "<a href ='"+url+temp+this.next()+"'>��һҳ</a>";
			str += "<a href ='"+url+temp+"cur_page="+this.getLast()+"'>βҳ</a>";
		}
		
		str += "��<b>" + this.rowCount + "</b>����¼";
		return str;
	}
	

}
