package server.provider;

import server.util.DirectoryViewer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClassProviderImpl implements ClassProvider {
    private Class[] classes;

    private Map<Class,Class[]> classesByAnnotations;

    public ClassProviderImpl() throws ClassNotFoundException {
        this.classes = DirectoryViewer.findControllers(System.getProperty("user.dir") + "\\target\\classes\\app");
        this.classesByAnnotations = new HashMap<>();
    }

    @Override
    public Class[] getClassesByAnnotation(Class annotation) {
        if (this.classesByAnnotations.containsKey(annotation)){
            return this.classesByAnnotations.get(annotation);
        }

        Class[] result = Arrays.stream(this.classes).filter(c -> c.isAnnotationPresent(annotation)).toArray(Class[]::new);

        this.classesByAnnotations.put(annotation,result);

        return result;
    }
}
