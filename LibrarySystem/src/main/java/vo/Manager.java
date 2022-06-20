package vo;


public class Manager {
    int id;
    String name;
    String gender;
    int age;
    String phone;
    String password;

    public Manager() {
    }

    public Manager(int id, String name, String gender, int age,  String phone,  String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
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
        return ""+id+"\t"+name+"\t"+gender+"\t"+age+"\t"+phone;
    }
}

