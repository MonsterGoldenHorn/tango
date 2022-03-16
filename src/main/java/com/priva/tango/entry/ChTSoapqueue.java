package com.priva.tango.entry;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChTSoapqueue implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal seq;

    private String type;

    private String status;

    private Date createtime;

    private String soapinfo;

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSoapinfo() {
        return soapinfo;
    }

    public void setSoapinfo(String soapinfo) {
        this.soapinfo = soapinfo;
    }

	@Override
	public String toString() {
		return "ChTSoapqueue [seq=" + seq + ", type=" + type + ", status=" + status + ", createtime=" + createtime
				+ ", soapinfo=" + soapinfo + "]";
	}
}