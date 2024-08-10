//public class helloworld{
//    public static void main(String[] args) {
//        int num1, num2, sum ;
//        num1=30;
//        num2=20;
//        sum = add(num1,num2);
//        System.out.println("Hello World "+ sum);
//    }
//
//    public static int add(int num1, int num2){
//        return num1 + num2;
//    }
//}

public class superclass{
	int a=5;
	int b=15;
    public int add(int c){
    	return a+b+c
        
    }
}

public class subclass extends superclass{
	subclass s = new subclass();
	int ans = s.add(70)
	System.out.println(ans)
			
	
}