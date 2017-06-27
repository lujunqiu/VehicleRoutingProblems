/**
 * Created by qiu on 17-6-22.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 500; i++) {
                if (Thread.interrupted()) {
                    System.out.println("停止状态");
                    throw new InterruptedException();
                }
                System.out.println("i = " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("线程进入异常处理模块，停止运行run方法");
        }

    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("end");
    }
}
