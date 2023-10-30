package org.example.utils;

import java.io.File;

public class FileUtil {
    private static File usersFile;

    public static File getUsersFile() {
        if (usersFile == null)
            usersFile = new File("src/main/java/org/example/asset/users.csv");
        return usersFile;
    }

    public static String getFilePath() {
        return "src/main/java/org/example/asset/";
    }
}
