package facebook;

/**
 * Created by cicean on 9/7/2016.
 *
 感谢zengm321的帖子的起始，我大概写了一下这个题，还请大家批评指正。。。. more info on 1point3acres.com
 1. flag 记录/*有没有开始， 如果没开始，但找到了/*，说明这行有comment, 把comment前的东西print出来
 2. 如果这行还出现了, 说明comment的结尾也找到了，把结尾后面的代码print出来
        3. 如果flag = true，没出现结尾，说明这行完全是comment(multiple lines), 跳过
        4. 如果flag = false， 没出现开头，说明这行完全是代码，print出来。
 */
public class removecomment {

    public void remove(){
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(flag == false && s.indexOf("/*") != -1){
                flag = true;
                System.out.print(s.substring(0, s.indexOf("/*")));
            }
            if(flag == true && s.indexOf("*/") != -1){
                flag = false;
                System.out.print(s.substring(s.indexOf("*/") + 2));
            }
            if(flag == true){
                continue;
            }
            if(flag == false && s.indexOf("*/") == -1){
                System.out.print(s);
            }
        }
        sc.close();
    }

    String removeComments(String src) {
        return src.replaceAll("(?:/\\*(?:.|[\\n\\r])*?\\*/) | (?://.*)", "");
    }
}
