package Q4;

import java.awt.Point;
import java.util.Arrays;
import java.util.Hashtable;

public class UnionFind {
	int[] id;
	Point[] elements;
	double angle;
	int[] size;
	public UnionFind(int size, double angle) {
		elements = Ex4Utils.generateRandomArray(size);//O(size)

		this.size = new int[size];
		id = new int[size];
		for (int i = 0; i < size; i++) {//O(size)
			id[i] = i;
			this.size[i]++;
		}
		this.angle = angle;
		UnionByAngularDist(new Point(50,50));
	}
	public int Find(int p) {//O(log n)
		if (p != id[p])
			id[p] = Find(id[p]);
		return id[p];
	}
	public void doisJoin() {//O(h) when h is tree height
		for (int i = 0; i < this.size.length; i++) {
			this.id[i] = i;
			size[i] = i;
		}
	}
	public void Union(int ind1, int ind2) {
		int parentNumInd1 = Find(ind1);//O(log n)
		int parentNumInd2 = Find(ind2);//O(log n)
		if (parentNumInd1 != parentNumInd2) {
			if (this.size[parentNumInd1] < this.size[parentNumInd2]) {
				id[parentNumInd1] = parentNumInd2;
				this.size[parentNumInd2] = this.size[parentNumInd2] + this.size[parentNumInd1];
			}
			else {
				id[parentNumInd2] = parentNumInd1;
				this.size[parentNumInd1] = this.size[parentNumInd2] + this.size[parentNumInd1];
			}
		}
	}

	public void increaseAngle(int d) {//O(size * log n)
		angle += d;
		UnionByAngularDist(new Point(50,50));
	}
	
	public void UnionByAngularDist(Point p) {//O(size * log size)
		Hashtable<Integer, Integer> check = new Hashtable<>();
		for (int i = 0; i < this.elements.length; i++) {//O(size)
			double newAngle = Ex4Utils.angleFrom(p,elements[i]);//O(1)
			if (!check.containsKey((int) (newAngle / angle))) {
				int newGroup = (int) (newAngle / angle);
				check.put(newGroup ,i);//O(1)
			}
			else {
				Union(check.get((int) (newAngle / angle)) ,i);//O(log size)
			}
		}
	}
}
