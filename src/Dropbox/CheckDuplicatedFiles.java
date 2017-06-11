package Dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by cicean on 10/17/2016.
 */
public class CheckDuplicatedFiles {

    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("cannot initialize SHA-512 hash function", e);
        }
    }

    public void find(Map<String, List<String>> lists, File directory, boolean leanAlgorithm) throws Exception{
        String hash;
        for (File child : directory.listFiles()) {
            if (child.isDirectory()) find(lists, child, leanAlgorithm);
            else {
                try {
                    hash = leanAlgorithm ? makeHashLean(child) : makeHashQuick(child);
                    List<String> list = lists.get(hash);
                    if (list == null) lists.put(hash, new LinkedList<String>());
                    list.add(child.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException("cannot read file " + child.getAbsolutePath(), e);
                }
            }
        }
    }

    /*
 * quick but memory hungry (might like to run with java -Xmx2G or the like to increase heap space if RAM available)
 */
    public String makeHashQuick(File infile) throws Exception {
        FileInputStream fin = new FileInputStream(infile);
        byte data[] = new byte[(int) infile.length()];
        fin.read(data);
        fin.close();
        String hash = new BigInteger(1, md.digest(data)).toString(16);
        return hash;
    }

    /*
    * slower but memory efficient  -- you might like to play with the size defined by "buffSize"
    */
    public String makeHashLean(File infile) throws Exception {
        RandomAccessFile file = new RandomAccessFile(infile, "r");

        int buffSize = 16384;
        byte[] buffer = new byte[buffSize];
        long read = 0;

        // calculate the hash of the whole file for the test
        long offset = file.length();
        int unitsize;
        while (read < offset) {
            unitsize = (int) (((offset - read) >= buffSize) ? buffSize
                    : (offset - read));
            file.read(buffer, 0, unitsize);
            md.update(buffer, 0, unitsize);
            read += unitsize;
        }

        file.close();
        String hash = new BigInteger(1, md.digest()).toString(16);
        return hash;
    }

    public static void main(String[] args) {

        CheckDuplicatedFiles slt = new CheckDuplicatedFiles();

        if (args.length < 1) {
            System.out.println("Please supply a path to directory to find duplicate files in.");
            return;
        }
        File dir = new File(args[0]);
        if (!dir.isDirectory()) {
            System.out.println("Supplied directory does not exist.");
            return;
        }
        Map<String, List<String>> lists = new HashMap<String, List<String>>();
        try {
            slt.find(lists, dir, args.length == 1 || !args[1].equals("-quick"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (List<String> list : lists.values()) {
            if (list.size() > 1) {
                System.out.println("--");
                for (String file : list) {
                    System.out.println(file);
                }
            }
        }
        System.out.println("--");
    }


}
