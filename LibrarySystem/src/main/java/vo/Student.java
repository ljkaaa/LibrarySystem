package vo;

public class Student {
    int id;
    String name;
    String gender;
    int age;
    String institute;
    String phone;
    String password;

    public Student() {
    }

    public Student(int id, String name, String gender, int age, String institute, String phone,  String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.institute = institute;
        this.phone = phone;
        this.password = password;
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
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ""+id+"\t"+name+"\t"+gender+"\t"+age+"\t"+institute+"\t"+phone;
    }
}
