package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ContentSaver {

    private final File file;

    public ContentSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
