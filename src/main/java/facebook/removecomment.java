package facebook;

/**
 * Created by cicean on 9/7/2016.
 *
 ��лzengm321�����ӵ���ʼ���Ҵ��д��һ������⣬����������ָ��������. more info on 1point3acres.com
 1. flag ��¼/*��û�п�ʼ�� ���û��ʼ�����ҵ���/*��˵��������comment, ��commentǰ�Ķ���print����
 2. ������л�������, ˵��comment�Ľ�βҲ�ҵ��ˣ��ѽ�β����Ĵ���print����
        3. ���flag = true��û���ֽ�β��˵��������ȫ��comment(multiple lines), ����
        4. ���flag = false�� û���ֿ�ͷ��˵��������ȫ�Ǵ��룬print������
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
