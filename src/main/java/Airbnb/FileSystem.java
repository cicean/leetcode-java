package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */
public class FileSystem {

    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;
    public FileSystem() {
        this.pathMap = new HashMap<>();
        this.callbackMap = new HashMap<>();
        this.pathMap.put("", 0);
    }
    public boolean create(String path, int value) {
        if (pathMap.containsKey(path)) {
            return false;
        }
        int lastSlashIndex = path.lastIndexOf("/");
        if (!pathMap.containsKey(path.substring(0, lastSlashIndex))) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }
    public boolean set(String path, int value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
// Trigger callbacks
        String curPath = path;
        while (curPath.length() > 0) {
            if (callbackMap.containsKey(curPath)) {
                callbackMap.get(curPath).run();
            }
            int lastSlashIndex = path.lastIndexOf("/");
            curPath = curPath.substring(0, lastSlashIndex);
        }
        return true;
    }
    public Integer get(String path) {
        return pathMap.get(path);
    }

    public boolean watch(String path, Runnable callback) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        callbackMap.put(path, callback);
        return true;
    }

}
