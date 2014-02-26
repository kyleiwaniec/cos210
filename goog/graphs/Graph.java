import java.util.*;

public class Graph < V > {
    // Edge class for both undirected and directed case
    public static class Edge <V> {
        private V begin;
        private V end;
        private Edge <V> getReverted() {
            return new Edge < V > (end, begin);
        }
        public Edge (V begin, V end){

        }
        //constructors, getters and setters below
        //...
    }
    private Set < V > vertices;
    private Set < Edge < V >> edges;
    public void traverseBFS() {
        Set < V > visited = new HashSet < V > ();
        LinkedList < V > queue = new LinkedList < V > ();
        //put any vertex to the queue
        queue.addFirst(vertices.iterator().next());
        while (!queue.isEmpty()) {
            V current = queue.pollFirst();
            //disovered new vertex, do some routine
            visited.add(current);
            for (V connected: getConnectedVertices(current))
                if (!visited.contains(connected))
                    queue.addLast(connected);
        }
    }
    public void traverseDFS() {
        Set < V > visited = new HashSet < V > ();
        LinkedList < V > queue = new LinkedList < V > ();
        //put any vertex to the queue
        queue.addFirst(vertices.iterator().next());
        while (!queue.isEmpty()) {
            V current = queue.pollFirst();
            //disovered new vertex, do some routine
            visited.add(current);
            Set < Edge > edgesForCurrent = edgesMap.get(current);
            for (V connected: getConnectedVertices(current))
                if (!visited.contains(connected))
                    queue.addFirst(edge.getEnd()); //the only diff with BFS
        }
    }
    public Set < V > getConnectedVertices(V vertex) {
        Set < V > conencted = new HashSet < V > ();
        for (Edge < V > edge: edges) {
            if (edge.getBegin().equals(vertex)) connected.add(edge.getEnd());
            if (edge.getEnd().equals(vertex)) connected.add(edge.getBegin());
        }
        return connected;
    }
    public static class DistanceToVertex < V > {
        private int distance;
        private V vertex;
        //constructors, getters and setters below
        //...
    }
    public int getDistanceBetweenVertices(V v1, V v2) {
        LinkedList < DistanceToVertex < V >> queue = new LinkedList < DistanceToVertex < V >> ();
        queue.addFirst(new DistanceToVertex(0, v1));
        Set < V > visited = new HashSet < V > ();
        while (queue.size() > 0) {
            DistanceToVertex < V > dtv = queue.pollFirst();
            if (dtv.getVertex().equals(v2)) return dtv.getDistance();
            visited.add(dtv.getVertex());

            for (V connected: getConnectedVertices(dtv.getVertex())){
            	if (!visited.contains(connected)) {
            		queue.addFirst(new DistanceToVertex < V > (dtv.getDistance() + 1, connected));
            	}
            } 
        } 
            return -1;
    }
        public int getMaxDistanceBetweenVertices() {
            List < V > vertexList = new ArrayList < V > (vertices);
            int maxDistance = -1;
            for (int i = 0; i < vertexList.size() - 1; i++)
                for (int j = i + 1; j < vertexList.size(); j++) {
                    V v1 = vertexList.get(i);
                    V v2 = vertexList.get(j);
                    int distance = getDistanceBetweenVertices(v1, v2);
                    if (distance > maxDistance) maxDistance = distance;
                }
            return maxDistance;
        }
        //constructors, getters and setters below
        //...
    }