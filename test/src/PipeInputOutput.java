import java.io.*;

/**
 * Created by qiu on 17-7-25.
 */
public class PipeInputOutput {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            outputStream.connect(inputStream);

            //注意：首先读取线程启动，由于当时没有数据写入，会有I/O阻塞，直到数据被写入，才继续运行下去
            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();
            Thread.sleep(1000);
            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WriteData{
    public void writeMethod(PipedOutputStream outputStream) {
        try {
            System.out.println("write:");
            for (int i = 0; i < 10; i++) {
                String outData = "" + i;
                outputStream.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData{
    public void readMethod(PipedInputStream inputStream){
        try {
            System.out.println("read:");
            byte[] bytes = new byte[2];
            int readLength = inputStream.read(bytes);
            while (readLength != -1) {
                String readData = new String(bytes, 0, readLength);
                System.out.print(" read : "+readData);
                readLength = inputStream.read(bytes);
            }
            System.out.println();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite extends Thread {
    private WriteData writeData;
    private PipedOutputStream outputStream;

    ThreadWrite(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }
    @Override

    public void run() {
        super.run();
        writeData.writeMethod(outputStream);
    }
}

class ThreadRead extends Thread {
    private ReadData readData;
    private PipedInputStream inputStream;

    public ThreadRead(ReadData readData, PipedInputStream inputStream) {
        this.readData = readData;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        super.run();
        readData.readMethod(inputStream);
    }
}


