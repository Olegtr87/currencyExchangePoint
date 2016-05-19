package com.epam.vasilevsky.exchanger.webapp.page.course;

import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
import com.epam.vasilevsky.exchanger.webapp.page.course.panel.CourseListPanel;

public class CoursePage extends AbstractHomePage {

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new CourseListPanel("list-panel"));
	}

}
