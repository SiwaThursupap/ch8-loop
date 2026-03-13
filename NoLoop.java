public class NoLoop {
    public static void main(String[] args) {
       //IO.println("Hello");
       //IO.println("Hello");
       //IO.println("Hello");
       //IO.println("Hello");
       //IO.println("Hello");
       int sum = 0;
       for (int i=0;i<100;i++){
        if(i%2==0){
            IO.print(i+ " ");
            sum = sum + i ;
        }
       }
       IO.print("\n sum = " + sum);
    }
}
