package com.finalproject;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

public class lectureCourseClassFrame {

	protected Shell shell;
	private List list;
	private ArrayList<lectureCourseClassQuery> sch;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(883, 499);
		shell.setText("Lecture Course Class List");
		
		list = new List(shell, SWT.BORDER);
		list.setBounds(10, 42, 848, 408);
		
		Label LecctureCourseListLabel = new Label(shell, SWT.NONE);
		LecctureCourseListLabel.setBounds(10, 10, 892, 26);
		LecctureCourseListLabel.setText("In this window you can see each Lecture and the courses he teaches, the classes and the time. ");
		
		
		
		try {
				sch = Connection2DB.Instance().getClassCourseList();
				for (lectureCourseClassQuery lectureCourseClass : sch) 
				{
					list.add(lectureCourseClass.toString());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
	}
}