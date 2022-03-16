package com.priva.tango.entry;

import java.math.BigDecimal;
import java.util.Date;

public class ChTListInfo {
    private BigDecimal seq;

    private String copyrightId;

    private String songId;

    private String songName;

    private String singerId;

    private String singerName;

    private String lyricAuthorId;

    private String lyricAuthorName;

    private String songAuthorId;

    private String songAuthorName;

    private String status;

    private Date createTime;

    private String lrcUrl;
    
    private BigDecimal versionNumberSeq;

    public ChTListInfo() {
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(String copyrightId) {
        this.copyrightId = copyrightId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getLyricAuthorId() {
        return lyricAuthorId;
    }

    public void setLyricAuthorId(String lyricAuthorId) {
        this.lyricAuthorId = lyricAuthorId;
    }

    public String getLyricAuthorName() {
        return lyricAuthorName;
    }

    public void setLyricAuthorName(String lyricAuthorName) {
        this.lyricAuthorName = lyricAuthorName;
    }

    public String getSongAuthorId() {
        return songAuthorId;
    }

    public void setSongAuthorId(String songAuthorId) {
        this.songAuthorId = songAuthorId;
    }

    public String getSongAuthorName() {
        return songAuthorName;
    }

    public void setSongAuthorName(String songAuthorName) {
        this.songAuthorName = songAuthorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public BigDecimal getVersionNumberSeq() {
        return versionNumberSeq;
    }

    public void setVersionNumberSeq(BigDecimal versionNumberSeq) {
        this.versionNumberSeq = versionNumberSeq;
    }
    
}