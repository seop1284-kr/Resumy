package com.proj.resumy.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsInfo {
	private Channel channel;
}

@Data
class Channel {
	private String title;
	private List<NewsItemInfo> item;
}

@Data
class NewsItemInfo {
	private String title;
	private String originallink;
	private String link;
	private String description;
	private LocalDate pubDate;
	
	public void setPubDate(String pubDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

		this.pubDate = LocalDate.parse(pubDate, formatter);
	}
	
	public String getPubDate() {
		return this.pubDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}