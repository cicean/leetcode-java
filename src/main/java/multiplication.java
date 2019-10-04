
public class multiplication {


public void print_multiplication_table(int n) {

   for (int i= 1; i<= n; i++){
    for (int j = 1 ; j<= n; j++){
    	int res= i*j;
    
        if(j!= n){
        System.out.print(res+" ");}
        else{
        System.out.println(res);
        }     
    }
   }
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  
		multiplication slt = new multiplication();
		slt.print_multiplication_table(5);
		
	}

}
