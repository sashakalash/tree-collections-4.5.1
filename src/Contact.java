public class Contact implements Comparable<Contact> {
    private String name;
    private String surname;
    private String phone;
    private Group group;

    public Contact(String name, String surname, String phone, String group) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.group = convertGroupName(group);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = convertGroupName(group);
    }

    @Override
    public String toString() {
        return  "имя: " + name +
                ", фамилия: " + surname +
                ", телефон: " + phone +
                ", группа: " + group;
    }

    public int compareTo(Contact another) {
        return name.compareTo(another.getName());
    }

    public Group convertGroupName(String name) {
        if ("работа".equals(name)) {
            return Group.WORK;
        }
        if ("семья".equals(name)) {
            return Group.FAMILY;
        }
        if ("друзья".equals(name)) {
            return Group.FRIENDS;
        }
        return Group.UNKNOWN;
    }
}
