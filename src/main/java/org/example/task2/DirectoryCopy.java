package org.example.task2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Ви повинні замінити path/to/source/directory шляхом до вихідної директорії, яку ви хочете скопіювати,
 * і path/to/target/directory шляхом до цільової директорії, куди ви хочете зробити копію.
 *
 * Цей код використовує клас File та метод listFiles() для отримання списку файлів і папок у вихідній директорії.
 * Далі, виконується рекурсивне копіювання кожного файлу та папки у відповідний шлях у цільовій директорії.
 * Для копіювання файлів використовується клас Files та метод copy(), а для копіювання папок застосовується
 * рекурсивний виклик copyDirectory().
 *
 * Будь ласка, замініть відповідні значення шляхів та спробуйте виконати цей код для скопіювання директорії.
 * Не забудьте перевірити, чи у вас є необхідні дозволи для читання файлів та запису у вихідну та цільову директорії.
 */
public class DirectoryCopy {
    public static void main(String[] args) {
        String sourceDirPath = "path/to/source/directory";
        String targetDirPath = "path/to/target/directory";

        try {
            copyDirectory(new File(sourceDirPath), new File(targetDirPath));
            System.out.println("Директорія успішно скопійована!");
        } catch (IOException e) {
            System.out.println("Помилка при копіюванні директорії: " + e.getMessage());
        }
    }

    private static void copyDirectory(File sourceDir, File targetDir) throws IOException {
        // Перевіряємо, чи вихідна директорія існує
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new IllegalArgumentException("Вихідна директорія не існує або не є директорією!");
        }

        // Створюємо цільову директорію, якщо вона не існує
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        // Отримуємо список файлів і папок у вихідній директорії
        File[] files = sourceDir.listFiles();

        if (files != null) {
            for (File file : files) {
                File targetFile = new File(targetDir, file.getName());

                if (file.isDirectory()) {
                    // Рекурсивно копіюємо вкладену директорію
                    copyDirectory(file, targetFile);
                } else {
                    // Копіюємо файл
                    Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
