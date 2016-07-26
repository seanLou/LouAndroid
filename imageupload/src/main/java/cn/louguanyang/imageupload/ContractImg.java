package cn.louguanyang.imageupload;

import java.util.Date;

public class ContractImg {
	private long id;
	private String uuid;
	private String contractUUID;
	private String path;
	private String fileName;
	private Date createTime;
	private String createIp;
	private Date deleteTime;
	private String deleteIp;
	private int imgState;
	
	public ContractImg() {
		super();
	}
	
	public ContractImg(String uuid, String contractUUID, String path,
			String fileName, String createIp) {
		super();
		this.uuid = uuid;
		this.contractUUID = contractUUID;
		this.path = path;
		this.fileName = fileName;
		this.createIp = createIp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContractUUID() {
		return contractUUID;
	}

	public void setContractUUID(String contractUUID) {
		this.contractUUID = contractUUID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDeleteIp() {
		return deleteIp;
	}

	public void setDeleteIp(String deleteIp) {
		this.deleteIp = deleteIp;
	}

	public int getImgState() {
		return imgState;
	}

	public void setImgState(int imgState) {
		this.imgState = imgState;
	}
	
}
