package com.yybt.datastructure.graph;

import com.yybt.datastructure.stack.MyStack;

/**
 * ͼ
 * @author Administrator
 *
 */
public class Graph {
	//��������
	private Vertex[] vertexList;
	//�ڽӾ���
	private int[][] adjMat;
	//����������Ŀ
	private int maxSize = 20;
	//��ǰ����
	private int nVertex;
	//ջ
	private MyStack stack;
	
	public Graph() {
		vertexList = new Vertex[maxSize];
		adjMat = new int[maxSize][maxSize];
		for(int i = 0; i < maxSize; i++) {
			for(int j = 0; j < maxSize; j++) {
				adjMat[i][j] = 0;
			}
		}
		nVertex = 0;
		stack = new MyStack();
	}
	
	/**
	 * ��Ӷ���
	 */
	public void addVertex(char label) {
		vertexList[nVertex++] = new Vertex(label);
	}
	
	/**
	 * ��ӱ�
	 */
	public void addEdge(int start,int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public int getadjUnvisitedVertex(int v) {
		for(int i = 0; i < nVertex; i++) {
			if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return -1;
	}
	
	public void dfs() {
		//���ȷ���0�Ŷ���
		vertexList[0].wasVisited = true;
		//��ʾ�ö���
		displayVertex(0);
		//ѹ��ջ��
		stack.push(0);
		while(!stack.isEmpty()) {
			//���һ��δ���ʹ����ڽӵ�
			int v = getadjUnvisitedVertex((int)stack.peek());
			if(v == -1) {
				//����һ������
				stack.pop();
			} else {
				vertexList[v].wasVisited = true;
				displayVertex(v);
				stack.push(v);
			}
		}
		
		//�������Ժ�Ҫ��������Ϣ�޸�
		for(int i = 0; i < nVertex; i++) {
			vertexList[i].wasVisited = false;
		}
		
	}
	
	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}
	
	public void mst() {
		//���ȷ���0�Ŷ���
		vertexList[0].wasVisited = true;
		//ѹ��ջ��
		stack.push(0);
		while(!stack.isEmpty()) {
			//��ǰ����
			int currentVertex = (int)stack.peek();
			//���һ��δ���ʹ����ڽӵ�
			int v = getadjUnvisitedVertex(currentVertex);
			if(v == -1) {
				//����һ������
				stack.pop();
			} else {
				vertexList[v].wasVisited = true;
				
				stack.push(v);
				displayVertex(currentVertex);
				System.out.print("-");
				displayVertex(v);
				System.out.print(" ");
			}
		}
		
		//�������Ժ�Ҫ��������Ϣ�޸�
		for(int i = 0; i < nVertex; i++) {
			vertexList[i].wasVisited = false;
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		
		g.mst();
	}
	
}
