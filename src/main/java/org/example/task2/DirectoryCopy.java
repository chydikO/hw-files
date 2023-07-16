package org.example.task2;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new IllegalArgumentException("Вихідна директорія не існує або не є директорією!");
        }

        if (!targetDir.exists()) {
            if(targetDir.mkdirs()) {
                System.out.println(targetDir + " створена");
            } else {
                throw new AccessDeniedException("Не можу створити директорію! -> " + targetDir);
            }
        }

        // Отримуємо список файлів і папок у вихідній директорії
        File[] files = sourceDir.listFiles();

        if (files != null) {
            for (File file : files) {
                File targetFile = new File(targetDir, file.getName());

                if (file.isDirectory()) {
                    copyDirectory(file, targetFile);
                } else {
                    Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
