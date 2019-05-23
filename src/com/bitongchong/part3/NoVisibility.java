package src.com.bitongchong.part3;

public class NoVisibility {
    private static int num;
    private static boolean flag;

    static class Func extends Thread {
        @Override
        public void run() {
            while (!flag) {
                System.out.println(num);
            }
            System.out.println(num);
        }
    }


    public static void main(String[] args) {
        new Func().start();
        num = 900;
        flag = true;
    }
}
