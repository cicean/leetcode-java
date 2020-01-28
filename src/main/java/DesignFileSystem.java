/**
 * 1166. Design File System
 * Medium
 *
 * 57
 *
 * 4
 *
 * Add to List
 *
 * Share
 * You are asked to design a file system which provides two functions:
 *
 * createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
 * get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 * Implement the two functions.
 *
 * Please refer to the examples for clarifications.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 * Example 2:
 *
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 *
 *
 * Constraints:
 *
 * The number of calls to the two functions is less than or equal to 10^4 in total.
 * 2 <= path.length <= 100
 * 1 <= value <= 10^9
 * NOTE: create method has been changed on August 29, 2019 to createPath. Please reset to default code definition to get new method signature.
 *
 * Accepted
 * 4,339
 * Submissions
 * 7,636
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Airbnb
 * |
 * 9
 * What if you think of a tree hierarchy for the files?.
 * A path is a node in the tree.
 * Use a hash table to store the valid paths along with their values.
 */


import com.sun.xml.internal.ws.util.StringUtils;
import datastructure.FilePathNode;

import java.util.*;


public class DesignFileSystem {

    class FileSystem {

        FilePathNode root;

        public FileSystem() {
            this.root = new FilePathNode();
        }

        public boolean createPath(String path, int value) {
            String[] pathList = path.split("/");
            System.out.println("path = " + Arrays.toString(pathList));
            if (pathList == null || pathList.length < 1) {
                return false;
            }
            Queue<FilePathNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 1;
            while(!queue.isEmpty() && count < pathList.length) {
                FilePathNode filePathNode = queue.poll();
                List<FilePathNode> subPathNodes = filePathNode.subpath;

                for(FilePathNode subPathNode : subPathNodes) {
                    if (subPathNode.path.equals(pathList[count])) {
                        queue.add(subPathNode);
                    }
                }

                if (queue.isEmpty() && count == pathList.length - 1) {
                    FilePathNode newPathNode = new FilePathNode();
                    newPathNode.path = pathList[count];
                    newPathNode.value = value;
                    System.out.println("path = " + pathList[count] + ", queue size = " + queue.size() + ", count=" + count);
                    subPathNodes.add(newPathNode);
                    filePathNode.subpath = subPathNodes;
                    //queue.add(newPathNode);
                }

                if (queue.isEmpty() && count < pathList.length - 1) {
                    return false;
                }

                count++;

            }

            return queue.isEmpty();
        }

        public int get(String path) {
            String[] pathList = path.split("/");

            if (pathList == null || pathList.length < 1) {
                return -1;
            }
            Queue<FilePathNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 1;
            while(!queue.isEmpty() && count < pathList.length) {
                FilePathNode filePathNode = queue.poll();
                List<FilePathNode> subPathNodes = filePathNode.subpath;

                for(FilePathNode subPathNode : subPathNodes) {
                    if (subPathNode.path.equals(pathList[count])) {
                        if (count == pathList.length - 1) {
                            return subPathNode.value;
                        }
                        queue.add(subPathNode);
                    }
                }

                count++;

            }
            return -1;

        }
    }

    public class FilePathNode {
        public String path;
        public int value;
        public List<FilePathNode> subpath;

        public FilePathNode() {
            path = "";
            subpath = new ArrayList<>();
        }

        public FilePathNode(String path, int value, List<FilePathNode> subnode) {
            this.path = path;
            this.value = value;
            this.subpath = subnode;
        }

    }

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */

class FileSystem_Map {

    Map<String, Integer> map;

    public FileSystem_Map() {
        map = new HashMap<>();
        map.put("", 0);
    }

    public boolean createPath(String path, int value) {
        int lastSlash = path.lastIndexOf("/");
        String parentPath = path.substring(0, lastSlash);
        if (!map.containsKey(parentPath) || map.containsKey(path)) {
            return false;
        }
        map.put(path, value);
        return true;
    }

    public int get(String path) {
        if (!map.containsKey(path)) {
            return -1;
        }
        return map.get(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */



}
