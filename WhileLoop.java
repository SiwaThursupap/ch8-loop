import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    int age = -1;
    
    while (age < 0 || age > 150) {
        IO.print("กรุณาป้อนอายุ ( 0 - 150 ปี) : ");
        age = scanner.nextInt();
    }
    IO.println("ลงทะเบียนสำเร็จ");
    IO.print("อายุของคุณคือ : " +age+ "ปี");
    scanner.close();
}
