import java.util.*;

/**
 *
 */
public class GroupthePeopleGiventheGroupSizeTheyBelongTo {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map=new HashMap<>();
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<groupSizes.length;i++) {
            if(!map.containsKey(groupSizes[i])) map.put(groupSizes[i], new ArrayList<>());
            List<Integer> cur=map.get(groupSizes[i]);
            cur.add(i);
            if(cur.size()==groupSizes[i]) {
                res.add(cur);
                map.remove(groupSizes[i]);
            }
        }
        return res;
    }

    class Solution {
        public List<List<Integer>> groupThePeople(int[] g) {
            List<List<Integer>> list1 = new ArrayList<>();

            for (int i = 0; i< g.length;i++)
            {
                if(g[i]!= -1)
                {
                    List<Integer> list2 = new ArrayList<>();
                    list2.add(i);

                    for (int j = i+1 ; j<g.length ; j++)
                    {
                        if( g[i] ==g[j])
                        {


                            if (list2.size()==g[i])
                            {
                                list1.add (list2);
                                list2 = new ArrayList<>();
                                list2.add(j);

                            }
                            else {

                                list2.add(j);

                            }
                            g[j]=-1;
                        }
                    }

                    list1.add (list2);

                }
            }

            return list1;
        }
    }

    class Solution_bt {
        Object[] groupOfSize;
        List<List<Integer>> result;
        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            groupOfSize = new Object[groupSizes.length+1];
            result = new ArrayList<>();
            for(int i=0; i<groupSizes.length; i++) {
                addToGroup(groupSizes[i], i);
            }
            return result;
        }

        void addToGroup(int size, int val) {
            List<Integer> group = (List<Integer>)groupOfSize[size];
            if (group == null) {
                group = new ArrayList<Integer>();
            }
            group.add(val);
            if (group.size() == size) {
                result.add(group);
                group = new ArrayList<Integer>();
            }
            groupOfSize[size] = group;
        }
    }
}
