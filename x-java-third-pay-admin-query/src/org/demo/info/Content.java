package org.demo.info;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content {
	private int id;
	private String catalog;
	private String title;
	private String content;
	private int status;
	private String statusStr;
	private Long authorId;
	private String authorName;
	private Long addTime;
	private String addTimeStr;
	private Long lastModifyId;
	private String lastModifyName;
	private Long lastModifyTime;
	private String lastModifyTimeStr;
	private String subTitle;
	private int priority;
	public Content() {
		super();
	}
	
	public Content(int id, String catalog, String title, String content, int status, Long authorId, Long addTime,
			Long lastModifyId, Long lastModifyTime) {
		super();
		this.id = id;
		this.catalog = catalog;
		this.title = title;
		this.content = content;
		this.status = status;
		this.authorId = authorId;
		this.addTime = addTime;
		this.lastModifyId = lastModifyId;
		this.lastModifyTime = lastModifyTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		setStatusStr(status == 1?"发布":"隐藏"); 
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
		setAddTimeStr( sf.format(new Date(addTime)) );
	}

	public Long getLastModifyId() {
		return lastModifyId;
	}

	public void setLastModifyId(Long lastModifyId) {
		this.lastModifyId = lastModifyId;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
		setLastModifyTimeStr( sf.format(new Date(lastModifyTime)) );		
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getLastModifyName() {
		return lastModifyName;
	}

	public void setLastModifyName(String lastModifyName) {
		this.lastModifyName = lastModifyName;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getLastModifyTimeStr() {
		return lastModifyTimeStr;
	}

	public void setLastModifyTimeStr(String lastModifyTimeStr) {
		this.lastModifyTimeStr = lastModifyTimeStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
}
