package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.toLowerCase().endsWith(".html") || name.toLowerCase().endsWith(".htm") ||
                file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
