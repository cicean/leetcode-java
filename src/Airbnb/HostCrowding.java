package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */
public class HostCrowding {

    /**
Host Crowding

You’re given an array of CSV strings representing search results. Results are sorted
by a score initially. A given host may have several listings that show up in these results. .留学论坛-一亩-三分地
Suppose we want to show 12 results per page, but we don’t want the same host to dominate
the results. Write a function that will reorder the list so that a host shows up at most . visit 1point3acres for more.
once on a page if possible, but otherwise preserves the ordering. Your program should return
the new array and print out the results in blocks representing the pages.. Waral 博客有更多文章,
-google 1point3acres paging
Input:
*  An array of csv strings, with sort score
*  number of results per page
     ["host_id,listing_id,score,city",
     "1,28,300.1,San Francisco",
     "4,5,209.1,San Francisco",
     "20,7,208.1,San Francisco",
     "23,8,207.1,San Francisco",
     "16,10,206.1,Oakland",
     "1,16,205.1,San Francisco",
     "1,31,204.6,San Francisco",
     "6,29,204.1,San Francisco",
     "7,20,203.1,San Francisco",
     "8,21,202.1,San Francisco",
     "2,18,201.1,San Francisco",
     "2,30,200.1,San Francisco",
     "15,27,109.1,Oakland",
     "10,13,108.1,Oakland",
     "11,26,107.1,Oakland",
     "12,9,106.1,Oakland",
     "13,1,105.1,Oakland",
     "22,17,104.1,Oakland",
     "1,2,103.1,Oakland",
     "28,24,102.1,Oakland",
     "18,14,11.1,San Jose",
     "6,25,10.1,Oakland",
     "19,15,9.1,San Jose",
     "3,19,8.1,San Jose",
     "3,11,7.1,Oakland",
     "27,12,6.1,Oakland",
     "1,3,5.1,Oakland",
     "25,4,4.1,San Jose",
     "5,6,3.1,San Jose",
     "29,22,2.1,San Jose",
     "30,23,1.1,San Jose"
     ]
*/

    //The above is O(n) runtime best case, O(n log n) worst case, and O(n) worst case space complexity
    static class Listing {
        Float score;
        String hostId;
        String listing;
        Listing(float s, String h, String l) {
            score = s;
            hostId = h;
            listing = l;
        }
    }

    static class Host {
        String hostId;
        LinkedList<Listing> listings = new LinkedList<>();
        Host(String s) {
            hostId = s;
        }
    }

    static Listing getListingFromString(String s) {
        String[] fields = s.split(",");
        String hostId = fields[0];
        float score = Float.parseFloat(fields[2]);
        return new Listing(score,hostId,s);
    }

    static List<String> getSortedPages(ArrayList<String> listings, int perPage) {
        Set<String> set = new HashSet<>();
        PriorityQueue<Host> duplicates = new PriorityQueue<>((a,b)->b.listings.getFirst().score.compareTo(a.listings.getFirst().score));



        Map<String,Host> map = new HashMap<>();
        List<String> output = new ArrayList<>();

        int count = 0;
        for(int i=1; i<listings.size(); i++) {
            if(count > 0 && count % perPage == 0) {
                set.clear();
                count = 0;
                LinkedList<Host> temp = new LinkedList<>();
                while(!duplicates.isEmpty()) {
                    Host h = duplicates.poll();
                    output.add(h.listings.removeFirst().listing);
                    set.add(h.hostId);
                    temp.add(h);
                    count++;
                }
                while(!temp.isEmpty()) {
                    Host h = temp.removeFirst();
                    if(h.listings.size() > 0) {
                        duplicates.add(h);
                    }
                }
            }
            Listing l = getListingFromString(listings.get(i));
            if(!set.contains(l.hostId)) {
                output.add(l.listing);
                set.add(l.hostId);
                count++;
            } else {
                Host h = map.computeIfAbsent(l.hostId, k->new Host(l.hostId));
                h.listings.add(l);
                if(h.listings.size() == 1) { // Not in priority queue
                    duplicates.add(h);
                }
            }
        }
        while(!duplicates.isEmpty()) {
            Host h = duplicates.poll();
            output.add(h.listings.removeFirst().listing);
            if(h.listings.size() > 0) {
                duplicates.add(h);
            }
        }
        return output;
    }

    public List<String> displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return res;
        }
        List<String> visited = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        boolean reachEnd = false;
        while (iter.hasNext()) {
            String curr = iter.next();
            String hostId = curr.split(",")[0];
            if (!visited.contains(hostId) || reachEnd) {
                res.add(curr);
                visited.add(hostId);
                iter.remove();
            }
            if (visited.size() == pageSize) {
                visited.clear();
                reachEnd = false;
                if (!input.isEmpty()) {
                    res.add(" ");
                }
                iter = input.iterator();
            }
            if (!iter.hasNext()) {
                iter = input.iterator();
                reachEnd = true;
            }
        }
        return res;
    }

    public List<String> displayPages_2(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        Iterator<String> iter = input.iterator();
        Set<String> set = new HashSet<>();
        boolean reachEnd = false;
        int counter = 0;
        while (iter.hasNext()) {
            String cur = iter.next();
            String id = (cur.split(","))[0];
            if (!set.contains(id) || reachEnd) {
                res.add(cur);
                set.add(id);
                iter.remove();
                counter++;
            }
            if (counter == pageSize) {
                if (!input.isEmpty())
                    res.add(" ");
                set.clear();
                counter = 0;
                reachEnd = false;
                iter = input.iterator();
            }
            if (!iter.hasNext()) {
                reachEnd = true;
                iter = input.iterator();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        Solution solution = new Solution();

        String[] tests = {"host_id,listing_id,score,city",
                "1,28,300.1,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,208.1,San Francisco",
                "23,8,207.1,San Francisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,San Francisco",
                "1,31,204.6,San Francisco",
                "6,29,204.1,San Francisco",
                "7,20,203.1,San Francisco",
                "8,21,202.1,San Francisco",
                "2,18,201.1,San Francisco",
                "2,30,200.1,San Francisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,San Jose",
                "6,25,10.1,Oakland",
                "19,15,9.1,San Jose",
                "3,19,8.1,San Jose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,San Jose",
                "5,6,3.1,San Jose",
                "29,22,2.1,San Jose",
                "30,23,1.1,San Jose"
        };
        List<String> inputs = new ArrayList<>();
        for (String s : tests) {
            inputs.add(s);
        }

        for (String s : solution.displayPages_2(inputs, 5)) {
            System.out.println(s);
        }


    }
}
