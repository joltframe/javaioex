package javaioexp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IoNode {

	private IoNodeType ioNodeType;
	private String fqName;
	
	public IoNode() {
		
	}
	
	public IoNode(IoNodeType ioNodeType, String fqName) {
		super();
		this.ioNodeType = ioNodeType;
		this.fqName = fqName;
	}

	public IoNodeType getIoNodeType() {
		return ioNodeType;
	}
	public void setIoNodeType(IoNodeType ioNodeType) {
		this.ioNodeType = ioNodeType;
	}
	public String getFqName() {
		return fqName;
	}
	public void setFqName(String fqName) {
		this.fqName = fqName;
	}

	@Override
	public String toString() {
		return "IoNode [ioNodeType=" + ioNodeType + ", fqName=" + fqName + "]";
	}
	
	
}
