import java.io.File;
import java.io.IOException;
import java.util.*;

public class Closed {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        File infile = new File(filename);
        Scanner input = new Scanner(infile);

        int T = input.nextInt();
        int R = input.nextInt();
        int C = input.nextInt();
        int a = input.nextInt();
        int b = input.nextInt();
        int tdep = input.nextInt();

        int[] dist = new int[T + 1];
        for(int i = 1; i <= T; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[a] = tdep;

        ArrayList<Roads>[] adj = new ArrayList[T + 1];
        for (int i = 1; i <= T; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            int t1 = input.nextInt();
            int t2 = input.nextInt();
            int dur = input.nextInt();
            Roads road = new Roads(dur, t1, t2, i);
            adj[t1].add(road);
            adj[t2].add(new Roads(dur, t2, t1, i));
        }

        Map<Integer, List<Closures>> closuresByRoad = new HashMap<>();
        for (int i = 0; i < C; i++) {
            int road = input.nextInt() - 1;
            int start = input.nextInt();
            int end = input.nextInt();
            if (!closuresByRoad.containsKey(road)) {
                closuresByRoad.put(road, new ArrayList<>());
            }
            closuresByRoad.get(road).add(new Closures(road, start, end));        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a1 -> a1[1]));
        pq.add(new int[]{a, tdep});
        boolean[] visited = new boolean[T + 1];

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], time = curr[1];

            if (visited[node]) continue;
            visited[node] = true;

            for (Roads road : adj[node]) {
                int neighbor = road.endTown;
                int wait = getWaitingTime(time, road.duration, road.index, closuresByRoad);
                int newTime = time + wait + road.duration;

                if (newTime < dist[neighbor]) {
                    dist[neighbor] = newTime;
                    pq.add(new int[]{neighbor, newTime});
                }
            }
        }

        int result = dist[b];
        System.out.println(result);
        scanner.close();
    }

    static int getWaitingTime(int time, int duration, int index, Map<Integer, List<Closures>> closuresByRoad) {
        int adjustedStart = time;
        List<Closures> list = closuresByRoad.get(index);
        if (list == null) return 0;

        for (int i = 0; i < list.size(); i++) {
            Closures closure = list.get(i);
            if (!(adjustedStart + duration <= closure.start || adjustedStart >= closure.end)) {
                adjustedStart = closure.end;
                i = -1;
            }
        }
        return adjustedStart - time;
    }
}

class Roads {
    int duration;
    int startTown;
    int endTown;
    int index;

    public Roads(int duration, int startTown, int endTown, int index) {
        this.duration = duration;
        this.startTown = startTown;
        this.endTown = endTown;
        this.index = index;
    }
}

class Closures {
    int road;
    int start;
    int end;

    public Closures(int road, int start, int end) {
        this.road = road;
        this.start = start;
        this.end = end;
    }
}
