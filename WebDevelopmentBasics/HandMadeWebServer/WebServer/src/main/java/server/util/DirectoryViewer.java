package server.util;

import server.routing.annotations.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectoryViewer {

    public static Class[] findControllers(String initialPath) throws ClassNotFoundException {
        List<File> classes = getClasses(initialPath);

        List<Class> controllers = new LinkedList<>();
        for (File file : classes) {
            String fileName = file.getAbsolutePath().split("classes")[1].replace("\\",".");

            Class clazz = Class.forName(fileName.substring(1,fileName.length() - 6));

            if (clazz.isAnnotationPresent(Controller.class)){
                controllers.add(clazz);
            }
        }

        return controllers.toArray(new Class[0]);
    }

    private static List<File> getClasses(String path) {
        List<File> files = new ArrayList<>();
        File current = new File(path);


        File[] filesInCurrent = current.listFiles();

        for (File file : filesInCurrent) {
            if (file.isDirectory()){
                List<File> newFiles = getClasses(file.getAbsolutePath());
                for (File newFile : newFiles) {
                    files.add(newFile);
                }
            }

            if (file.getName().endsWith(".class")){
                files.add(file);
            }
        }


        return files;
    }




}
