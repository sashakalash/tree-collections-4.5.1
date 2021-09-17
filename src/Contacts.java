import java.util.HashMap;
import java.util.NoSuchElementException;

public class Contacts {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        contacts.put(contact.getPhone(), contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact.getPhone());
    }

    public Contact getContactByPhone(String phone) {
        return contacts.get(phone);
    }

    public Contact getContactByName(String name, String surname) {
        try {
            Contact foundContact = contacts.entrySet().stream()
                    .filter(entry -> entry.getValue().getName().equals(name) && entry.getValue().getSurname().equals(surname))
                    .findFirst().get().getValue();
            return foundContact;
        } catch(NoSuchElementException err) {
            return null;
        }
    }

    public HashMap<String, Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        contacts.values().forEach(value -> sb.append(value.toString()));
        return sb.toString();
    }
}
