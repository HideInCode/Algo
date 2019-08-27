package Context.EventDrivenSimulation;

import Fundamentals.utils.StdDraw;
import Sorting.PriorityQueues.MinPQ;

/**
 * 模拟碰撞环境
 */
public class CollisionSystem {
    
    
    private MinPQ<Event> pq;
    
    //模拟时钟
    private double time = 0.0;
    
    private Particle[] particles;
    
    
    private class Event implements Comparable<Event> {
        
        private final double time;
        
        private final Particle a;
        private final Particle b;
        private final int countA;
        private final int countB;
        
        public Event(double time, Particle a, Particle b) {
            this.time = time;
            this.a = a;
            this.b = b;
            
            if (a != null) {
                countA = a.count();
            } else {
                countA = -1;
            }
            
            if (b != null) {
                countB = b.count();
            } else {
                countB = -1;
            }
            
        }
        
        @Override
        public int compareTo(Event o) {
            return Double.compare(this.time, o.time);
        }
        
        public boolean isValid() {
            if (a != null && a.count() != countA) {
                return false;
            }
            if (b != null && b.count() != countB) {
                return false;
            }
            
            return true;
        }
    }
    
    
    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }
    
    private void predictCollisions(Particle a, double limit) {
        if (a == null) {
            return;
        }
        
        for (int i = 0; i < particles.length; i++) {
            //与particle[i]发生碰撞的事件插入pq
            double dt = a.timeToHit(particles[i]);
            if (time + dt <= limit) {
                pq.insert(new Event(time + dt, a, particles[i]));
            }
        }
        
        double dtX = a.timeToHitVerticalWall();
        if (time + dtX <= limit) {
            pq.insert(new Event(time + dtX, a, null));
        }
        
        double dtY = a.timeToHitHorizontalWall();
        if (time + dtY <= limit) {
            pq.insert(new Event(time + dtY, null, a));
        }
    }
    
    public void redraw(double limit, double Hz) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        
        StdDraw.show(20);
        if (time < limit) {
            pq.insert(new Event(time + 1.0 / Hz, null, null));
        }
    }
    
    //模拟碰撞
    public void simulate(double limit, double Hz) {
        pq = new MinPQ<>();
        
        for (int i = 0; i < particles.length; i++) {
            predictCollisions(particles[i], limit);
        }
        
        pq.insert(new Event(0, null, null));
        
        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) {
                continue;
            }
            
            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.time - time);
            }
            
            time = event.time;
            
            Particle a = event.a;
            Particle b = event.b;
            
            
            //分别对a,b存不存在时的碰撞判断
            if (a != null && b != null) {
                a.bounceOff(b);
            } else if (a != null && b == null) {
                a.bounceOffVerticalWall();
            } else if (a == null && b != null) {
                b.bounceOffHorizontalWall();
            } else if (a == null && b == null) {
                redraw(limit, Hz);
            }
            
            predictCollisions(a, limit);
            predictCollisions(b, limit);
            
            
        }
    }
    
    public static void main(String[] args) {
        StdDraw.show(0);
        int N = Integer.parseInt(args[0]);
        
        Particle[] particles = new Particle[N];
        for (int i = 0; i < N; i++) {
            particles[i] = new Particle();
        }
        
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000, 0.5);
        
        
        //从文件中读取粒子信息
//        StdDraw.setCanvasSize(600, 600);
//        StdDraw.enableDoubleBuffering();
//        Particle[] particles;
//
//        if (args.length == 1) {
//            int n = Integer.parseInt(args[0]);
//            particles = new Particle[n];
//            for (int i = 0; i < n; i++)
//                particles[i] = new Particle();
//        } else {
//            int n = StdIn.readInt();
//            particles = new Particle[n];
//            for (int i = 0; i < n; i++) {
//                double rx = StdIn.readDouble();
//                double ry = StdIn.readDouble();
//                double vx = StdIn.readDouble();
//                double vy = StdIn.readDouble();
//                double radius = StdIn.readDouble();
//                double mass = StdIn.readDouble();
//                int r = StdIn.readInt();
//                int g = StdIn.readInt();
//                int b = StdIn.readInt();
//                Color color = new Color(r, g, b);
//                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
//            }
//        }
//
//        // create collision system and simulate
//        CollisionSystem system = new CollisionSystem(particles);
//        system.simulate(10000, 0.5);
    }
}
