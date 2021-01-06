package com.active4j.entity.base.model;

import java.io.Serializable;


/**
 * 用户返回前端的值模型，  适用前端select
 * @author teli_
 *
 */
public class ValueLabelModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4736356073744665935L;


	private String value;

	private String label;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
