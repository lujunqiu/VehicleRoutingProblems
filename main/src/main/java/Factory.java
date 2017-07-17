/**
 * Created by qiu on 17-6-7.
 * 完成初始化的工作:生成各种节点以及地图信息
 * 场景:模拟北京电网分布图
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
２元组数据结构类
 */
class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TwoTuple other = (TwoTuple) obj;
        if (!this.first.equals(other.first)) {
            return false;
        } else if (!this.second.equals(other.second)) {
            return false;
        } else {
            return true;
        }

    }

    public String toString() {
        return "(" + this.first + "," + this.second + ")";
    }
}

/*
5元组数据结构类
 */
class FiveTuple<A, B, C, D, E> extends TwoTuple {
    public final C third;
    public final D fourth;
    public final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b);
        this.third = c;
        this.fourth = d;
        this.fifth = e;
    }

    public String toString() {
        return "(" + this.first + "," + this.second + "," + this.third + "," + this.fourth + "," + this.fifth + ")";
    }
}

class ShowPic extends JFrame implements Runnable {
    private MyPanel mp;

    public ShowPic(MyPanel mp) {
        this.mp = mp;
    }

    public void run() {
        this.add(mp);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ShowResult extends JFrame implements Runnable {
    private MyPanel2 mp2;

    public ShowResult(MyPanel2 mp2) {
        this.mp2 = mp2;
    }

    public void run() {
        this.add(mp2);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

public class Factory {
    private MyPanel myp;
    private MyPanel2 myp2;

    public Factory() {
        myp = new MyPanel();
        Thread t = new Thread(new ShowPic(myp));
        t.start();
    }

    public void repaint(CarrierRoute carrierRoute) {
        myp2 = new MyPanel2(carrierRoute);
        Thread t2 = new Thread(new ShowResult(myp2));
        t2.start();
    }

    /*
    得到所有新建的客户节点
     */
    public ArrayList<Node> getNodes() {
        return myp.getNodes();
    }

    /*
    得到所有新建的停靠点
     */
    public ArrayList<Depot> getDepots() {
        return myp.getDepots();
    }

    public static void main(String[] args) {
        Factory f = new Factory();

        System.out.println("客户节点数量：　" + f.getNodes().size());
        for (Node x : f.getNodes()) {
            System.out.println("客户节点" + x.getId() + ":" + x.getX() + "," + x.getY());
        }

        System.out.println("停靠点数量： " + f.getDepots().size());
        for (Depot depot : f.getDepots()) {
            System.out.println("停靠点" + depot.getId() + ":" + depot.getX() + "," + depot.getY());
        }
    }
}

class MyPanel extends JPanel {
    public MyPanel() {
        GenerateCustomers();
        GenerateDepots();
    }

    private int size = 3;
    private ArrayList<FiveTuple<Float, Float, Float, Float, Float>> choosed = new ArrayList<FiveTuple<Float, Float, Float, Float, Float>>();
    private float[] data = {2, -50, 10, 20, 80,
            0.5f, 10, 3, 40, 70,
            0.5f, 70, 3, 80, 110,
            -0.5f, 180, 3, 50, 80,
            -2, 255, 3, 20, 50,
            0.1f, 210, 6, 20, 100,
            -1, 320, 10, 100, 200,
            2f, -280, 10, 140, 200,
            -0.5f, 80, 3, 70, 100,
            0.5f, -20, 10, 100, 170};
    private Random rand = new Random();
    private ArrayList<TwoTuple<Float, Float>> customers = new ArrayList<TwoTuple<Float, Float>>();
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<TwoTuple<Integer, Integer>> DepotsCenter = new ArrayList<TwoTuple<Integer, Integer>>();
    private int[] depotsCenter = {40, 10, 30, 20, 40, 30, 70, 30, 75, 50, 55, 80, 60, 120, 90, 110, 75, 130, 50, 150, 70, 170, 80, 200, 50, 190, 90, 190, 120, 200, 150, 180, 130, 160, 170, 140, 200, 130, 180, 120, 200, 110, 170, 85, 185, 75, 170, 60, 180, 50, 170, 40, 150, 20, 90, 50, 110, 20, 120, 40, 130, 30, 140, 60};
    private ArrayList<Depot> depots = new ArrayList<Depot>();

    private void GenerateCustomers() {
        for (int i = 0; i < data.length - 4; i += 5) {
            choosed.add(new FiveTuple(data[i], data[i + 1], data[i + 2], data[i + 3], data[i + 4]));
        }
        for (FiveTuple<Float, Float, Float, Float, Float> item : choosed) {
            float step = (item.fifth - item.fourth) / item.third;
            for (float center = (Float) item.fourth + step; center <= (Float) item.fifth; center += step) {
                customers.add(new TwoTuple<Float, Float>((center + (rand.nextInt() % 5)) * 2 , (((center * (Float) item.first)) + (Float) (item.second) + (rand.nextInt() % 5)) * 2));
            }
        }
    }

    private void GenerateDepots() {
        for (int i = 0; i < depotsCenter.length - 1; i += 2) {
            DepotsCenter.add(new TwoTuple<Integer, Integer>(depotsCenter[i] * 2, depotsCenter[i + 1] * 2));
        }
    }

    public ArrayList<Node> getNodes() {
        int id = 0;
        for (TwoTuple<Float, Float> a : customers) {
            nodes.add(new Node(id++, 10, a.first.intValue(), a.second.intValue()));
        }
        return nodes;
    }

    public ArrayList<Depot> getDepots() {
        char id = 'A';
        for (TwoTuple<Integer, Integer> a : DepotsCenter) {
            depots.add(new Depot(id++, a.first, a.second));
        }
        return depots;
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (TwoTuple<Float, Float> a : customers) {
            g.drawOval(a.first.intValue(), a.second.intValue(), size, size);
        }
        g.setColor(Color.RED);
        g.fillOval(320, 460, 10, 10);
        g.setColor(Color.BLUE);
        for (TwoTuple<Integer, Integer> b : DepotsCenter) {
            g.fillOval(b.first, b.second, 7, 7);
        }
    }
}

class MyPanel2 extends JPanel {
    private CarrierRoute cr;

    public MyPanel2(CarrierRoute cr) {
        this.cr = cr;
    }

    public void paint(Graphics g) {
        for (Depot depot : cr.getRoute()) {
            g.setColor(Color.RED);
            g.fillOval(depot.getX(), depot.getY(), 5, 5);
            g.setColor(Color.BLACK);
            for (UAVRoute ur : depot.getRoutes()) {
                ArrayList<Point> points = ur.getRoute();
                for (int i = 0; i < points.size() - 1; i++) {
                    Point begin = points.get(i);
                    Point end = points.get(i + 1);
                    g.drawLine(begin.getX(), begin.getY(), end.getX(), end.getY());
                }
                Point first = points.get(0);
                Point last = points.get(points.size() - 1);
                g.drawLine(last.getX(), last.getY(), first.getX(), first.getY());
            }
        }
    }
}