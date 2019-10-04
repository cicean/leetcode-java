/*
 * 167	Two Sum II - Input array is sorted 	43.4%	Medium
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
[����]
greedy algorithm, ��ͷpointer ���м��ƶ�.
���͵�˫ָ�����⡣

��ʼ����ָ��leftָ��������ʼ����ʼ����ָ��rightָ�������β��

����������������ԣ�

��1�����numbers[left]��numbers[right]�ĺ�tmpС��target��˵��Ӧ������tmp,���left����ָ��һ���ϴ��ֵ��

��2�����tmp����target��˵��Ӧ�ü�Сtmp,���right����ָ��һ����С��ֵ��

��3��tmp����target�����ҵ�������left+1��right+1����ע����1Ϊ��ʼ�±꣩

ʱ�临�Ӷ�O(n): ɨһ��

�ռ临�Ӷ�O(1)

ps: �ϸ���˵������int�ļӺͿ������int����˽�tmp��target����Ϊlong long int�ٽ��бȽϸ�³����
 */
public class TwoSumII {
	
	public int[] twoSum(int[] numbers, int target) {  
        if(numbers==null || numbers.length < 1) return null;  
        int i=0, j=numbers.length-1;  
          
        while(i<j) {  
            int x = numbers[i] + numbers[j];  
            if(x<target) {  
                ++i;  
            } else if(x>target) {  
                --j;  
            } else {  
                return new int[]{i+1, j+1};  
            }  
        }  
        return null;  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoSumII slt = new TwoSumII();
		int[] numbers = {2,7,11,15};
		int target = 9;
		
		int[] index = slt.twoSum(numbers, target);
		
		System.out.println("index1 = " + index[0] + ", " + "index2 = " + index[1]);
		
		
	}

}
