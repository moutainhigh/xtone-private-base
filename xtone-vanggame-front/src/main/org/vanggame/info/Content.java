package org.vanggame.info;

import java.text.SimpleDateFormat;

public class Content {
	private int id;
	private String catalog;
	private String title;
	private String content;
	private int status;
	private Long authorId;
	private String authorName;
	private Long addTime;
	private Long lastModifyId;
	private String lastModifyName;
	private Long lastModifyTime;
	private String timeStr;
	
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
		setTimeStr(lastModifyTime);
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

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		this.timeStr = sdf.format(time);
	}
	
}
