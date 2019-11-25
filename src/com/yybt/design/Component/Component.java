package com.yybt.design.Component;

/**
 * 组合模式(Component)
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

	component[] files; // 既可以放文件File类，也可以放文件夹Folder类。Folder类下又有子文件或子文件夹。
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