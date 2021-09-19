import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Contacts contacts = new Contacts();
    public static MissedCalls missedCalls = new MissedCalls();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Меню:\n" +
                    "1. Добавить контакт\n" +
                    "2. Отредактировать контакт\n" +
                    "3. Удалить контакт\n" +
                    "4. Добавить пропущенный вызов\n" +
                    "5. Вывести все пропущенные вызовы\n" +
                    "6. Очистить пропущенные вызовы\n" +
                    "7. Выход\n" +
                    "Выберите пункт из меню (1-7):");
            String input = scanner.nextLine();
            if ("7".equals(input)) {
                break;
            }
            interactWithContacts(input);
        }
    }

    public static void interactWithContacts(String input) {
        switch (input) {
            case "1":
                System.out.println("Введите данные через пробел в формате \"Имя фамилия телефон группа");
                input = scanner.nextLine();
                addContact(input);
                System.out.println("Контакт добавлен!");
                break;
            case "2":
                System.out.println("Введите телефон контакта для редактирования");
                input = scanner.nextLine();
                editContact(input);
                System.out.println("Контакт успешно отредактирован!");
                break;
            case "3":
                System.out.println("Введите данные контакта в формате \"Имя фамилия\"");
                input = scanner.nextLine();
                removeContact(input);
                break;
            case "4":
                System.out.println("Добавьте номер пропущенного вызова");
                input = scanner.nextLine();
                addMissedCall(input);
                System.out.println("Пропущенный вызов записан!");
                break;
            case "5":
                System.out.println("Список пропущенных вызовов:");
                showMissedCalls();
                break;
            case "6":
                missedCalls.clearMissedCalls();
                System.out.println("Список пропущенных вызовов очищен!");
                break;
            default:
                break;
        }
    }

    public static void addContact(String input) {
        String[] splittedInput = input.split(" ");
        Contact contact = new Contact(splittedInput[0], splittedInput[1], splittedInput[2], splittedInput[3]);
        contacts.addContact(contact);
    }

    public static void addMissedCall(String input) {
        missedCalls.addMissedCall(LocalDateTime.now(), input);
    }

    public static void showMissedCalls() {
        Map<LocalDateTime, String> calls =  missedCalls.getMissedCalls();
        if (calls.size() == 0) {
            System.out.println("Список пуст!");
        } else {
            calls.forEach((time, call) -> {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formatDateTime = time.format(format);
                Contact contact = getContact(call);
                String callInfo = contact != null
                        ? contact.getName() + " " + contact.getSurname() + " " + call
                        : call;
                System.out.println(formatDateTime + " " + callInfo);
            });
        }
    }

    public static Contact getContact(String phone) {
        return contacts.getContactByPhone(phone);
    }

    public static void editContact(String input) {
        Contact contact = getContact(input);
        if (contact == null) {
            System.out.println("Такого контакта не существует!");
        }
        while (true) {
            System.out.println("Какие данные отредактировать:\n" +
                    "1. Имя\n" +
                    "2. Фамилия\n" +
                    "3. Телефон\n" +
                    "4. Группа\n" +
                    "5. Готово\n" +
                    "Выберите пункт из меню (1-5):");
            String newInput = scanner.nextLine();
            if ("5".equals(newInput)) {
                break;
            }
            switch (newInput) {
                case "1":
                    System.out.println("Введите новое имя");
                    input = scanner.nextLine();
                    contact.setName(input);
                    System.out.println("Имя изменено!");
                    break;
                case "2":
                    System.out.println("Введите новую фамилию");
                    input = scanner.nextLine();
                    contact.setSurname(input);
                    System.out.println("Фамилия изменена!");
                    break;
                case "3":
                    System.out.println("Введите новый номер");
                    input = scanner.nextLine();
                    contact.setPhone(input);
                    System.out.println("Телефон изменен!");
                    break;
                case "4":
                    System.out.println("Введите новую группу");
                    input = scanner.nextLine();
                    contact.setGroup(input);
                    System.out.println("Группа изменена!");
                    break;
            }
        }
        contacts.addContact(contact);
    }

    public static void removeContact(String input) {
        String[] splittedInput = input.split(" ");
        Contact contact = contacts.getContactByName(splittedInput[0], splittedInput[1]);
        if (contact == null) {
            System.out.println("Такого контакта не существует!");
            return;
        }
        contacts.removeContact(contact);
        System.out.println("Контакт успешно удален!");
    }
}
