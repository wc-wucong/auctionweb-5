package com.accp.pojo;

import java.util.Date;
import java.util.List;

public class PersonObj {
    private Integer pid;
    private String gyl;
    public String getGyl() {
		return gyl;
	}

	public void setGyl(String gyl) {
		this.gyl = gyl;
	}

	private String pname;

    private Integer psex;

    private Date pdate;

    private String pimg;

    private String pimgdata;

    public Integer getPid() {
        return pid;
    }

    private List<AddressObj> addresses;
    public List<AddressObj> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressObj> addresses) {
		this.addresses = addresses;
	}

	public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getPsex() {
        return psex;
    }

    public void setPsex(Integer psex) {
        this.psex = psex;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg == null ? null : pimg.trim();
    }

    public String getPimgdata() {
        return pimgdata;
    }

    public void setPimgdata(String pimgdata) {
        this.pimgdata = pimgdata == null ? null : pimgdata.trim();
    }
}