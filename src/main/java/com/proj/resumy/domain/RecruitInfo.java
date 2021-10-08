package com.proj.resumy.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "dhsOpenEmpInfoList")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecruitInfo {
	private String total;
	private List<Info> dhsOpenEmpInfo;	
}

@Data
class Info {
	private String empWantedTitle;
	private String empBusiNm;
	private String coClcdNm;
	private String empWantedStdt;
	private String empWantedEndt;
	private String empWantedTypeNm;
	private String regLogImgNm;
	private String empWantedHomepgDetail;
	private String empWantedMobileUrl;
}