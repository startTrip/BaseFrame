package com.mihua.frameproject.design_mode.builder;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/24
 *     desc   :
 * </pre>
 */
public class Person {

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", mFather=" + mFather +
                ", mMother=" + mMother +
                '}';
    }

    private String name;
    private String age;

    private String sex;
    Person mFather;

    Person mMother;

    public Person(Person.Builder builder) {
        name = builder.name;
        age = builder.age;
        sex = builder.sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person getFather() {
        return mFather;
    }

    public void setFather(Person father) {
        mFather = father;
    }

    public Person getMother() {
        return mMother;
    }

    public void setMother(Person mother) {
        mMother = mother;
    }

    public static class Builder{

        String name;
        String age;

        String sex;
        Person mFather;

        Person mMother;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setSex(String sex){
            this.sex = sex;
            return this;
        }

        public Builder setAge(String age){
            this.age = age;
            return this;
        }


        public Person builder(){
            return new Person(this);
        }
    }
}
