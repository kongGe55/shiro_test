package com.ji.domain;

import java.util.List;
import java.util.Objects;

/**
 * ClassName: Directory
 * Description:
 * date: 2019/5/7 17:24
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
public class Directory {
    private String id;
    private String name;
    private String value;
    private String type;
    private String pid;
    private List<Directory> childList;

    public List<Directory> getChildList() {
        return childList;
    }

    public void setChildList(List<Directory> childList) {
        this.childList = childList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return id.equals(directory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", pid='" + pid + '\'' +
                ", childList=" + childList +
                '}';
    }
}
