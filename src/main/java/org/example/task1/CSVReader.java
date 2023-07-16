package org.example.task1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CSVReader {
    public static final String SEP = System.getProperty("file.separator");
    public static final String FILE_DIR = System.getProperty("user.dir") + SEP + "files";

    public static void main(String[] args) throws ParseException {
        String csvFile = FILE_DIR + SEP + "students.csv";

            var students = new ArrayList<Student>();
            try (InputStream in = new FileInputStream(csvFile);
                 Scanner scanner = new Scanner(in)) {
                scanner.nextLine();
                while (true) {
                    try {
                        String[] data = scanner.nextLine().split(",");
                        String surname = data[0].trim();
                        String name = data[1].trim();
                        String lastName = data[2].trim();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        Date dateOfBirth = sdf.parse(data[3].trim());
                        int grade = Integer.parseInt(data[4].trim());
                        students.add(new Student(surname, name, lastName, dateOfBirth, grade));
                    } catch (Exception ex) {
                        break;
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        double averageAge = students.stream()
                .mapToLong(student -> {
                    Date currentDate = new Date();
                    long ageInMillis = currentDate.getTime() - student.getDateOfBirth().getTime();
                    return ageInMillis / (1000L * 60 * 60 * 24 * 365);
                })
                .average()
                .orElse(0);

            System.out.println("Загальна кількість студентів: " + students.size());
            System.out.println("Середній вік студентів: " + averageAge);

        }
    }

