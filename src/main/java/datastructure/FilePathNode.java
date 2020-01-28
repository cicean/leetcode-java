package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
