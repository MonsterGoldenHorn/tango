package com.priva.tango.entry;

import java.math.BigDecimal;
import java.util.Date;

import io.netty.util.internal.StringUtil;

public class ChTSinggerInfo {
    private String status;//状态(暂未使用)
    private String singgerId;//艺术家ID

    private String singgerName;//艺术家名字
    
    private String birthDate;//出生日期

    private String birthPlace;//出生地
    
    private String bloodType;//血型
    
    private String company;//经纪公司
    
    private String constellation;//星座传递枚举值
    
    private String country;//国籍
    
    private String deathdate;//逝世日期
    
    private String activeYears;//活跃年代

    private String gender;//性别: 1.男、2.女、3.组合、0.未知
    
    private String  groupCreateDate;//乐队/组合组成时间
    
    private String  groupDismissDate;//乐队/组合解散时间
    
    private String height;//身高

    private String hobby;//爱好
    
    private String lover;//恋人爱人
    
    private String representWorks;//代表作品
    
    private String anotherName;//别名

    private String  nameCn;//中文名
    
    private String englishname;//英文名字
    
    private String nickName;//昵称

    private String formerName;//曾用名

    private String nation;//民族
    
    private String singgerPciL;//艺术家图片地址(大)
    
    private String singgerPciM;//艺术家图片地址(中)
    
    private String singgerPciS;//艺术家图片地址(小)
    
    private String awards;//获奖情况

    private String intro;//艺人介绍

    private String school;//学校
    
    private String weitht;//体重
    
    private String artistInGroup;//艺人所属组合

    private String groupArtist;//组合包含成员
    
    private String similarSingger;//相似音乐人likeArtistSet

    private String  primitiveName;//原文名
    
    private String localColor;
    
    private String namePinyin;//艺术家名字拼音

    private String singgerIni;//艺术家名字拼音首字母
    
    //tags
    private String instrument;//擅长乐器

    private String singgerArea;//歌手地域

    private String singgerStyle;//演唱风格

    //报文外数据用于本地处理
    private Date createTime;

    private String singgerPciSDir;

    private String singgerPciMDir;

    private String singgerPciLDir;

    private BigDecimal versionNumberSeq;
    @Deprecated
    private String idol;
    @Deprecated
    private String singgerType;
    @Deprecated
    private String singgerLevel;
    
    
    public ChTSinggerInfo(){};

	public String getSinggerId() {
        return singgerId;
    }

    public void setSinggerId(String singgerId) {
        this.singgerId = singgerId;
    }

    public String getSinggerName() {
        return singgerName;
    }

    public void setSinggerName(String singgerName) {
        this.singgerName = singgerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getSinggerIni() {
        return singgerIni;
    }

    public void setSinggerIni(String singgerIni) {
        this.singgerIni = singgerIni;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getActiveYears() {
        return activeYears;
    }

    public void setActiveYears(String activeYears) {
        this.activeYears = activeYears;
    }

    public String getSinggerPciS() {
        return singgerPciS;
    }

    public void setSinggerPciS(String singgerPciS) {
        this.singgerPciS = singgerPciS;
    }

    public String getSinggerPciM() {
        return singgerPciM;
    }

    public void setSinggerPciM(String singgerPciM) {
        this.singgerPciM = singgerPciM;
    }

    public String getSinggerPciL() {
        return singgerPciL;
    }

    public void setSinggerPciL(String singgerPciL) {
        this.singgerPciL = singgerPciL;
    }

    public String getSimilarSingger() {
        return similarSingger;
    }

    public void setSimilarSingger(String similarSingger) {
        this.similarSingger = similarSingger;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getRepresentWorks() {
        return representWorks;
    }

    public void setRepresentWorks(String representWorks) {
        this.representWorks = representWorks;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeitht() {
        return weitht;
    }

    public void setWeitht(String weitht) {
        this.weitht = weitht;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getLover() {
        return lover;
    }

    public void setLover(String lover) {
        this.lover = lover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSinggerArea() {
        return singgerArea;
    }

    public void setSinggerArea(String singgerArea) {
        this.singgerArea = singgerArea;
    }

    public String getSinggerStyle() {
        return singgerStyle;
    }

    public void setSinggerStyle(String singgerStyle) {
        this.singgerStyle = singgerStyle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSinggerPciSDir() {
        return singgerPciSDir;
    }

    public void setSinggerPciSDir(String singgerPciSDir) {
        this.singgerPciSDir = singgerPciSDir;
    }

    public String getSinggerPciMDir() {
        return singgerPciMDir;
    }

    public void setSinggerPciMDir(String singgerPciMDir) {
        this.singgerPciMDir = singgerPciMDir;
    }

    public String getSinggerPciLDir() {
        return singgerPciLDir;
    }

    public void setSinggerPciLDir(String singgerPciLDir) {
        this.singgerPciLDir = singgerPciLDir;
    }

    public BigDecimal getVersionNumberSeq() {
        return versionNumberSeq;
    }

    public void setVersionNumberSeq(BigDecimal versionNumberSeq) {
        this.versionNumberSeq = versionNumberSeq;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(String deathdate) {
        this.deathdate = deathdate;
    }

    public String getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(String groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    public String getGroupDismissDate() {
        return groupDismissDate;
    }

    public void setGroupDismissDate(String groupDismissDate) {
        this.groupDismissDate = groupDismissDate;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getArtistInGroup() {
        return artistInGroup;
    }

    public void setArtistInGroup(String artistInGroup) {
        this.artistInGroup = artistInGroup;
    }

    public String getGroupArtist() {
        return groupArtist;
    }

    public void setGroupArtist(String groupArtist) {
        this.groupArtist = groupArtist;
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName;
    }

    public String getLocalColor() {
        return localColor;
    }

    public void setLocalColor(String localColor) {
        this.localColor = localColor;
    }

    public String getIdol() {
        return idol;
    }

    public void setIdol(String idol) {
        this.idol = idol;
    }

    public String getSinggerType() {
        return singgerType;
    }

    public void setSinggerType(String singgerType) {
        this.singgerType = singgerType;
    }

    public String getSinggerLevel() {
        return singgerLevel;
    }

    public void setSinggerLevel(String singgerLevel) {
        this.singgerLevel = singgerLevel;
    }
    
}