package com.bamboo.model;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
public class ReflectionDO {
    private int id;
    private String name;
    private String type;

    public ReflectionDO() {
    }

    public ReflectionDO(String name) {
        this.name = name;
        System.out.println("带有String的构造方法");
    }
    //私有的构造方法
    public ReflectionDO(String name, String type){
        this.name = name;
        this.type = type;
        System.out.println("带有String，int的构造方法");
    }

    public void aa(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        System.out.println(this.id+this.name+this.type );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //私有方法
    private void method5(){
        System.out.println("私有方法");
    }

    @Override
    public String toString() {
        return "ReflectionDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
