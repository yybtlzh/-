package com.yybt.design.Component;

/**
 * ���ģʽ(Component)
 * 
 * @author lx
 *
 */
abstract class component {

}

class File extends component {
	String filename;
}

class Folder extends component {

	component[] files; // �ȿ��Է��ļ�File�࣬Ҳ���Է��ļ���Folder�ࡣFolder�����������ļ������ļ��С�
	String foldername;

	public Folder(component[] source) {
		files = source;
	}

	public void scan() {
		for (component f : files) {
			if (f instanceof File) {
				System.out.println("File " + ((File) f).filename);
			} else if (f instanceof Folder) {
				Folder e = (Folder) f;
				System.out.println("Folder " + e.foldername);
				e.scan();
			}
		}
	}

}