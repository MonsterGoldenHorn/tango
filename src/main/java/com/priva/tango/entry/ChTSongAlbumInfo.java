package com.priva.tango.entry;

import java.math.BigDecimal;

public class ChTSongAlbumInfo {
    private BigDecimal seq;

    private String albumId;

    private String songId;

    private String singerId;
    
    private BigDecimal trackNo;

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public BigDecimal getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(BigDecimal trackNo) {
        this.trackNo = trackNo;
    }
    
}