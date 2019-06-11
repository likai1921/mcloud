package com.cloud.web.model;

public class Cfg {
    private Long id;

    private String name;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	@Override
	public String toString() {
		return "Cfg [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
    
}