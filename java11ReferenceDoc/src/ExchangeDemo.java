import java.util.concurrent.Exchanger;

public class ExchangeDemo {

    public static void main(String[] args) {
        Exchanger<String> exgr = new Exchanger<>();

        new Thread(new UseString(exgr)).start();
        new Thread(new MakeString(exgr)).start();
    }
}

class MakeString implements Runnable {

    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> c) {
        this.ex = c;
        this.str = new String();
    }

    @Override
    public void run() {
        char ch = 'A';

        for (int i = 0; i < 10; i++) {
            str += ch++;

            try {
                str = ex.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class UseString implements Runnable {

    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> c) {
        this.ex = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.str = ex.exchange(new String());
                System.out.println("Got: " + this.str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
